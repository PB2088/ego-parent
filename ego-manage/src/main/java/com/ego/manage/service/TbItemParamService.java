package com.ego.manage.service;

import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.commons.domain.EgoResult;
import com.ego.domain.TbItemParam;

/**
 * 规格参数处理服务
 * @author boge.peng
 * @create 2019-03-13 10:16
 */
public interface TbItemParamService {

    /**
     * 规格参数分页
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGrid selectItemParamListForPage(int page, int rows);

    /**
     * 商品规格参数删除(支持批量处理)
     * @param ids(多个id,以","隔开)
     * @return
     * @throws Exception
     */
    int deletByIds(String ids) throws Exception;

    /**
     * 根据商品类目ID,查询商品规格参数
     * @param itemCatId
     * @return
     */
    EgoResult selectByItemCatId(long itemCatId);

    /**
     * 新增商品规格
     * @param param
     * @return
     */
    EgoResult saveItemParam(TbItemParam param);
}
