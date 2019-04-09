package com.ego.manage.controller;

import com.ego.commons.constant.RequestMappingConstant;
import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.commons.domain.EgoResult;
import com.ego.domain.TbContent;
import com.ego.manage.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author boge.peng
 * @create 2019-03-14 9:46
 */
@Controller
public class TbContentController {

    @Autowired
    private TbContentService contentService;

    @RequestMapping(RequestMappingConstant.Manage.CONTENT_QUERY_LIST)
    @ResponseBody
    public EasyUIDataGrid showContent(long categoryId, int page, int rows) {
        return contentService.selectContentListForPage(categoryId,page,rows);
    }

    @RequestMapping(RequestMappingConstant.Manage.CONTENT_SAVE)
    @ResponseBody
    public EgoResult saveContent(TbContent content) {
        return contentService.save(content);
    }

    @RequestMapping(RequestMappingConstant.Manage.CONTENT_DELETE)
    @ResponseBody
    public EgoResult deleteContent(String ids) {
        return contentService.delete(ids);
    }
}
