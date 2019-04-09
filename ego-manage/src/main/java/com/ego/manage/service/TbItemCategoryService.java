package com.ego.manage.service;

import com.ego.commons.domain.EasyUITree;

import java.util.List;

/**
 * 商品类目管理服务
 * @author boge.peng
 * @create 2019-03-12 13:42
 */
public interface TbItemCategoryService {
    List<EasyUITree> showItemCategoryByPid(long pid);
}
