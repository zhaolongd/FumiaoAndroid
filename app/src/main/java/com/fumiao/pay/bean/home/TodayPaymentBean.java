package com.fumiao.pay.bean.home;

public class TodayPaymentBean {
    private double order_money_sum; // 交易金额
    private int order_count; //交易笔数

    public double getOrder_money_sum() {
        return order_money_sum;
    }

    public void setOrder_money_sum(double order_money_sum) {
        this.order_money_sum = order_money_sum;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }
}
