package com.fumiao.pay.mvp.me;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.pay.bean.home.CashierDetailBean;
import com.fumiao.pay.bean.store.MemberDetailsBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class MePresenter extends BasePresenter<MeView> {

    public MePresenter(MeView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    //获取收银通道是否开通
    public void getCashierDetail(int store_id, int request_code) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        OkGo.<BaseResponse<CashierDetailBean>>get(CASHIER_DETAIL)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<CashierDetailBean>>(activity,true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<CashierDetailBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.cashierDetail(response.body().data, request_code);
                        }
                    }
                });
    }

    public void getClerkDetails(int id) {
        OkGo.<BaseResponse<MemberDetailsBean>>get(MEMBER_PROFILE)
                .params("id", id)
                .execute(new JsonCallback<BaseResponse<MemberDetailsBean>>(activity,false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<MemberDetailsBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.showClerkDetails(response.body().data);
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
                        if (mvpView != null) {
                            mvpView.bindCashierQrcodeSuccess(request_code);
                            ToastUitl.show(response.body().data);
                        }
                    }
                });
    }
}
