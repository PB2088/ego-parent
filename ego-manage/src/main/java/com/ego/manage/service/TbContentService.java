package com.ego.manage.service;

import com.ego.commons.domain.EasyUIDataGrid;
import com.ego.commons.domain.EgoResult;
import com.ego.domain.TbContent;

/**
 * @author boge.peng
 * @create 2019-03-14 9:42
 */
public interface TbContentService {
    /**
     * 分页查询网站内容
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGrid selectContentListForPage(long categoryId, int page, int rows);

    /**
     * 网站内容新增
     * @param content
     * @return
     */
    EgoResult save(TbContent content);

    /**
     * 网站内容删除
     * @param ids
     * @return
     */
    EgoResult delete(String ids);
}
