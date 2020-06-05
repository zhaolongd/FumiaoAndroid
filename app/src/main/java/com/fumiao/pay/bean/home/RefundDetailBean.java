package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

public class RefundDetailBean {

    private RefundInfoBean info;

    public RefundInfoBean getInfo() {
        return info;
    }

    public void setInfo(RefundInfoBean info) {
        this.info = info;
    }

    public static class RefundInfoBean  extends CoreBean {
        private int cashier_id;
        private int member_id;
        private String pay_type_name; // 退款方式
        private String store_name;  //退款门店
        private String member_name; //店员名称
        private String cashier_name; //收银员名称
        private int refund_time; //退款时间
        private double money; //退款金额
        private int pay_type;
        private String pay_orderid;
        private String refund_orderid;
        private String status_name;

        public int getCashier_id() {
            return cashier_id;
        }

        public void setCashier_id(int cashier_id) {
            this.cashier_id = cashier_id;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getPay_type_name() {
            return pay_type_name;
        }

        public void setPay_type_name(String pay_type_name) {
            this.pay_type_name = pay_type_name;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getCashier_name() {
            return cashier_name;
        }

        public void setCashier_name(String cashier_name) {
            this.cashier_name = cashier_name;
        }

        public int getRefund_time() {
            return refund_time;
        }

        public void setRefund_time(int refund_time) {
            this.refund_time = refund_time;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public String getPay_orderid() {
            return pay_orderid;
        }

        public void setPay_orderid(String pay_orderid) {
            this.pay_orderid = pay_orderid;
        }

        public String getRefund_orderid() {
            return refund_orderid;
        }

        public void setRefund_orderid(String refund_orderid) {
            this.refund_orderid = refund_orderid;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }
    }
}
