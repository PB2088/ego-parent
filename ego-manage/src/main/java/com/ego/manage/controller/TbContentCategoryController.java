package com.ego.manage.controller;

import com.ego.commons.constant.RequestMappingConstant;
import com.ego.commons.domain.EasyUITree;
import com.ego.commons.domain.EgoResult;
import com.ego.domain.TbContentCategory;
import com.ego.manage.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author boge.peng
 * @create 2019-03-13 22:38
 */
@Controller
public class TbContentCategoryController {
    @Autowired
    private TbContentCategoryService contentCategoryService;

    @RequestMapping(RequestMappingConstant.Manage.CONTENT_CATEGORY_LIST)
    @ResponseBody
    public List<EasyUITree> showContentCategory(@RequestParam(defaultValue = "0") long id) {
        return contentCategoryService.showContentCategory(id);
    }

    @RequestMapping(RequestMappingConstant.Manage.CONTENT_CATEGORY_CREATE)
    @ResponseBody
    public EgoResult create(TbContentCategory contentCategory) {
        return contentCategoryService.create(contentCategory);
    }

    @RequestMapping(RequestMappingConstant.Manage.CONTENT_CATEGORY_UPDATE)
    @ResponseBody
    public EgoResult update(TbContentCategory contentCategory) {
        return contentCategoryService.update(contentCategory);
    }

    @RequestMapping(RequestMappingConstant.Manage.CONTENT_CATEGORY_DELETE)
    @ResponseBody
    public EgoResult delete(TbContentCategory contentCategory) {
        return contentCategoryService.delete(contentCategory);
    }

}
