package com.ego.dubbo.service.impl;

import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.domain.TbItemParam;
import com.ego.domain.TbItemParamExample;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.mapper.TbItemParamMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author boge.peng
 * @create 2019-03-13 10:09
 */
public class TbItemParamDubboServiceImpl implements TbItemParamDubboService {

    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Autowired
    private SqlSession batchSession;

    @Override
    public EasyUIDataGrid selectItemParamListForPage(int page, int rows) {
        PageHelper.startPage(page,rows);

        List<TbItemParam> tbItemParams = itemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
        PageInfo<TbItemParam> paramPageInfo = new PageInfo<>(tbItemParams);

        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setRows(paramPageInfo.getList());
        easyUIDataGrid.setTotal(paramPageInfo.getTotal());

        return easyUIDataGrid;
    }

    @Override
    public int deletByIds(String ids) throws Exception {
        int index = 1;

        TbItemParamMapper batchMapper = batchSession.getMapper(TbItemParamMapper.class);
        String[] idArr = ids.split(",");

        try {
            for (String id : idArr) {
                batchMapper.deleteByPrimaryKey(Long.valueOf(id));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            index = 0;
        }

        if(index==1){
            return index;
        }else{
            throw new Exception("删除失败.可能原因:数据已经不存在!");
        }
    }

    @Override
    public TbItemParam selectByItemCatId(long itemCatId) {
        TbItemParamExample example = new TbItemParamExample();
        example.createCriteria().andItemCatIdEqualTo(itemCatId);

        List<TbItemParam> tbItemParams = itemParamMapper.selectByExampleWithBLOBs(example);

        if (tbItemParams !=null && !tbItemParams.isEmpty()) {
            return tbItemParams.get(0);
        }

        return null;
    }

    @Override
    public int saveItemParam(TbItemParam param) {
        return itemParamMapper.insert(param);
    }
}
