package com.ego.portal.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cache.dao.JedisClusterDao;
import com.ego.commons.utils.CommonFunctions;
import com.ego.commons.utils.JsonUtils;
import com.ego.domain.TbContent;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.portal.service.TbPortalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author boge.peng
 * @create 2019-03-14 22:00
 */
@Service
public class TbPortalContentServiceImpl implements TbPortalContentService {

    @Reference
    private TbContentDubboService contentDubboService;

    @Autowired
    private JedisClusterDao jedisCluster;

    @Value("${redis.portal.bigpic.key}")
    private String bigPicKey;

    @Override
    public String showBigPic() {

        if (jedisCluster.exists(bigPicKey)) {
            String resutl = jedisCluster.get(bigPicKey);
            if (CommonFunctions.notEmpty(resutl)) {
                return resutl;
            }
        }

        List<TbContent> contents = contentDubboService.selectByCount(89,6, true);

        List<Map<String,Object>> listResult = new ArrayList<>();
        for (TbContent tc : contents) {
            Map<String,Object> map = new HashMap<>();

            map.put("srcB", tc.getPic2());
            map.put("height", 240);
            map.put("alt", "对不起,加载图片失败");
            map.put("width", 670);
            map.put("src", tc.getPic());
            map.put("widthB", 550);
            map.put("href", tc.getUrl() );
            map.put("heightB", 240);

            listResult.add(map);
        }

        String jsonValue = JsonUtils.objectToJson(listResult);
        jedisCluster.set(bigPicKey,jsonValue);

        return jsonValue;
    }
}
