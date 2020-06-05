package com.fumiao.pay.bean.main;

public class PaySuccessBean {


    /**
     * status : 3
     */

    private int status;
    private OrderInfoBean order_info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OrderInfoBean getOrder_info() {
        return order_info;
    }

    public void setOrder_info(OrderInfoBean order_info) {
        this.order_info = order_info;
    }

    public static class OrderInfoBean{
        private String payamount;  //收款金额
        private String payorderid; //订单编号
        private String pay_successdate; //订单时间

        public String getPayamount() {
            return payamount;
        }

        public void setPayamount(String payamount) {
            this.payamount = payamount;
        }

        public String getPayorderid() {
            return payorderid;
        }

        public void setPayorderid(String payorderid) {
            this.payorderid = payorderid;
        }

        public String getPay_successdate() {
            return pay_successdate;
        }

        public void setPay_successdate(String pay_successdate) {
            this.pay_successdate = pay_successdate;
        }
    }
}
