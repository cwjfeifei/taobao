package com.xbrc.cerlibrary.taobao.bean;

import java.util.ArrayList;
import java.util.List;

public class TaoBaoOrderListBean {
    private String seller_shopName;//店铺名称
    private String orderInfo_id;//订单ID
    private String orderInfo_createTime;//订单创建时间
    private String payInfo_actualFee;//订单实际付款金额
    private String statusInfo_text;//订单状态
    private List<TaoBaoOrderInfoListBean.MainOrdersBean.SubOrdersBean> itemBeanList = new ArrayList<>();//购买商品列表

    public String getSeller_shopName() {
        return seller_shopName;
    }

    public void setSeller_shopName(String seller_shopName) {
        this.seller_shopName = seller_shopName;
    }

    public String getOrderInfo_id() {
        return orderInfo_id;
    }

    public void setOrderInfo_id(String orderInfo_id) {
        this.orderInfo_id = orderInfo_id;
    }

    public String getOrderInfo_createTime() {
        return orderInfo_createTime;
    }

    public void setOrderInfo_createTime(String orderInfo_createTime) {
        this.orderInfo_createTime = orderInfo_createTime;
    }

    public String getPayInfo_actualFee() {
        return payInfo_actualFee;
    }

    public void setPayInfo_actualFee(String payInfo_actualFee) {
        this.payInfo_actualFee = payInfo_actualFee;
    }

    public String getStatusInfo_text() {
        return statusInfo_text;
    }

    public void setStatusInfo_text(String statusInfo_text) {
        this.statusInfo_text = statusInfo_text;
    }

    public List<TaoBaoOrderInfoListBean.MainOrdersBean.SubOrdersBean> getItemBeanList() {
        return itemBeanList;
    }

    public void setItemBeanList(List<TaoBaoOrderInfoListBean.MainOrdersBean.SubOrdersBean> itemBeanList) {
        this.itemBeanList = itemBeanList;
    }

    @Override
    public String toString() {
        return "TaoBaoOrderListBean{" +
                "seller_shopName='" + seller_shopName + '\'' +
                ", orderInfo_id='" + orderInfo_id + '\'' +
                ", orderInfo_createTime='" + orderInfo_createTime + '\'' +
                ", payInfo_actualFee='" + payInfo_actualFee + '\'' +
                ", statusInfo_text='" + statusInfo_text + '\'' +
                ", itemBeanList=" + itemBeanList +
                '}';
    }
}
