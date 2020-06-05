package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class DayCalendarBean {
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
        private List<DaysBean> days;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public List<DaysBean> getDays() {
            return days;
        }

        public void setDays(List<DaysBean> days) {
            this.days = days;
        }
    }

    public static  class DaysBean extends CoreBean {
        private String day;
        private float amount;
        private String date;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
