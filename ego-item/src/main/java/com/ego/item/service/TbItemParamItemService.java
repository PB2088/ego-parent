package com.ego.item.service;

import com.ego.domain.TbItemParamItem;

/**
 * 商品参数服务
 * @author boge.peng
 * @create 2019-03-16 23:45
 */
public interface TbItemParamItemService {
    /**
     * 根据商品id查询商品参数数据
     * @param id
     * @return
     */
    TbItemParamItem selectByItemId(long id);

    /**
     * 显示商品规格参数
     * @param itemId
     * @return
     */
    String showItemParam(long itemId);
}
