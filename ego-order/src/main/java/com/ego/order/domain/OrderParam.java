package com.ego.order.domain;

import com.ego.domain.TbOrderItem;
import com.ego.domain.TbOrderShipping;

import java.io.Serializable;
import java.util.List;

/**
 * @author boge.peng
 * @create 2019-03-18 14:29
 */
public class OrderParam implements Serializable{
    private static final long serialVersionUID = 2488333252021570513L;

    private int paymentType;

    private List<TbOrderItem> orderItems;

    private String payment;

    private TbOrderShipping orderShipping;

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
