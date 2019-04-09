package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cache.dao.JedisClusterDao;
import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.commons.domain.EgoResult;
import com.ego.commons.utils.CommonFunctions;
import com.ego.commons.utils.JsonUtils;
import com.ego.domain.TbContent;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.manage.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author boge.peng
 * @create 2019-03-14 9:42
 */
@Service
public class TbContentServiceImpl implements TbContentService {

    @Reference
    private TbContentDubboService contentDubboService;

    @Autowired
    private JedisClusterDao jedisCluster;

    @Value("${redis.portal.bigpic.key}")
    private String bigPicKey;

    @Override
    public EasyUIDataGrid selectContentListForPage(long categoryId, int page, int rows) {
        return contentDubboService.selectContentListForPage(categoryId, page, rows);
    }

    @Override
    public EgoResult save(TbContent content) {
        Date currentDate = new Date();
        content.setCreated(currentDate);
        content.setUpdated(currentDate);

        int index = contentDubboService.save(content);

        if (jedisCluster.exists(bigPicKey)) {
            String cacheValue = jedisCluster.get(bigPicKey);
            if (CommonFunctions.notEmpty(cacheValue)) {

                List<Map> maps = JsonUtils.jsonToList(cacheValue, Map.class);

                Map<String, Object> map = new HashMap<>();

                map.put("srcB", content.getPic2());
                map.put("height", 240);
                map.put("alt", "对不起,加载图片失败");
                map.put("width", 670);
                map.put("src", content.getPic());
                map.put("widthB", 550);
                map.put("href", content.getUrl());
                map.put("heightB", 240);

                //保证六个
                if (maps.size() == 6) {
                    maps.remove(5);
                }
                maps.add(0, map);

                jedisCluster.set(bigPicKey, JsonUtils.objectToJson(maps));
            }
        }

        if (index == 1) {
            return EgoResult.SUCCESS;
        }
        return EgoResult.FAILED;
    }

    @Override
    public EgoResult delete(String ids) {
        String[] idArr = ids.split(",");

        contentDubboService.delete(idArr);

        return EgoResult.SUCCESS;
    }
}
