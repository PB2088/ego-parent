package com.ego.cart.controller;

import com.ego.cart.service.CartService;
import com.ego.commons.constant.RequestMappingConstant;
import com.ego.commons.domain.EgoResult;
import com.ego.commons.domain.TbItemExtend;
import com.ego.commons.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author boge.peng
 * @create 2019-03-17 16:08
 */
@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping(RequestMappingConstant.Cart.ADD_CART)
    public String addCart(@PathVariable long id, int num, HttpServletRequest request) {
        cartService.addCart(id,num,CookieUtils.getCookieValue(request, "TT_TOKEN"));
        return "cartSuccess";
    }

    @RequestMapping(RequestMappingConstant.Cart.SHOW_CART_CART)
    public String showCart(Map<String,List<TbItemExtend>> model,HttpServletRequest request) {
        model.put("cartList",cartService.showCart(CookieUtils.getCookieValue(request, "TT_TOKEN")));
        return "cart";
    }

    @RequestMapping(RequestMappingConstant.Cart.UPDATE_CART)
    @ResponseBody
    public EgoResult update(@PathVariable long id, @PathVariable int num,HttpServletRequest request) {
        return cartService.update(id,num,CookieUtils.getCookieValue(request, "TT_TOKEN"));
    }

    @RequestMapping(RequestMappingConstant.Cart.DELETE_CART)
    @ResponseBody
    public EgoResult delete(@PathVariable long id,HttpServletRequest request) {
        return cartService.delete(id,CookieUtils.getCookieValue(request, "TT_TOKEN"));
    }

}
