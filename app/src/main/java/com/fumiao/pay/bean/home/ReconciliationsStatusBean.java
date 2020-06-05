package com.fumiao.pay.bean.home;

/**
 * Created by chee on 2018/9/13.
 */
public class ReconciliationsStatusBean {


    /**
     * statistic : {"order_money_sum":"0.59","order_count":6}
     */

    private StatisticBean statistic;

    public StatisticBean getStatistic() {
        return statistic;
    }

    public void setStatistic(StatisticBean statistic) {
        this.statistic = statistic;
    }

    public static class StatisticBean {
        /**
         * order_money_sum : 0.59
         * order_count : 6
         */

        private String order_money_sum;
        private int order_count;

        public String getOrder_money_sum() {
            return order_money_sum;
        }

        public void setOrder_money_sum(String order_money_sum) {
            this.order_money_sum = order_money_sum;
        }

        public int getOrder_count() {
            return order_count;
        }

        public void setOrder_count(int order_count) {
            this.order_count = order_count;
        }
    }
}
