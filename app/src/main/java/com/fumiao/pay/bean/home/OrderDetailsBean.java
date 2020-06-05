package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;
import java.util.List;

public class OrderDetailsBean extends CoreBean {


    /**
     * payment : {"id":11,"pay_orderid":"QR20181227220594032254","pay_amount":"300.00","pay_actualamount":"300.00","pay_applydate":1545904220,"pay_successdate":1545904220,"pay_status":1,"transaction_id":"20181227220594032254","pay_productname":"","is_void":0,"refund_status":0,"refund_money":"0.00","paytype_name":"银联扫码","cashier_name":"","store_name":"测试门店","payer_name":"付款人","refund_enabled_money":"300.00","void_enabled":1,"refund_enabled":1}
     */
    private PaymentBean payment;
    private List<RefundOrderBean> refund_order_list;

    public List<RefundOrderBean> getRefund_order_list() {
        return refund_order_list;
    }

    public void setRefund_order_list(List<RefundOrderBean> refund_order_list) {
        this.refund_order_list = refund_order_list;
    }

    public PaymentBean getPayment() {
        return payment;
    }

    public void setPayment(PaymentBean payment) {
        this.payment = payment;
    }



    public static class PaymentBean extends CoreBean {
        /**
         * id : 11
         * pay_orderid : QR20181227220594032254
         * pay_amount : 300.00
         * pay_actualamount : 300.00
         * pay_applydate : 1545904220
         * pay_successdate : 1545904220
         * pay_status : 1
         * transaction_id : 20181227220594032254
         * pay_productname :
         * is_void : 0
         * refund_status : 0
         * refund_money : 0.00
         * paytype_name : 银联扫码
         * cashier_name :
         * store_name : 测试门店
         * payer_name : 付款人
         * refund_enabled_money : 300.00
         * void_enabled : 1
         * refund_enabled : 1
         */

        private int id;
        private String pay_orderid;
        private String pay_amount;
        private String refundable_amount;
        private String pay_actualamount;
        private int pay_applydate;
        private int pay_successdate;
        private int pay_status;
        private String transaction_id;
        private String pay_productname;
        private int is_void;
        private int refund_status;
        private double refund_money;
        private String paytype_name;
        private String cashier_name;
        private String store_name;
        private String payer_name;
        private String refund_enabled_money;
        private int void_enabled;
        private int refund_enabled;
        private String collection_account; //收款账户
        private String attach; //备注
        private String out_transaction_id; //外部单号：比如微信单号、支付宝单号....
        private int is_refund; //是否有退款 1是 0否
        private int refund_time; //退款时间
        private String pay_rate; //费率
        private String pay_poundage; //手续费


        public String getCollection_account() {
            return collection_account;
        }

        public void setCollection_account(String collection_account) {
            this.collection_account = collection_account;
        }

        public String getAttach() {
            return attach;
        }

        public void setAttach(String attach) {
            this.attach = attach;
        }

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

        public String getPay_actualamount() {
            return pay_actualamount;
        }

        public void setPay_actualamount(String pay_actualamount) {
            this.pay_actualamount = pay_actualamount;
        }

        public int getPay_applydate() {
            return pay_applydate;
        }

        public void setPay_applydate(int pay_applydate) {
            this.pay_applydate = pay_applydate;
        }

        public int getPay_successdate() {
            return pay_successdate;
        }

        public void setPay_successdate(int pay_successdate) {
            this.pay_successdate = pay_successdate;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getPay_productname() {
            return pay_productname;
        }

        public void setPay_productname(String pay_productname) {
            this.pay_productname = pay_productname;
        }

        public int getIs_void() {
            return is_void;
        }

        public void setIs_void(int is_void) {
            this.is_void = is_void;
        }

        public int getRefund_status() {
            return refund_status;
        }

        public void setRefund_status(int refund_status) {
            this.refund_status = refund_status;
        }

        public double getRefund_money() {
            return refund_money;
        }

        public void setRefund_money(double refund_money) {
            this.refund_money = refund_money;
        }

        public String getPaytype_name() {
            return paytype_name;
        }

        public void setPaytype_name(String paytype_name) {
            this.paytype_name = paytype_name;
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

        public String getPayer_name() {
            return payer_name;
        }

        public void setPayer_name(String payer_name) {
            this.payer_name = payer_name;
        }

        public String getRefund_enabled_money() {
            return refund_enabled_money;
        }

        public void setRefund_enabled_money(String refund_enabled_money) {
            this.refund_enabled_money = refund_enabled_money;
        }

        public int getVoid_enabled() {
            return void_enabled;
        }

        public void setVoid_enabled(int void_enabled) {
            this.void_enabled = void_enabled;
        }

        public int getRefund_enabled() {
            return refund_enabled;
        }

        public void setRefund_enabled(int refund_enabled) {
            this.refund_enabled = refund_enabled;
        }

        public String getOut_transaction_id() {
            return out_transaction_id;
        }

        public void setOut_transaction_id(String out_transaction_id) {
            this.out_transaction_id = out_transaction_id;
        }

        public int getIs_refund() {
            return is_refund;
        }

        public void setIs_refund(int is_refund) {
            this.is_refund = is_refund;
        }

        public int getRefund_time() {
            return refund_time;
        }

        public void setRefund_time(int refund_time) {
            this.refund_time = refund_time;
        }

        public String getPay_rate() {
            return pay_rate;
        }

        public void setPay_rate(String pay_rate) {
            this.pay_rate = pay_rate;
        }

        public String getPay_poundage() {
            return pay_poundage;
        }

        public void setPay_poundage(String pay_poundage) {
            this.pay_poundage = pay_poundage;
        }

        public String getRefundable_amount() {
            return refundable_amount;
        }

        public void setRefundable_amount(String refundable_amount) {
            this.refundable_amount = refundable_amount;
        }
    }
}
