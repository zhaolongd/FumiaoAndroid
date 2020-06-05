package com.fumiao.pay.mvp.home;

import com.fumiao.pay.bean.home.DayBillReportBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface DayBillReportView extends BaseView {
    void showDayBillReport(DayBillReportBean reportBean);
}
