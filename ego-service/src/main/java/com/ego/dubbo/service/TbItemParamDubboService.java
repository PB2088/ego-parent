package com.ego.dubbo.service;

import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.domain.TbItemParam;

/**
 * @author boge.peng
 * @create 2019-03-13 10:08
 */
public interface TbItemParamDubboService {
    /**
     * 规格参数分页
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGrid selectItemParamListForPage(int page, int rows);

    /**
     * 删除规格参数(支持批量处理)
     * @param ids (多个id以",")
     * @return
     * @throws Exception
     */
    int deletByIds(String ids) throws Exception;

    /**
     * 根据商品类目Id查询商品规格参数
     * @param itemCatId
     * @return
     */
    TbItemParam selectByItemCatId(long itemCatId);

    /**
     * 新增商品规格
     * @param param
     * @return
     */
    int saveItemParam(TbItemParam param);
}
