package com.ego.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.commons.domain.EgoResult;
import com.ego.domain.TbItemParam;
import com.ego.dubbo.service.TbItemCategoryDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.manage.domain.TbItemParamChild;
import com.ego.manage.service.TbItemParamService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author boge.peng
 * @create 2019-03-13 10:19
 */
@Service
public class TbItemParamServiceImpl implements TbItemParamService {

    @Reference
    private TbItemParamDubboService itemParamDubboService;

    @Reference
    private TbItemCategoryDubboService itemCategoryDubboService;

    @Override
    public EasyUIDataGrid selectItemParamListForPage(int page, int rows) {
        EasyUIDataGrid easyUIDataGrid = itemParamDubboService.selectItemParamListForPage(page, rows);
        List<TbItemParam> oldItemParams = (List<TbItemParam>) easyUIDataGrid.getRows();

        List<TbItemParam> newItemParams = new ArrayList<>();
        for (TbItemParam param : oldItemParams) {
            TbItemParamChild newItemParam = new TbItemParamChild();
            BeanUtils.copyProperties(param,newItemParam);
            newItemParam.setItemCatName(itemCategoryDubboService.selectById(param.getItemCatId()).getName());

            newItemParams.add(newItemParam);
        }

        easyUIDataGrid.setRows(newItemParams);

        return easyUIDataGrid;
    }

    @Override
    public int deletByIds(String ids) throws Exception {
        return itemParamDubboService.deletByIds(ids);
    }

    @Override
    public EgoResult selectByItemCatId(long itemCatId) {
        TbItemParam itemParam = itemParamDubboService.selectByItemCatId(itemCatId);

        if (!Objects.equals(itemParam,null)) {
            return EgoResult.SUCCESS.setData(itemParam);
        }

        return EgoResult.FAILED;
    }

    @Override
    public EgoResult saveItemParam(TbItemParam param) {
        Date currentDate = new Date();
        param.setCreated(currentDate);
        param.setUpdated(currentDate);

        int index = itemParamDubboService.saveItemParam(param);
        if (index > 0) {
            return EgoResult.SUCCESS;
        }
        return EgoResult.FAILED;
    }
}
