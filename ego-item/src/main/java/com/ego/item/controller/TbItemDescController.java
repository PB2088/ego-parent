package com.ego.item.controller;

import com.ego.commons.constant.RequestMappingConstant;
import com.ego.item.service.TbItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author boge.peng
 * @create 2019-03-16 16:04
 */
@Controller
public class TbItemDescController {

    @Autowired
    private TbItemDescService itemDescService;

    @RequestMapping(value = RequestMappingConstant.Item.SHOW_ITEM_DESC,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String showItemDesc(@PathVariable long id) {
        return itemDescService.selectByItemId(id).getItemDesc();
    }

}
