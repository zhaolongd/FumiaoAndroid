package com.fumiao.pay.mvp.home;

import com.fumiao.pay.bean.home.RefundDetailBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface OrderRefundDetailsView extends BaseView {
    void showOrderRefundDetails(RefundDetailBean data);
}
