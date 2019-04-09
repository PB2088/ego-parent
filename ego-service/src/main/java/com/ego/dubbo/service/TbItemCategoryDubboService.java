package com.ego.dubbo.service;

import com.ego.commons.domain.EasyUITree;
import com.ego.domain.TbItemCat;

import java.util.List;

/**
 * 商品类目服务
 * @author boge.peng
 * @create 2019-03-12 13:49
 */
public interface TbItemCategoryDubboService {
    /**
     * 根据商品类目父ID,加载所有子类商品类目
     * @param pid 父类ID
     * @return
     */
    List<TbItemCat> showItemCategoryByPid(long pid);

    /**
     * 根据商品类目Id查询
     * @param id
     * @return
     */
    TbItemCat selectById(long id);
}
