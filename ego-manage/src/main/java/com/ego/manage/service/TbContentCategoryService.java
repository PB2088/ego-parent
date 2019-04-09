package com.ego.manage.service;

import com.ego.commons.domain.EasyUITree;
import com.ego.commons.domain.EgoResult;
import com.ego.domain.TbContentCategory;

import java.util.List;

/**
 * 网站内容分类管理服务
 * @author boge.peng
 * @create 2019-03-13 22:23
 */
public interface TbContentCategoryService {
    /**
     * 查询所有类目并转换为easyui tree的属性要求
     * @param id
     * @return
     */
    List<EasyUITree> showContentCategory(long id);

    /**
     * 新增网站内容分类
     * @param contentCategory
     * @return
     */
    EgoResult create(TbContentCategory contentCategory);

    /**
     * 更新网站内容分类
     * @param contentCategory
     * @return
     */
    EgoResult update(TbContentCategory contentCategory);

    /**
     * 删除网站内容分类
     * @param contentCategory
     * @return
     */
    EgoResult delete(TbContentCategory contentCategory);
}
