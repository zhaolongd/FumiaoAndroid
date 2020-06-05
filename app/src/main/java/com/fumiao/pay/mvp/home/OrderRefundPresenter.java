package com.fumiao.pay.mvp.home;

import android.app.Activity;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.RefundBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class OrderRefundPresenter extends BasePresenter<OrderRefundView> {

    public OrderRefundPresenter(OrderRefundView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    //申请退款
    public void requestRefund(String data) {
        OkGo.<BaseResponse<RefundBean>>get(ORDER_REFUND)
                .params("data", data)
                .execute(new JsonCallback<BaseResponse<RefundBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<RefundBean>> response) {
                        if (mvpView != null && response.body().data != null){
                            mvpView.refundSuccess(response.body().data);
                        }
                    }
                });
    }
}
