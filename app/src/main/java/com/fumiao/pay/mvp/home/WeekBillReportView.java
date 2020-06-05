package com.fumiao.pay.mvp.home;


import com.fumiao.pay.bean.home.WeekBillReportBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface WeekBillReportView extends BaseView {
    void showWeekBillReport(WeekBillReportBean reportBean);
}
