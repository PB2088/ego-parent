package com.ego.manage.service;

import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.domain.TbItem;

/**
 * @author boge.peng
 * @create 2019-03-12 0:44
 */
public interface TbItemService {
    /**
     * 分页查询商品列表
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGrid selectItemListForPage(int page, int rows);

    /**
     * 更新商品状态
     * @param ids 商品编号字符串(支持批量处理,id以","隔开)
     * @param status 商品状态:1-正常,2-下架,3-删除
     * @return
     */
    int updateItemStatusById(String ids,byte status);

    /**
     * 新增商品
     * @param tbItem
     * @param desc
     * @return
     */
    int saveItem(TbItem tbItem, String desc,String itemParams) throws Exception;
}
