package com.fumiao.pay.mvp.home;

import com.fumiao.pay.bean.home.DayCalendarBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface SelectDayReportView  extends BaseView {

    void showDayCalendar(DayCalendarBean dayCalendarBean);

}
