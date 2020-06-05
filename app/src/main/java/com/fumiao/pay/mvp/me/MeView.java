package com.fumiao.pay.mvp.me;

import com.fumiao.pay.bean.home.CashierDetailBean;
import com.fumiao.pay.bean.store.MemberDetailsBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface MeView extends BaseView{
    void cashierDetail(CashierDetailBean data, int request_code);
    void showClerkDetails(MemberDetailsBean data);
    void bindCashierQrcodeSuccess(int request_code);
}
