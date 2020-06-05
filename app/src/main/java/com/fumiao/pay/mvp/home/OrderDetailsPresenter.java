package com.fumiao.pay.mvp.home;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.OrderDetailsBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;


public class OrderDetailsPresenter extends BasePresenter<OrderDetailsView> {

    public OrderDetailsPresenter(OrderDetailsView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getOrderDetails(String id) {
        OkGo.<BaseResponse<OrderDetailsBean>>get(ORDER_DETAILS)
                .params("id", id)
                .execute(new JsonCallback<BaseResponse<OrderDetailsBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<OrderDetailsBean>> response) {
                        if (mvpView != null && response.body().data != null){
                            mvpView.showOrderDetails(response.body().data);
                        }
                    }
                });
    }

    //取消订单
    public void requestOrderCancel(String id) {
        OkGo.<BaseResponse>get(ORDER_CANCEL)
                .params("pay_orderid", id)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        if (mvpView != null) {
                            mvpView.cancelOrderSuccess();
                        }
                    }
                });
    }

}
