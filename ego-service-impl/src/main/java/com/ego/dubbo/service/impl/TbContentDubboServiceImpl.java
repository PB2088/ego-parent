package com.ego.dubbo.service.impl;

import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.domain.TbContent;
import com.ego.domain.TbContentExample;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.mapper.TbContentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author boge.peng
 * @create 2019-03-14 9:00
 */
public class TbContentDubboServiceImpl implements TbContentDubboService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private SqlSession batchSession;

    @Override
    public EasyUIDataGrid selectContentListForPage(long categoryId, int page, int rows) {
        PageHelper.startPage(page,rows);

        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> contents = contentMapper.selectByExampleWithBLOBs(example);

        PageInfo<TbContent> pageInfo = new PageInfo<>(contents);

        //封装分页后的数据
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setRows(pageInfo.getList());
        easyUIDataGrid.setTotal(pageInfo.getTotal());

        return easyUIDataGrid;
    }

    @Override
    public int save(TbContent content) {
        return contentMapper.insertSelective(content);
    }

    @Override
    public void delete(String... ids) {
        TbContentMapper mapper = batchSession.getMapper(TbContentMapper.class);
        for (String id : ids) {
            mapper.deleteByPrimaryKey(Long.valueOf(id));
        }
    }

    @Override
    public List<TbContent> selectByCount(long categoryId,int count, boolean isSort) {
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        if (isSort) {
            example.setOrderByClause("updated desc");
        }
        if (count != 0) {
            PageHelper.startPage(1,count);
            List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
            PageInfo<TbContent>  pageInfo = new PageInfo<>(list);
            return pageInfo.getList();
        } else {
            return contentMapper.selectByExampleWithBLOBs(example);
        }
    }
}
