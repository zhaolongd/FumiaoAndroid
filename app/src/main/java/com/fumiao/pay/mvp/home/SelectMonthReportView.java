package com.fumiao.pay.mvp.home;

import com.fumiao.pay.bean.home.MonthCalendarBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface SelectMonthReportView extends BaseView {

    void showMonthCalendar(MonthCalendarBean monthCalendarBean);
}
