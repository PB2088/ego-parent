package com.ego.dubbo.service;

import com.ego.domain.TbContentCategory;

import java.util.List;

/**
 * @author boge.peng
 * @create 2019-03-13 22:10
 */
public interface TbContentCategoryDubboService {
    /**
     * 根据父类ID查询所有的子类目
     * @param pid
     * @return
     */
    List<TbContentCategory> selectByPid(long pid);

    /**
     * 新增网站内容分类
     * @param contentCategory
     * @return
     */
    int insertContentCategory(TbContentCategory contentCategory);

    /**
     * 更新网站内容分类
     * @param contentCategory
     * @return
     */
    int updateContentCategoryById(TbContentCategory contentCategory);

    /**
     * 根据ID查询网站内容分类信息
     * @param id
     * @return
     */
    TbContentCategory selectById(long id);

    /**
     * 删除网站内容分类
     * @param contentCategory
     * @return
     */
    int deleteContentCategoryById(TbContentCategory contentCategory);
}
