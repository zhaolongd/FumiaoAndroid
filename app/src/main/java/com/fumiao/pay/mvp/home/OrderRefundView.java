package com.fumiao.pay.mvp.home;

import com.fumiao.pay.bean.home.RefundBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface OrderRefundView  extends BaseView {
    void refundSuccess(RefundBean data);
}
