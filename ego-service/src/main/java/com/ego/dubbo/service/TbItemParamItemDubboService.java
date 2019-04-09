package com.ego.dubbo.service;

import com.ego.domain.TbItemParamItem;

/**
 * 商品参数Dubbo服务
 * @author boge.peng
 * @create 2019-03-16 23:48
 */
public interface TbItemParamItemDubboService {
    /**
     * 根据商品id查询商品参数数据
     * @param itemId
     * @return
     */
    TbItemParamItem selectByItemId(long itemId);
}
