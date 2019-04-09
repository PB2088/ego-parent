package com.ego.order.controller;

import com.ego.commons.constant.RequestMappingConstant;
import com.ego.commons.domain.EgoResult;
import com.ego.commons.domain.TbItemExtend;
import com.ego.commons.utils.CookieUtils;
import com.ego.order.domain.OrderParam;
import com.ego.order.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author boge.peng
 * @create 2019-03-18 12:10
 */
@Controller
public class OrderController {

    @Autowired
    private TbOrderService orderService;

    @RequestMapping(RequestMappingConstant.Order.SHOW_CART_ORDER)
    public String showCartOrder(Map<String, List<TbItemExtend>> model, @RequestParam("id") List<String> itemIds,
                                HttpServletRequest request) {
        System.out.println(itemIds);
        model.put("cartList", orderService.showOrderCart(itemIds, CookieUtils.getCookieValue(request, "TT_TOKEN")));

        return "order-cart";
    }

    @RequestMapping(RequestMappingConstant.Order.CREATE_ORDER)
    public String createOrder(OrderParam param,HttpServletRequest request) {
        EgoResult result = orderService.createOrder(param,CookieUtils.getCookieValue(request, "TT_TOKEN"));

        if(result.getStatus()==200){
            return "my-orders";
        }else{
            request.setAttribute("message", "订单创建失败");
            return "error/exception";
        }
    }
}
