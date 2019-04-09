package com.ego.manage.controller;

import com.ego.commons.constant.RequestMappingConstant;
import com.ego.commons.domain.EasyUITree;
import com.ego.manage.service.TbItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author boge.peng
 * @create 2019-03-12 13:11
 */
@Controller
public class TbItemCategoryController {

    @Autowired
    private TbItemCategoryService itemCategoryService;

    @RequestMapping(RequestMappingConstant.Manage.ITEM_CAT_LIST)
    @ResponseBody
    public List<EasyUITree> showItemCategoryList(@RequestParam(defaultValue = "0") Long id) {
        return itemCategoryService.showItemCategoryByPid(id);
    }

}
