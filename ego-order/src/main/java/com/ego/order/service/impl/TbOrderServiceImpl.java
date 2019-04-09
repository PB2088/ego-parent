package com.ego.order.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cache.dao.JedisClusterDao;
import com.ego.commons.domain.EgoResult;
import com.ego.commons.domain.TbItemExtend;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.IDUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.domain.TbItem;
import com.ego.domain.TbOrder;
import com.ego.domain.TbOrderItem;
import com.ego.domain.TbOrderShipping;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.dubbo.service.TbOrderDubboService;
import com.ego.order.domain.OrderParam;
import com.ego.order.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author boge.peng
 * @create 2019-03-18 12:58
 */
@Service
public class TbOrderServiceImpl implements TbOrderService {

    @Reference
    private TbItemDubboService itemDubboService;

    @Autowired
    private JedisClusterDao jedisClusterDao;

    @Value("${redis.cart.item.id.key}")
    private String cartKey;

    @Value("${passport.user.url}")
    private String passportUrl;

    @Reference
    private TbOrderDubboService orderDubboService;

    @Override
    public List<TbItemExtend> showOrderCart(List<String> ids, String cookieValue) {
        String jsonUser = HttpClientUtil.doPost(passportUrl + cookieValue);
        EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
        String key = cartKey + ((LinkedHashMap) er.getData()).get("username");

        String cartJson = jedisClusterDao.get(key);
        Map<String, TbItemExtend> cartCollection = JsonUtils.jsonToPojo(cartJson, Map.class);

        List<TbItemExtend> itemExtends = new ArrayList<>();
        for (String id : ids) {
            if (cartCollection.containsKey(id)) {
                String itemExtendJson = JsonUtils.objectToJson(cartCollection.get(String.valueOf(id)));
                TbItemExtend itemExtend = JsonUtils.jsonToPojo(itemExtendJson, TbItemExtend.class);

                TbItem item = itemDubboService.selectById(Long.valueOf(id));
                itemExtend.setStock(item.getNum());
                itemExtend.setEnough(item.getNum() >= itemExtend.getNum());

                itemExtends.add(itemExtend);
            }
        }

        return itemExtends;
    }

    @Override
    public EgoResult createOrder(OrderParam param, String cookieValue) {

        long orderId = IDUtils.genItemId();
        Date date = new Date();
        //订单表数据
        TbOrder order = new TbOrder();
        order.setPayment(param.getPayment());
        order.setPaymentType(param.getPaymentType());
        order.setOrderId(String.valueOf(orderId));
        order.setCreateTime(date);
        order.setUpdateTime(date);

        String result = HttpClientUtil.doPost(passportUrl + cookieValue);
        EgoResult er = JsonUtils.jsonToPojo(result, EgoResult.class);
        Map user = (LinkedHashMap) er.getData();
        order.setUserId(Long.parseLong(user.get("id").toString()));
        order.setBuyerNick(user.get("username").toString());
        order.setBuyerRate(0);

        //订单-商品表
        for (TbOrderItem item : param.getOrderItems()) {
            item.setId(String.valueOf(IDUtils.genItemId()));
            item.setOrderId(String.valueOf(orderId));
        }
        //收货人信息
        TbOrderShipping shipping = param.getOrderShipping();
        shipping.setOrderId(String.valueOf(orderId));
        shipping.setCreated(date);
        shipping.setUpdated(date);

        try {
            int index = orderDubboService.createOrder(order, param.getOrderItems(), shipping);
            if (index > 0) {

                String cartJson = jedisClusterDao.get(cartKey+user.get("username"));
                Map<String, TbItemExtend> cartCollection = JsonUtils.jsonToPojo(cartJson, Map.class);

                for (TbOrderItem item : param.getOrderItems()) {
                    if (cartCollection.containsKey(item.getItemId())) {
                        cartCollection.remove(item.getItemId());
                    }
                }

                jedisClusterDao.set(cartKey+user.get("username"),JsonUtils.objectToJson(cartCollection));

                return EgoResult.SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return EgoResult.FAILED;
    }
}
