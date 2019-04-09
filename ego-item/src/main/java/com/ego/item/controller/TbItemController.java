package com.ego.item.controller;

import com.ego.commons.constant.RequestMappingConstant;
import com.ego.item.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 商品处理控制器
 * @author boge.peng
 * @create 2019-03-16 15:02
 */
@Controller
public class TbItemController {
    @Autowired
    private TbItemService itemService;

    @RequestMapping(RequestMappingConstant.Item.SHOW_ITEM_DETAIL)
    public String showItemDetails(@PathVariable long id, Map<String,Object> model) {

        model.put("item",itemService.selectById(id));

        return "item";
    }
}
