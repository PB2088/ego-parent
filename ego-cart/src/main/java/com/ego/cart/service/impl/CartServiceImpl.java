package com.ego.cart.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cache.dao.JedisClusterDao;
import com.ego.cart.service.CartService;
import com.ego.commons.domain.EgoResult;
import com.ego.commons.domain.TbItemExtend;
import com.ego.commons.utils.CommonFunctions;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.JsonUtils;
import com.ego.domain.TbItem;
import com.ego.dubbo.service.TbItemDubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 购物车实现类
 *
 * @author boge.peng
 * @create 2019-03-17 17:22
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private JedisClusterDao jedisClusterDao;

    @Reference
    private TbItemDubboService itemDubboService;

    @Value("${redis.cart.item.id.key}")
    private String cartKey;

    @Value("${passport.user.url}")
    private String passportUrl;

    @Override
    public void addCart(long id, int num, String cookieValue) {

        String jsonUser = HttpClientUtil.doPost(passportUrl + cookieValue);
        EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
        String key = cartKey + ((LinkedHashMap) er.getData()).get("username");

        Map<String, TbItemExtend> cartCollection = new HashMap<>();
        if (jedisClusterDao.exists(key)) {
            String cartJson = jedisClusterDao.get(key);
            if (CommonFunctions.notEmpty(cartJson)) {
                cartCollection = JsonUtils.jsonToPojo(cartJson, cartCollection.getClass());

                if (cartCollection.containsKey(String.valueOf(id))) {
                    String itemExtendJson = JsonUtils.objectToJson(cartCollection.get(String.valueOf(id)));

                    TbItemExtend itemExtend = JsonUtils.jsonToPojo(itemExtendJson, TbItemExtend.class);
                    itemExtend.setNum(itemExtend.getNum() + num);

                    cartCollection.put(String.valueOf(id), itemExtend);

                    jedisClusterDao.set(key, JsonUtils.objectToJson(cartCollection));

                    return;
                }
            }
        }

        TbItem tbItem = itemDubboService.selectById(id);
        TbItemExtend itemExtend = new TbItemExtend();
        BeanUtils.copyProperties(tbItem, itemExtend);
        itemExtend.setNum(num);
        itemExtend.setImages(CommonFunctions.notEmpty(tbItem.getImage()) ? tbItem.getImage().split(",") : new String[1]);

        cartCollection.put(String.valueOf(id), itemExtend);

        jedisClusterDao.set(key, JsonUtils.objectToJson(cartCollection));
    }

    @Override
    public List<TbItemExtend> showCart(String cookieValue) {
        String jsonUser = HttpClientUtil.doPost(passportUrl + cookieValue);
        EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
        String key = cartKey + ((LinkedHashMap) er.getData()).get("username");

        String cartJsonValue = jedisClusterDao.get(key);
        Map<String, LinkedHashMap<String, Object>> map = JsonUtils.jsonToPojo(cartJsonValue, Map.class);

        Collection<LinkedHashMap<String, Object>> values = map.values();

        List<TbItemExtend> tbItemExtends = JsonUtils.jsonToList(JsonUtils.objectToJson(values), TbItemExtend.class);

        return tbItemExtends;
    }

    @Override
    public EgoResult update(long id, int num, String cookieValue) {
        String jsonUser = HttpClientUtil.doPost(passportUrl + cookieValue);
        EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
        String key = cartKey + ((LinkedHashMap) er.getData()).get("username");

        String cartJson = jedisClusterDao.get(key);

        Map<String, TbItemExtend> cartCollection = JsonUtils.jsonToPojo(cartJson, Map.class);
        String itemExtendJson = JsonUtils.objectToJson(cartCollection.get(String.valueOf(id)));

        TbItemExtend itemExtend = JsonUtils.jsonToPojo(itemExtendJson, TbItemExtend.class);
        itemExtend.setNum(num);

        cartCollection.put(String.valueOf(id), itemExtend);

        jedisClusterDao.set(key, JsonUtils.objectToJson(cartCollection));

        return EgoResult.SUCCESS;
    }

    @Override
    public EgoResult delete(long id, String cookieValue) {
        String jsonUser = HttpClientUtil.doPost(passportUrl + cookieValue);
        EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
        String key = cartKey + ((LinkedHashMap) er.getData()).get("username");

        String cartJson = jedisClusterDao.get(key);
        Map<String, TbItemExtend> cartCollection = JsonUtils.jsonToPojo(cartJson, Map.class);
        cartCollection.remove(String.valueOf(id));

        String ok = jedisClusterDao.set(key, JsonUtils.objectToJson(cartCollection));
        if (Objects.equals("OK", ok)) {
            return EgoResult.SUCCESS;
        }

        return EgoResult.FAILED;
    }
}
