package com.ego.dubbo.service.impl;

import com.ego.domain.TbOrder;
import com.ego.domain.TbOrderItem;
import com.ego.domain.TbOrderShipping;
import com.ego.dubbo.service.TbOrderDubboService;
import com.ego.mapper.TbOrderItemMapper;
import com.ego.mapper.TbOrderMapper;
import com.ego.mapper.TbOrderShippingMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 订单服务实现类
 *
 * @author boge.peng
 * @create 2019-03-18 14:36
 */
public class TbOrderDubboServiceImpl implements TbOrderDubboService {

    @Autowired
    private TbOrderMapper orderMapper;

    @Autowired
    private TbOrderItemMapper orderItemMapper;

    @Autowired
    private TbOrderShippingMapper orderShippingMapper;

    @Override
    public int createOrder(TbOrder order, List<TbOrderItem> orderItems, TbOrderShipping orderShipping) throws Exception {

        int index = orderMapper.insertSelective(order);
        for (TbOrderItem tbOrderItem : orderItems) {
            index += orderItemMapper.insertSelective(tbOrderItem);
        }
        index += orderShippingMapper.insertSelective(orderShipping);
        if (index == 2 + orderItems.size()) {
            return 1;
        } else {
            throw new Exception("创建订单失败");
        }

    }
}
