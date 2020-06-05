package com.fumiao.pay.mvp.home;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.RefundDetailBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class OrderRefundDetailsPresenter extends BasePresenter<OrderRefundDetailsView> {

    public OrderRefundDetailsPresenter(OrderRefundDetailsView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getRefundOrderDetails(String id) {
        OkGo.<BaseResponse<RefundDetailBean>>get(REFUND_ORDER_DETAILS)
                .params("pay_orderid", id)
                .execute(new JsonCallback<BaseResponse<RefundDetailBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<RefundDetailBean>> response) {
                        if (mvpView != null && response.body().data != null){
                            mvpView.showOrderRefundDetails(response.body().data);
                        }
                    }
                });
    }
}
