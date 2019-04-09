package com.ego.dubbo.service.impl;

import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.domain.TbItem;
import com.ego.domain.TbItemDesc;
import com.ego.domain.TbItemExample;
import com.ego.domain.TbItemParamItem;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.mapper.TbItemParamItemMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TbItemDubboServiceImpl implements TbItemDubboService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public EasyUIDataGrid selectItemListForPage(int page, int rows) {
        //进行分页处理
        PageHelper.startPage(page, rows);

        //查询全部商品
        List<TbItem> tbItems = tbItemMapper.selectByExample(new TbItemExample());
        PageInfo<TbItem> tbItemPageInfo = new PageInfo<>(tbItems);

        //封装分页后的数据
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setRows(tbItemPageInfo.getList());
        easyUIDataGrid.setTotal(tbItemPageInfo.getTotal());

        return easyUIDataGrid;
    }

    @Override
    public int updeItemStatusById(TbItem item) {
        return tbItemMapper.updateByPrimaryKeySelective(item);
    }

    @Override
    public int saveItem(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem) throws Exception {
        int index = 0;
        try {
            index = tbItemMapper.insertSelective(item);
            index += tbItemDescMapper.insertSelective(itemDesc);
            index += tbItemParamItemMapper.insertSelective(itemParamItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (index == 3) {
            return index;
        } else {
            throw new Exception("新增商品失败,数据还原!");
        }

    }

    @Override
    public List<TbItem> selectAllByStatus(byte status) {
        TbItemExample example = new TbItemExample();
        example.createCriteria().andStatusEqualTo(status);

        return tbItemMapper.selectByExample(example);
    }

    @Override
    public TbItem selectById(long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }
}
