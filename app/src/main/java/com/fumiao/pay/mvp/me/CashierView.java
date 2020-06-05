package com.fumiao.pay.mvp.me;

import com.fumiao.pay.bean.home.CashierCodeBean;
import com.fumiao.pay.bean.home.CashierDetailBean;
import com.fumiao.pay.bean.me.CashierDeviceBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface CashierView extends BaseView{
    void cashierList(CashierDeviceBean data);
    void cashierCode(CashierCodeBean data);
}
