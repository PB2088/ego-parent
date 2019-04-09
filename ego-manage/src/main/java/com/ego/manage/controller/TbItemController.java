package com.ego.manage.controller;

import com.ego.commons.constant.RequestMappingConstant;
import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.commons.domain.EgoResult;
import com.ego.domain.TbItem;
import com.ego.manage.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品管理控制器
 * @author boge.peng
 * @create 2019-03-12 0:41
 */
@Controller
public class TbItemController {

    @Autowired
    private TbItemService tbItemService;

    @ResponseBody
    @RequestMapping(RequestMappingConstant.Manage.ITEM_LIST)
    public EasyUIDataGrid showItemList(int page,int rows) {
        return tbItemService.selectItemListForPage(page,rows);
    }

    @RequestMapping(RequestMappingConstant.Manage.REST_PAGE_ITEM_EDIT)
    public String showItemEdit() {
        return "item-edit";
    }

    @ResponseBody
    @RequestMapping(RequestMappingConstant.Manage.REST_ITEM_DELETE)
    public EgoResult delete(String ids) {
        return updateItem(ids,"3");
    }

    @ResponseBody
    @RequestMapping(RequestMappingConstant.Manage.REST_ITEM_INSTOCK)
    public EgoResult instock(String ids) {
        return updateItem(ids,"2");
    }

    @ResponseBody
    @RequestMapping(RequestMappingConstant.Manage.REST_ITEM_RESHELF)
    public EgoResult reshelf(String ids) {
        return updateItem(ids,"1");
    }

    private EgoResult updateItem(String ids,String status) {
        int result = tbItemService.updateItemStatusById(ids, Byte.valueOf(status));

        return result == 1 ? EgoResult.SUCCESS : EgoResult.FAILED;
    }

    @RequestMapping(RequestMappingConstant.Manage.ITEM_SAVE)
    @ResponseBody
    public EgoResult saveItem(TbItem tbItem,String desc,String itemParams) {
        try {
            int index = tbItemService.saveItem(tbItem,desc,itemParams);
            if (index == 3) {
                return EgoResult.SUCCESS;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return EgoResult.FAILED.addData("errors",e.getMessage());
        }

        return null;
    }

}
