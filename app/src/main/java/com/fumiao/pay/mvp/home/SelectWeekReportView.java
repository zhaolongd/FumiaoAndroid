package com.fumiao.pay.mvp.home;

import com.fumiao.pay.bean.home.WeekCalendarBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface SelectWeekReportView extends BaseView {

    void showWeekCalendar(WeekCalendarBean weekCalendarBean);
}
