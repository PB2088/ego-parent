package com.ego.item.service;

import com.ego.domain.TbItemDesc;

/**
 * 商品描述服务
 * @author boge.peng
 * @create 2019-03-16 16:08
 */
public interface TbItemDescService {
    /**
     * 根据商品ID查询商品描述
     * @param itemId
     * @return
     */
    TbItemDesc selectByItemId(long itemId);
}
