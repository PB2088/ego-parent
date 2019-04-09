package com.ego.dubbo.service;

import com.ego.domain.TbItemDesc;

/**
 * 商品描述服务
 * @author boge.peng
 * @create 2019-03-16 0:44
 */
public interface TbItemDescDubboService {

    /**
     * 根据商品ID查询商品描述
     * @param itemId
     * @return
     */
    TbItemDesc selectByItemId(long itemId);

}
