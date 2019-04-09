package com.ego.dubbo.service;

import com.ego.domain.TbOrder;
import com.ego.domain.TbOrderItem;
import com.ego.domain.TbOrderShipping;

import java.util.List;

/**
 * 订单服务
 * @author boge.peng
 * @create 2019-03-18 14:36
 */
public interface TbOrderDubboService {
    /**
     * 创建订单
     * @param order
     * @param orderItems
     * @param orderShipping
     * @return
     */
    public int createOrder(TbOrder order, List<TbOrderItem> orderItems, TbOrderShipping orderShipping) throws Exception;
}
