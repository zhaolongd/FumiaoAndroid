package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

/**
 * Created by chee on 2018/9/18.
 */
public class BillAnalysisBean {


    /**
     * sum : {"pay_amount_sum":"0.2100","pay_count":10}
     * date : [{"date":20180903,"pay_amount_sum":0.09,"pay_count":7},{"date":20180904,"pay_amount_sum":0.12,"pay_count":3}]
     * paytype : [{"paytype":0,"paytype_name":"","paytype_icon":"","pay_amount_sum":0.12,"pay_count":3},{"paytype":1,"paytype_name":"微信扫码","paytype_icon":"/static/common/paytype/weixin_ico.png","pay_amount_sum":0.02,"pay_count":2},{"paytype":2,"paytype_name":"微信H5","paytype_icon":"/static/common/paytype/weixin_ico.png","pay_amount_sum":0.01,"pay_count":1},{"paytype":3,"paytype_name":"支付宝扫码","paytype_icon":"/static/common/paytype/ali_ico.png","pay_amount_sum":0.01,"pay_count":1},{"paytype":4,"paytype_name":"支付宝H5","paytype_icon":"/static/common/paytype/ali_ico.png","pay_amount_sum":0.04,"pay_count":2},{"paytype":7,"paytype_name":"百度钱包","paytype_icon":"/static/common/paytype/baidu_ico.png","pay_amount_sum":0.01,"pay_count":1}]
     * member : [{"pay_cashierid":1,"cashier_name":"真事","cashier_image":"0c\\71480df849c385861e50927fefc108af759e53.png","pay_amount_sum":0.1,"pay_count":1},{"pay_cashierid":4,"cashier_name":"简单爱","cashier_image":"","pay_amount_sum":0.11,"pay_count":9}]
     */

    private SumBean sum;
    private RefundSumBean refund_sum;
    private List<DateBean> date;
    private List<PaytypeBean> paytype;
    private List<MemberBean> member;

    public SumBean getSum() {
        return sum;
    }

    public void setSum(SumBean sum) {
        this.sum = sum;
    }

    public List<DateBean> getDate() {
        return date;
    }

    public void setDate(List<DateBean> date) {
        this.date = date;
    }

    public List<PaytypeBean> getPaytype() {
        return paytype;
    }

    public void setPaytype(List<PaytypeBean> paytype) {
        this.paytype = paytype;
    }

    public List<MemberBean> getMember() {
        return member;
    }

    public void setMember(List<MemberBean> member) {
        this.member = member;
    }

    public RefundSumBean getRefundSum() {
        return refund_sum;
    }

    public void setRefundSum(RefundSumBean refund_sum) {
        this.refund_sum = refund_sum;
    }

    public static class SumBean {
        /**
         * pay_amount_sum : 0.2100
         * pay_count : 10
         *
         */

        private String pay_amount_sum;
        private int pay_count;

        public String getPay_amount_sum() {
            return pay_amount_sum;
        }

        public void setPay_amount_sum(String pay_amount_sum) {
            this.pay_amount_sum = pay_amount_sum;
        }

        public int getPay_count() {
            return pay_count;
        }

        public void setPay_count(int pay_count) {
            this.pay_count = pay_count;
        }
    }

    public static class RefundSumBean{
        private String refund_amount_sum;
        private int refund_count;

        public String getRefundAmountSum() {
            return refund_amount_sum;
        }

        public void setRefundAmountSum(String refund_amount_sum) {
            this.refund_amount_sum = refund_amount_sum;
        }

        public int getRefundCount() {
            return refund_count;
        }

        public void setRefundCount(int refund_count) {
            this.refund_count = refund_count;
        }
    }

    public static class DateBean extends CoreBean {
        /**
         * date : 20180903
         * pay_amount_sum : 0.09
         * pay_count : 7
         */

        private String date;
        private float pay_amount_sum;
        private int pay_count;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public float getPay_amount_sum() {
            return pay_amount_sum;
        }

        public void setPay_amount_sum(float pay_amount_sum) {
            this.pay_amount_sum = pay_amount_sum;
        }

        public int getPay_count() {
            return pay_count;
        }

        public void setPay_count(int pay_count) {
            this.pay_count = pay_count;
        }
    }

    public static class PaytypeBean extends CoreBean {
        /**
         * paytype : 0
         * paytype_name :
         * paytype_icon :
         * pay_amount_sum : 0.12
         * pay_count : 3
         */

        private int paytype;
        private String paytype_name;
        private String paytype_icon;
        private double pay_amount_sum;
        private int pay_count;

        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
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

        public double getPay_amount_sum() {
            return pay_amount_sum;
        }

        public void setPay_amount_sum(double pay_amount_sum) {
            this.pay_amount_sum = pay_amount_sum;
        }

        public int getPay_count() {
            return pay_count;
        }

        public void setPay_count(int pay_count) {
            this.pay_count = pay_count;
        }
    }

    public static class MemberBean extends CoreBean {
        /**
         * pay_cashierid : 1
         * cashier_name : 真事
         * cashier_image : 0c\71480df849c385861e50927fefc108af759e53.png
         * pay_amount_sum : 0.1
         * pay_count : 1
         */

        private int pay_cashierid;
        private String cashier_name;
        private String cashier_image;
        private double pay_amount_sum;
        private int pay_count;
        private String store_name;

        public int getPay_cashierid() {
            return pay_cashierid;
        }

        public void setPay_cashierid(int pay_cashierid) {
            this.pay_cashierid = pay_cashierid;
        }

        public String getCashier_name() {
            return cashier_name;
        }

        public void setCashier_name(String cashier_name) {
            this.cashier_name = cashier_name;
        }

        public String getCashier_image() {
            return cashier_image;
        }

        public void setCashier_image(String cashier_image) {
            this.cashier_image = cashier_image;
        }

        public double getPay_amount_sum() {
            return pay_amount_sum;
        }

        public void setPay_amount_sum(double pay_amount_sum) {
            this.pay_amount_sum = pay_amount_sum;
        }

        public int getPay_count() {
            return pay_count;
        }

        public void setPay_count(int pay_count) {
            this.pay_count = pay_count;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }
    }
}
