package com.ego.dubbo.service;

import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.domain.TbContent;

import java.util.List;

/**
 * @author boge.peng
 * @create 2019-03-14 9:00
 */
public interface TbContentDubboService {
    /**
     * 分页查询网站内容
     * @param categoryId 网站内容类目id
     * @param page 当前页面
     * @param rows 每页显示记录数
     * @return
     */
    EasyUIDataGrid selectContentListForPage(long categoryId, int page, int rows);

    /**
     * 网站内容新增
     * @param content
     * @return
     */
    int save(TbContent content);

    /**
     * 网站内容删除(支持批量删除)
     * @param ids
     */
    void delete(String... ids);

    /**
     * 查询网站内容
     * @return
     */
    List<TbContent> selectByCount(long categoryId,int count, boolean isSort);
}
