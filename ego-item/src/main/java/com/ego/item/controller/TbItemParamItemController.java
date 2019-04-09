package com.ego.item.controller;

import com.ego.commons.constant.RequestMappingConstant;
import com.ego.item.service.TbItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author boge.peng
 * @create 2019-03-17 0:10
 */
@Controller
public class TbItemParamItemController {

    @Autowired
    private TbItemParamItemService itemParamItemService;

    @RequestMapping(value = RequestMappingConstant.Item.SHOW_ITEM_PARAM,produces="text/html;charset=utf-8")
    @ResponseBody
    public String param(@PathVariable long id) {
        return itemParamItemService.showItemParam(id);
    }
}
