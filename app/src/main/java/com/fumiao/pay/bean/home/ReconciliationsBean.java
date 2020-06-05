package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class ReconciliationsBean {
    String next_query_day;
    List<PaymentListBean> history_payment_list;
    private int is_end;

    public String getNextQueryDay() {
        return next_query_day;
    }

    public void setNextQueryDay(String next_query_day) {
        this.next_query_day = next_query_day;
    }

    public List<PaymentListBean> getHistoryPaymentList() {
        return history_payment_list;
    }

    public void setHistoryPaymentList(List<PaymentListBean> history_payment_list) {
        this.history_payment_list = history_payment_list;
    }

    public int getIsEnd() {
        return is_end;
    }

    public void setIsEnd(int is_end) {
        this.is_end = is_end;
    }

    public static class PaymentListBean  extends CoreBean{
        private String day;
        private double total_amount;
        private int total_orders;
        private List<DataBean> list;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public double getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(double total_amount) {
            this.total_amount = total_amount;
        }

        public int getTotal_orders() {
            return total_orders;
        }

        public void setTotal_orders(int total_orders) {
            this.total_orders = total_orders;
        }

        public List<DataBean> getList() {
            return list;
        }

        public void setList(List<DataBean> list) {
            this.list = list;
        }
    }

    public static class DataBean  extends CoreBean {
        private int id;
        private String pay_orderid;
        private String pay_amount;
        private String pay_successdate;
        private String paytype_name;
        private String paytype_icon;
        private String cashier_name;
        private String store_name;
        private int order_number ;
        private double order_money;
        private int order_type; //订单类型 1:收款订单 2:退款订单
        private int pay_status; // 0：等待支付 1：收款成功 2：收款失败 3：退款订单
        private int is_refund; //是否有退款 1是 0否
        private String refund_time; //退款时间
        private double refund_money; //退款金额
        private String refund_org_pay_orderid; //退款id
        private String pay_applydate; //支付时间

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPay_orderid() {
            return pay_orderid;
        }

        public void setPay_orderid(String pay_orderid) {
            this.pay_orderid = pay_orderid;
        }

        public String getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(String pay_amount) {
            this.pay_amount = pay_amount;
        }

        public String getPay_successdate() {
            return pay_successdate;
        }

        public void setPay_successdate(String pay_successdate) {
            this.pay_successdate = pay_successdate;
        }

        public String getPaytype_name() {
            return paytype_name;
        }

        public void setPaytype_name(String paytype_name) {
            this.paytype_name = paytype_name;
        }

        public String getPaytype_icon() {
            return paytype_icon;
        }

        public void setPaytype_icon(String paytype_icon) {
            this.paytype_icon = paytype_icon;
        }

        public String getCashier_name() {
            return cashier_name;
        }

        public void setCashier_name(String cashier_name) {
            this.cashier_name = cashier_name;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public int getOrder_number() {
            return order_number;
        }

        public void setOrder_number(int order_number) {
            this.order_number = order_number;
        }

        public double getOrder_money() {
            return order_money;
        }

        public void setOrder_money(double order_money) {
            this.order_money = order_money;
        }

        public int getOrder_type() {
            return order_type;
        }

        public void setOrder_type(int order_type) {
            this.order_type = order_type;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public int getIs_refund() {
            return is_refund;
        }

        public void setIs_refund(int is_refund) {
            this.is_refund = is_refund;
        }

        public String getRefund_time() {
            return refund_time;
        }

        public void setRefund_time(String refund_time) {
            this.refund_time = refund_time;
        }

        public double getRefund_money() {
            return refund_money;
        }

        public void setRefund_money(double refund_money) {
            this.refund_money = refund_money;
        }

        public String getRefund_org_pay_orderid() {
            return refund_org_pay_orderid;
        }

        public void setRefund_org_pay_orderid(String refund_org_pay_orderid) {
            this.refund_org_pay_orderid = refund_org_pay_orderid;
        }

        public String getPay_applydate() {
            return pay_applydate;
        }

        public void setPay_applydate(String pay_applydate) {
            this.pay_applydate = pay_applydate;
        }
    }

}
