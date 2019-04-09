package com.ego.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cache.dao.JedisClusterDao;
import com.ego.commons.domain.TbItemExtend;
import com.ego.commons.utils.CommonFunctions;
import com.ego.commons.utils.JsonUtils;
import com.ego.domain.TbItem;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.item.service.TbItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author boge.peng
 * @create 2019-03-16 14:51
 */
@Service
public class TbItemServiceImpl implements TbItemService {

    @Reference
    private TbItemDubboService itemDubboService;

    @Autowired
    private JedisClusterDao jedisClusterDao;

    @Value("${redis.item.item.detail.key}")
    private String itemDetailkey;

    @Override
    public TbItemExtend selectById(long id) {
        String key = itemDetailkey+id;
        if (jedisClusterDao.exists(key)) {
            String itemDetailJson = jedisClusterDao.get(itemDetailkey);
            if (CommonFunctions.notEmpty(itemDetailJson)) {
                return JsonUtils.jsonToPojo(itemDetailJson,TbItemExtend.class);
            }
        }

        TbItem tbItem = itemDubboService.selectById(id);
        TbItemExtend itemExtend = new TbItemExtend();
        BeanUtils.copyProperties(tbItem,itemExtend);

        itemExtend.setImages(CommonFunctions.notEmpty(tbItem.getImage())?tbItem.getImage().split(","):new String[1]);

        jedisClusterDao.set(key,JsonUtils.objectToJson(itemExtend));

        return itemExtend;
    }
}
