package com.ego.dubbo.service.impl;

import com.ego.commons.utils.CommonFunctions;
import com.ego.domain.TbItemParamItem;
import com.ego.domain.TbItemParamItemExample;
import com.ego.dubbo.service.TbItemParamItemDubboService;
import com.ego.mapper.TbItemParamItemMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 商品参数服务
 * @author boge.peng
 * @create 2019-03-16 23:49
 */
public class TbItemParamItemDubboServiceImpl implements TbItemParamItemDubboService {

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItemParamItem selectByItemId(long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        example.createCriteria().andItemIdEqualTo(itemId);

        List<TbItemParamItem> itemParamItems = itemParamItemMapper.selectByExampleWithBLOBs(example);

        return CommonFunctions.notEmpty(itemParamItems)?itemParamItems.get(0):TbItemParamItem.NULL;
    }
}
