package com.ego.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cache.dao.JedisClusterDao;
import com.ego.commons.utils.CommonFunctions;
import com.ego.commons.utils.JsonUtils;
import com.ego.domain.TbItemParamItem;
import com.ego.dubbo.service.TbItemParamItemDubboService;
import com.ego.item.domain.ParamItem;
import com.ego.item.service.TbItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品参数服务实现类
 * @author boge.peng
 * @create 2019-03-16 23:45
 */
@Service
public class TbItemParamItemServiceImpl implements TbItemParamItemService {

    @Reference
    private TbItemParamItemDubboService itemParamItemDubboService;

    @Autowired
    private JedisClusterDao jedisClusterDao;

    @Value("${redis.item.item.param.key}")
    private String itemParamkey;

    @Override
    public TbItemParamItem selectByItemId(long itemId) {
        return itemParamItemDubboService.selectByItemId(itemId);
    }

    @Override
    public String showItemParam(long id) {
        String key = itemParamkey+id;
        if (jedisClusterDao.exists(key)) {
            String value = jedisClusterDao.get(key);
            if (CommonFunctions.notEmpty(value)) {
                return value;
            }
        }

        TbItemParamItem itemParamItem = selectByItemId(id);
        List<ParamItem> list = JsonUtils.jsonToList(itemParamItem.getParamData(), ParamItem.class);

        StringBuffer sf = new StringBuffer();
        for (ParamItem param : list) {
            sf.append("<table width='500' style='color:gray;'>");
            for (int i = 0 ;i<param.getParams().size();i++) {
                if(i==0){
                    sf.append("<tr>");
                    sf.append("<td align='right' width='30%'>"+param.getGroup()+"</td>");
                    sf.append("<td align='right' width='30%'>"+param.getParams().get(i).getK()+"</td>");
                    sf.append("<td>"+param.getParams().get(i).getV()+"</td>");
                    sf.append("<tr/>");
                }else{
                    sf.append("<tr>");
                    sf.append("<td> </td>");
                    sf.append("<td align='right'>"+param.getParams().get(i).getK()+"</td>");
                    sf.append("<td>"+param.getParams().get(i).getV()+"</td>");
                    sf.append("</tr>");
                }
            }
            sf.append("</table>");
            sf.append("<hr style='color:gray;'/>");
        }

        jedisClusterDao.set(key,sf.toString());

        return sf.toString();
    }
}
