package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class MonthCalendarBean {

    public List<CalendarBean> calendar;

    public List<CalendarBean> getCalendar() {
        return calendar;
    }

    public void setCalendar(List<CalendarBean> calendar) {
        this.calendar = calendar;
    }

    public static class CalendarBean {

        private String year;

        private List<MonthsBean> months;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public List<MonthsBean> getMonths() {
            return months;
        }

        public void setMonths(List<MonthsBean> months) {
            this.months = months;
        }
    }


    public static class MonthsBean extends CoreBean {
        private String year_month;
        private String year;
        private String month;
        private float pay_amount;
        public String getYear_month() {
            return year_month;
        }

        public void setYear_month(String year_month) {
            this.year_month = year_month;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public float getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(float pay_amount) {
            this.pay_amount = pay_amount;
        }
    }
}
