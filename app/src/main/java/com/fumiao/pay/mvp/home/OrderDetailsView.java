package com.fumiao.pay.mvp.home;

import com.fumiao.pay.bean.home.OrderDetailsBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface OrderDetailsView  extends BaseView{
    void showOrderDetails(OrderDetailsBean data);
    void cancelOrderSuccess();
}
