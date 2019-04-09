package com.ego.dubbo.service.impl;

import com.ego.domain.TbItemDesc;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.mapper.TbItemDescMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品描述服务实现类
 * @author boge.peng
 * @create 2019-03-16 0:45
 */
public class TbItemDescDubboServiceImpl implements TbItemDescDubboService {

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Override
    public TbItemDesc selectByItemId(long itemId) {
        return itemDescMapper.selectByPrimaryKey(itemId);
    }
}
