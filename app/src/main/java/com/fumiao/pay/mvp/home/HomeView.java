package com.fumiao.pay.mvp.home;

import com.fumiao.pay.bean.home.CashierCodeBean;
import com.fumiao.pay.bean.home.CashierDetailBean;
import com.fumiao.pay.bean.home.HomeBean;
import com.fumiao.pay.bean.home.PaymentRecordListBean;
import com.fumiao.pay.bean.home.TodayPaymentBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface HomeView extends BaseView {
    void showHomeView(HomeBean data);
    void showTodayPayment(TodayPaymentBean data);
    void cashierDetail(CashierCodeBean data, int request_code);
    void bindCashierQrcodeSuccess(int request_code);
    void showPaymentRecord(PaymentRecordListBean listData);
}
