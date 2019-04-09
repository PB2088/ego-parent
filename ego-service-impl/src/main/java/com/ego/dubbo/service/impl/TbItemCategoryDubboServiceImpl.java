package com.ego.dubbo.service.impl;

import com.ego.commons.domain.EasyUITree;
import com.ego.domain.TbItemCat;
import com.ego.domain.TbItemCatExample;
import com.ego.dubbo.service.TbItemCategoryDubboService;
import com.ego.mapper.TbItemCatMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boge.peng
 * @create 2019-03-12 13:51
 */
public class TbItemCategoryDubboServiceImpl implements TbItemCategoryDubboService {

    @Autowired
    private TbItemCatMapper itemCatCategoryMapper;

    @Override
    public List<TbItemCat> showItemCategoryByPid(long pid) {
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(pid);

        List<TbItemCat> tbItemCategorys = itemCatCategoryMapper.selectByExample(example);

        return tbItemCategorys;
    }

    @Override
    public TbItemCat selectById(long id) {
        return itemCatCategoryMapper.selectByPrimaryKey(id);
    }
}
