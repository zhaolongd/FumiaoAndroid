package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class ReconciliationsResultBean {

    /**
     * payment_list : {"total":10,"per_page":100,"current_page":1,"last_page":1,"data":[{"id":222,"pay_orderid":"20180904160130027567","pay_amount":"0.0100","pay_successdate":1536048115,"paytype_name":null,"paytype_icon":null,"cashier_name":"简单爱","store_name":"店铺名称"},{"id":220,"pay_orderid":"20180904150318064797","pay_amount":"0.0100","pay_successdate":1536044604,"paytype_name":null,"paytype_icon":null,"cashier_name":"简单爱","store_name":"店铺名称"},{"id":219,"pay_orderid":"20180904150211437136","pay_amount":"0.0100","pay_successdate":1536044542,"paytype_name":null,"paytype_icon":null,"cashier_name":"简单爱","store_name":"店铺名称"},{"id":215,"pay_orderid":"20180903190041640442","pay_amount":"0.0100","pay_successdate":1535972447,"paytype_name":"微信扫码","paytype_icon":"/static/common/paytype/weixin_ico.png","cashier_name":"简单爱","store_name":"店铺名称"},{"id":214,"pay_orderid":"20180903185950443634","pay_amount":"0.0100","pay_successdate":1535972403,"paytype_name":"支付宝扫码","paytype_icon":"/static/common/paytype/ali_ico.png","cashier_name":"简单爱","store_name":"店铺名称"},{"id":207,"pay_orderid":"20180903185409424433","pay_amount":"0.0100","pay_successdate":1535972060,"paytype_name":"百度钱包","paytype_icon":"/static/common/paytype/baidu_ico.png","cashier_name":"简单爱","store_name":"店铺名称"},{"id":206,"pay_orderid":"20180903185234749716","pay_amount":"0.0100","pay_successdate":1535971969,"paytype_name":"微信扫码","paytype_icon":"/static/common/paytype/weixin_ico.png","cashier_name":"简单爱","store_name":"店铺名称"},{"id":205,"pay_orderid":"20180903183902681921","pay_amount":"0.0100","pay_successdate":1535971171,"paytype_name":"微信H5","paytype_icon":"/static/common/paytype/weixin_ico.png","cashier_name":"简单爱","store_name":"店铺名称"},{"id":202,"pay_orderid":"20180903162725473942","pay_amount":"0.0100","pay_successdate":1535963262,"paytype_name":"支付宝H5","paytype_icon":"/static/common/paytype/ali_ico.png","cashier_name":"简单爱","store_name":"店铺名称"},{"id":200,"pay_orderid":"20180903154609677872","pay_amount":"0.0300","pay_successdate":1535962743,"paytype_name":"支付宝H5","paytype_icon":"/static/common/paytype/ali_ico.png","cashier_name":"简单爱","store_name":"店铺名称"}]}
     * statistic : {"order_money_sum":0.12,"order_count":10}
     */

    private ReconciliationsResultBean.PaymentListBean payment_list;
    private StatisticBean statistic;

    public ReconciliationsResultBean.PaymentListBean getPayment_list() {
        return payment_list;
    }

    public void setPayment_list(ReconciliationsResultBean.PaymentListBean payment_list) {
        this.payment_list = payment_list;
    }

    public StatisticBean getStatistic() {
        return statistic;
    }

    public void setStatistic(StatisticBean statistic) {
        this.statistic = statistic;
    }

    public static class PaymentListBean  extends CoreBean {
        /**
         * total : 10
         * per_page : 100
         * current_page : 1
         * last_page : 1
         * data : [{"id":222,"pay_orderid":"20180904160130027567","pay_amount":"0.0100","pay_successdate":1536048115,"paytype_name":null,"paytype_icon":null,"cashier_name":"简单爱","store_name":"店铺名称"},{"id":220,"pay_orderid":"20180904150318064797","pay_amount":"0.0100","pay_successdate":1536044604,"paytype_name":null,"paytype_icon":null,"cashier_name":"简单爱","store_name":"店铺名称"},{"id":219,"pay_orderid":"20180904150211437136","pay_amount":"0.0100","pay_successdate":1536044542,"paytype_name":null,"paytype_icon":null,"cashier_name":"简单爱","store_name":"店铺名称"},{"id":215,"pay_orderid":"20180903190041640442","pay_amount":"0.0100","pay_successdate":1535972447,"paytype_name":"微信扫码","paytype_icon":"/static/common/paytype/weixin_ico.png","cashier_name":"简单爱","store_name":"店铺名称"},{"id":214,"pay_orderid":"20180903185950443634","pay_amount":"0.0100","pay_successdate":1535972403,"paytype_name":"支付宝扫码","paytype_icon":"/static/common/paytype/ali_ico.png","cashier_name":"简单爱","store_name":"店铺名称"},{"id":207,"pay_orderid":"20180903185409424433","pay_amount":"0.0100","pay_successdate":1535972060,"paytype_name":"百度钱包","paytype_icon":"/static/common/paytype/baidu_ico.png","cashier_name":"简单爱","store_name":"店铺名称"},{"id":206,"pay_orderid":"20180903185234749716","pay_amount":"0.0100","pay_successdate":1535971969,"paytype_name":"微信扫码","paytype_icon":"/static/common/paytype/weixin_ico.png","cashier_name":"简单爱","store_name":"店铺名称"},{"id":205,"pay_orderid":"20180903183902681921","pay_amount":"0.0100","pay_successdate":1535971171,"paytype_name":"微信H5","paytype_icon":"/static/common/paytype/weixin_ico.png","cashier_name":"简单爱","store_name":"店铺名称"},{"id":202,"pay_orderid":"20180903162725473942","pay_amount":"0.0100","pay_successdate":1535963262,"paytype_name":"支付宝H5","paytype_icon":"/static/common/paytype/ali_ico.png","cashier_name":"简单爱","store_name":"店铺名称"},{"id":200,"pay_orderid":"20180903154609677872","pay_amount":"0.0300","pay_successdate":1535962743,"paytype_name":"支付宝H5","paytype_icon":"/static/common/paytype/ali_ico.png","cashier_name":"简单爱","store_name":"店铺名称"}]
         */

        private int total;
        private int per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean  extends CoreBean{
            /**
             * id : 222
             * pay_orderid : 20180904160130027567
             * pay_amount : 0.0100
             * pay_successdate : 1536048115
             * paytype_name : null
             * paytype_icon : null
             * cashier_name : 简单爱
             * store_name : 店铺名称
             * refund_org_pay_orderid
             * pay_applydate :支付时间
             */

            private int id;
            private String pay_orderid;
            private String pay_amount;
            private String pay_successdate;
            private String paytype_name;
            private String paytype_icon;
            private String cashier_name;
            private String store_name;
            private boolean top;
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

            public boolean isTop() {
                return top;
            }

            public void setTop(boolean top) {
                this.top = top;
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

    public static class StatisticBean {
        /**
         * order_money_sum : 0.12
         * order_count : 10
         */

        private String order_money_sum = "0";
        private String order_count = "0";

        public String getOrder_money_sum() {
            return order_money_sum;
        }

        public void setOrder_money_sum(String order_money_sum) {
            this.order_money_sum = order_money_sum;
        }

        public String getOrder_count() {
            return order_count;
        }

        public void setOrder_count(String order_count) {
            this.order_count = order_count;
        }
    }
}
