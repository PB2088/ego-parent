package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cache.dao.JedisClusterDao;
import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.IDUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.commons.utils.ThreadPoolManager;
import com.ego.domain.TbItem;
import com.ego.domain.TbItemDesc;
import com.ego.domain.TbItemParamItem;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.manage.procedure.SolrSynchroHandler;
import com.ego.manage.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author boge.peng
 * @create 2019-03-12 0:45
 */
@Service
public class TbItemServiceImpl implements TbItemService {
    @Reference
    private TbItemDubboService itemDubboService;

    @Value("${search.add.url}")
    private String url;

    @Autowired
    private JedisClusterDao jedisClusterDao;

    @Value("${redis.item.item.detail.key}")
    private String itemDetailkey;

    @Override
    public EasyUIDataGrid selectItemListForPage(int page, int rows) {
        return itemDubboService.selectItemListForPage(page, rows);
    }

    @Override
    public int updateItemStatusById(String ids, byte status) {
        String[] idArr = ids.split(",");
        int index = 0;
        for (String id : idArr) {
            TbItem tbItem = new TbItem();
            tbItem.setId(Long.valueOf(id));
            tbItem.setStatus(status);
            tbItem.setUpdated(new Date());

            index += itemDubboService.updeItemStatusById(tbItem);

            if (status==2||status==3) {
                jedisClusterDao.del(itemDetailkey+id);
            }
        }

        return index == idArr.length ? 1 : 0;
    }

    @Override
    public int saveItem(TbItem tbItem, String desc,String itemParams) throws Exception {
        long id = IDUtils.genItemId();
        Date currentDate = new Date();

        tbItem.setId(id);
        tbItem.setCreated(currentDate);
        tbItem.setUpdated(currentDate);
        tbItem.setStatus((byte)1);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(id);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(currentDate);
        tbItemDesc.setUpdated(currentDate);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(id);
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setCreated(currentDate);
        tbItemParamItem.setUpdated(currentDate);

        //同步solr数据
        Map<String,Object> map = new HashMap<>();
        map.put("item", tbItem);
        map.put("desc", desc);
        ThreadPoolManager.getInstance().execute(SolrSynchroHandler.builder(url,map));

        jedisClusterDao.set(itemDetailkey+id,JsonUtils.objectToJson(tbItem));

        return itemDubboService.saveItem(tbItem,tbItemDesc,tbItemParamItem);
    }
}
