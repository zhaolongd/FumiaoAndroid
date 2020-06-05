package com.fumiao.pay.mvp.home;

import android.app.Activity;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.pay.bean.home.CashierCodeBean;
import com.fumiao.pay.bean.home.HomeBean;
import com.fumiao.pay.bean.home.PaymentRecordListBean;
import com.fumiao.pay.bean.home.TodayPaymentBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class HomePresenter extends BasePresenter<HomeView> {
    public HomePresenter(HomeView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getHomeData(){
        OkGo.<BaseResponse<HomeBean>>get(HOME_LIST)
                .execute(new JsonCallback<BaseResponse<HomeBean>>(activity,false) {
                    @Override
                        public void onSuccessCallback(Response<BaseResponse<HomeBean>> response) {
                        if (mvpView != null && response.body().data != null)
                            mvpView.showHomeView(response.body().data);
                        }
                    });
    }

    public void getTodayPayment(int store_id){
        OkGo.<BaseResponse<TodayPaymentBean>>post(HOME_TODAY_PAYMENT)
                .params("store_id", store_id)
                .execute(new JsonCallback<BaseResponse<TodayPaymentBean>>(activity,false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<TodayPaymentBean>> response) {
                        if (mvpView != null && response.body().data != null){
                            mvpView.showTodayPayment(response.body().data);
                        }
                    }
                });
    }

    //查询收款码有没有激活状态
    public void getCashierDetail(int store_id, int request_code) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        OkGo.<BaseResponse<CashierCodeBean>>post(PAYMENT_CODE)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<CashierCodeBean>>(activity,true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<CashierCodeBean>> response) {
                        if (mvpView != null && response.body().data != null){
                            mvpView.cashierDetail(response.body().data , request_code);
                        }
                    }
                });
    }

    //绑定扫描二维码
    public void bindCashierQrcode(String data, int request_code){
        OkGo.<BaseResponse<String>>post(BIND_CASHIER_QRCODE)
                .params("data", data)
                .execute(new JsonCallback<BaseResponse<String>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<String>> response) {
                        if (mvpView != null){
                            mvpView.bindCashierQrcodeSuccess(request_code);
                        }
                        ToastUitl.show(response.body().data);
                    }
                });
    }

    public void getPaymentRecord(int store_id){
        OkGo.<BaseResponse<PaymentRecordListBean>>get(PAYMENT_ARRIVED_RECORDS)
                .params("store_id", store_id)
                .execute(new JsonCallback<BaseResponse<PaymentRecordListBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<PaymentRecordListBean>> response) {
                        if (mvpView != null && response.body().data != null){
                            mvpView.showPaymentRecord(response.body().data);
                        }
                    }
                });
    }
}
