package com.ego.cart.service;

import com.ego.commons.domain.EgoResult;
import com.ego.commons.domain.TbItemExtend;

import java.util.List;

/**
 * 购物车服务
 * @author boge.peng
 * @create 2019-03-17 17:21
 */
public interface CartService {
    /**
     * 添加商品到购物车
     * @param id
     * @param num
     */
    public void addCart(long id,int num,String cookieValue);

    /**
     * 显示购物车商品
     * @param cookieValue
     * @return
     */
    List<TbItemExtend> showCart(String cookieValue);

    /**
     * 更新购物车商品数量
     * @param id
     * @param num
     * @param cookieValue
     * @return
     */
    EgoResult update(long id, int num, String cookieValue);

    /**
     * 删除购物车商品
     * @param id
     * @param cookieValue
     * @return
     */
    EgoResult delete(long id, String cookieValue);
}
