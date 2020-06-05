package com.fumiao.pay.mvp.home;

import com.fumiao.pay.bean.home.MonthBillReportBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface MonthBillReportView extends BaseView {
    void showMonthBillReport(MonthBillReportBean reportBean);
}
