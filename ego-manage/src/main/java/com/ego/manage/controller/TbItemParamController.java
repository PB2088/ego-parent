package com.ego.manage.controller;

import com.ego.commons.constant.RequestMappingConstant;
import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.commons.domain.EgoResult;
import com.ego.domain.TbItemParam;
import com.ego.manage.service.TbItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author boge.peng
 * @create 2019-03-13 10:20
 */
@Controller
public class TbItemParamController {

    @Autowired
    private TbItemParamService itemParamService;

    /**
     * 规格参数分页查询
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(RequestMappingConstant.Manage.ITEM_PARAM_LIST)
    @ResponseBody
    public EasyUIDataGrid showItemParamList(int page,int rows) {
        return itemParamService.selectItemParamListForPage(page,rows);
    }

    @RequestMapping(RequestMappingConstant.Manage.ITEM_PARAM_DELETE)
    @ResponseBody
    public EgoResult deleteItemParam(String ids) {
        try {
            int index = itemParamService.deletByIds(ids);
            if (index == 1) {
                return EgoResult.SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            EgoResult.FAILED.addData("errors",e.getMessage());
        }

        return  null;
    }

    @RequestMapping(RequestMappingConstant.Manage.ITEM_PARAM_QUERY_ITEMCATID)
    @ResponseBody
    public EgoResult queryItemParam(@PathVariable long catId) {
        return itemParamService.selectByItemCatId(catId);
    }

    @RequestMapping(RequestMappingConstant.Manage.ITEM_PARAM_SAVE)
    @ResponseBody
    public EgoResult saveItemParam(TbItemParam param,@PathVariable long catId) {
        param.setItemCatId(catId);

        return itemParamService.saveItemParam(param);
    }
}
