package com.ego.dubbo.service;

import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.domain.TbItem;
import com.ego.domain.TbItemDesc;
import com.ego.domain.TbItemParamItem;

import java.util.List;

public interface TbItemDubboService {
    /**
     * 商品分页查询
     * @return
     */
    EasyUIDataGrid selectItemListForPage(int page,int rows);

    /**
     * 根据商品Id,更新商品状态
     * @param item
     * @return
     */
    int updeItemStatusById(TbItem item);

    /**
     * 新增商品
     * @param item 商品数据
     * @param itemDesc 商品描述
     * @param itemParamItem 商品规格
     * @return
     * @throws Exception
     */
    int saveItem(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem) throws Exception;

    /**
     * 根据状态查询所有商品
     * @param status
     * @return
     */
    List<TbItem> selectAllByStatus(byte status);

    /**
     * 根据商品id查询商品详情
     * @param id
     * @return
     */
    TbItem selectById(long id);
}
