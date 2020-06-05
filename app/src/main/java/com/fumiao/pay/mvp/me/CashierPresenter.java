package com.fumiao.pay.mvp.me;

import android.app.Activity;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.CashierCodeBean;
import com.fumiao.pay.bean.home.CashierDetailBean;
import com.fumiao.pay.bean.me.CashierDeviceBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class CashierPresenter extends BasePresenter<CashierView> {

    public CashierPresenter(CashierView mvpView, Activity activity) {
        super(mvpView, activity);
    }



    public void getCashierList(int store_id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        OkGo.<BaseResponse<CashierDeviceBean>>post(CHAIER_QRCODE_LIST)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<CashierDeviceBean>>(activity,false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<CashierDeviceBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.cashierList(response.body().data);
                        }
                    }
                });
    }

    //获取收款码状态是否激活
    public void getCashierCode(int store_id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        OkGo.<BaseResponse<CashierCodeBean>>post(PAYMENT_CODE)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<CashierCodeBean>>(activity,true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<CashierCodeBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.cashierCode(response.body().data);
                        }
                    }
                });
    }


    //获取进件结果
    public void getIncomingResult(int store_id) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        OkGo.<BaseResponse<CashierDetailBean>>post(INCOMING_RESULT)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<CashierDetailBean>>(activity,true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<CashierDetailBean>> response) {

                    }
                });
    }

}
