package com.ego.item.service;

import com.ego.commons.domain.TbItemExtend;

/**
 * @author boge.peng
 * @create 2019-03-16 14:51
 */
public interface TbItemService {
    /**
     * 根据商品ID查询商品信息
     * @param id
     * @return
     */
    TbItemExtend selectById(long id);
}
