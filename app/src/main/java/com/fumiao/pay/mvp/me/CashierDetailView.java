package com.fumiao.pay.mvp.me;

import com.fumiao.pay.bean.home.CashierCodeBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface CashierDetailView extends BaseView{
    void showCode(byte[] data);
    void showCashierDetail(CashierCodeBean data);
}
