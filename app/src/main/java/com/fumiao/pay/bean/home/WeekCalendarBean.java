package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class WeekCalendarBean {
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


    public static class MonthsBean{
        private String month;
        private List<WeeksBean> weeks;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public List<WeeksBean> getWeeks() {
            return weeks;
        }

        public void setWeeks(List<WeeksBean> weeks) {
            this.weeks = weeks;
        }
    }

    public static  class WeeksBean extends CoreBean {
        private String year_month;
        private String year;
        private String month;
        private String week_number;
        private String start_date;
        private String end_date;
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

        public String getWeek_number() {
            return week_number;
        }

        public void setWeek_number(String week_number) {
            this.week_number = week_number;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public float getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(float pay_amount) {
            this.pay_amount = pay_amount;
        }
    }
}
