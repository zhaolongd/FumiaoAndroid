package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class DayBillReportBean {
    private String search_day;
    private String search_week;
    private String search_month;
    private List<TrendSumBean> trend_sum;
    private List<PaytypeBean> paytype;
    private ComparedSumBean compared_sum;
    private SumBean sum;
    private RefundSumBean refund_sum;

    public String getSearch_day() {
        return search_day;
    }

    public void setSearch_day(String search_day) {
        this.search_day = search_day;
    }

    public String getSearch_week() {
        return search_week;
    }

    public void setSearch_week(String search_week) {
        this.search_week = search_week;
    }

    public String getSearch_month() {
        return search_month;
    }

    public void setSearch_month(String search_month) {
        this.search_month = search_month;
    }

    public List<TrendSumBean> getTrend_sum() {
        return trend_sum;
    }

    public void setTrend_sum(List<TrendSumBean> trend_sum) {
        this.trend_sum = trend_sum;
    }

    public List<PaytypeBean> getPaytype() {
        return paytype;
    }

    public void setPaytype(List<PaytypeBean> paytype) {
        this.paytype = paytype;
    }

    public ComparedSumBean getCompared_sum() {
        return compared_sum;
    }

    public void setCompared_sum(ComparedSumBean compared_sum) {
        this.compared_sum = compared_sum;
    }

    public SumBean getSum() {
        return sum;
    }

    public void setSum(SumBean sum) {
        this.sum = sum;
    }

    public RefundSumBean getRefund_sum() {
        return refund_sum;
    }

    public void setRefund_sum(RefundSumBean refund_sum) {
        this.refund_sum = refund_sum;
    }

    public static class RefundSumBean{
        private float refund_amount_sum;
        private int refund_count;

        public float getRefundAmountSum() {
            return refund_amount_sum;
        }

        public void setRefundAmountSum(float refund_amount_sum) {
            this.refund_amount_sum = refund_amount_sum;
        }

        public int getRefundCount() {
            return refund_count;
        }

        public void setRefundCount(int refund_count) {
            this.refund_count = refund_count;
        }
    }

    public static class SumBean extends CoreBean {
        private float pay_amount_sum;
        private int pay_count;

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

    public static class ComparedSumBean extends CoreBean {
        private String day; //20190722
        private float pay_amount_sum;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public float getPay_amount_sum() {
            return pay_amount_sum;
        }

        public void setPay_amount_sum(float pay_amount_sum) {
            this.pay_amount_sum = pay_amount_sum;
        }
    }


    public static class TrendSumBean extends CoreBean {
        private String h;
        private float pay_amount_sum;

        public String getH() {
            return h;
        }

        public void setH(String h) {
            this.h = h;
        }

        public float getPay_amount_sum() {
            return pay_amount_sum;
        }

        public void setPay_amount_sum(float pay_amount_sum) {
            this.pay_amount_sum = pay_amount_sum;
        }
    }

    public static class PaytypeBean extends CoreBean {
        private int paytype;
        private String paytype_name;
        private String paytype_icon;
        private float pay_amount_sum;
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
}
