package com.ego.order.service;

import com.ego.commons.domain.EgoResult;
import com.ego.commons.domain.TbItemExtend;
import com.ego.order.domain.OrderParam;

import java.util.List;

/**
 * 订单服务接口
 * @author boge.peng
 * @create 2019-03-18 12:57
 */
public interface TbOrderService {
    /**
     * 确认订单信息包含的商品
     * @param ids
     * @return
     */
    List<TbItemExtend> showOrderCart(List<String> ids,String cookieValue);

    /**
     * 创建订单
     * @param param
     * @return
     */
    EgoResult createOrder(OrderParam param,String cookieValue);
}
