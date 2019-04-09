package com.ego.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cache.dao.JedisClusterDao;
import com.ego.commons.utils.CommonFunctions;
import com.ego.commons.utils.JsonUtils;
import com.ego.domain.TbItemCat;
import com.ego.dubbo.service.TbItemCategoryDubboService;
import com.ego.item.domain.PortalMen;
import com.ego.item.domain.PortalMenNode;
import com.ego.item.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boge.peng
 * @create 2019-03-13 21:30
 */
@Service
public class TbItemCatServiceImpl implements TbItemCatService {

    @Autowired
    private JedisClusterDao jedisCluster;

    @Reference
    private TbItemCategoryDubboService itemCategoryDubboService;

    @Value("${redis.item.portal.menu.key}")
    private String portMenuKey;

    @Override
    public PortalMen showPortalMenu() {

        if (jedisCluster.exists(portMenuKey)) {
            String portalMenuJson = jedisCluster.get(portMenuKey);
            if (CommonFunctions.notEmpty(portalMenuJson)) {
                return JsonUtils.jsonToPojo(portalMenuJson,PortalMen.class);
            }
        }

        //所有一级菜单
        List<TbItemCat> tbItemCats = itemCategoryDubboService.showItemCategoryByPid(0);

        PortalMen pm = new PortalMen();
        pm.setData(selectAllMenu(tbItemCats));

        jedisCluster.set(portMenuKey,JsonUtils.objectToJson(pm));

        return pm;
    }

    private List<Object> selectAllMenu(List<TbItemCat> tbItemCats) {
        List<Object> listNode = new ArrayList<>();
        for (TbItemCat itemCat : tbItemCats) {
            if(itemCat.getIsParent()) {
                PortalMenNode node = new PortalMenNode();
                node.setU("/products/"+itemCat.getId()+".html");
                node.setN("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
                node.setI(selectAllMenu(itemCategoryDubboService.showItemCategoryByPid(itemCat.getId())));
                listNode.add(node);
            } else {
                listNode.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());
            }
        }

        return listNode;
    }
}
