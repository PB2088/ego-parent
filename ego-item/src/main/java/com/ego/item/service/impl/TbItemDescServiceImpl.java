package com.ego.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cache.dao.JedisClusterDao;
import com.ego.commons.utils.CommonFunctions;
import com.ego.commons.utils.JsonUtils;
import com.ego.domain.TbItemDesc;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.item.service.TbItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisSentinelPool;

import javax.smartcardio.CommandAPDU;

/**
 * 商品描述服务类
 *
 * @author boge.peng
 * @create 2019-03-16 16:08
 */
@Service
public class TbItemDescServiceImpl implements TbItemDescService {

    @Reference
    private TbItemDescDubboService itemDescDubboService;

    @Autowired
    private JedisClusterDao jedisClusterDao;

    @Value("${redis.item.item.desc.key}")
    private String itemDesckey;

    @Override
    public TbItemDesc selectByItemId(long itemId) {
        String key = itemDesckey + itemId;
        if (jedisClusterDao.exists(key)) {
            String jsonValue = jedisClusterDao.get(key);
            if (CommonFunctions.notEmpty(jsonValue)) {
                return JsonUtils.jsonToPojo(jsonValue,TbItemDesc.class);
            }
        }

        TbItemDesc itemDesc = itemDescDubboService.selectByItemId(itemId);
        jedisClusterDao.set(key,JsonUtils.objectToJson(itemDesc));

        return itemDesc;
    }
}
