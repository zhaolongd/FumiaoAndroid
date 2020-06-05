package com.fumiao.pay.mvp.me;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.CashierCodeBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class CashierDetailPresenter extends BasePresenter<CashierDetailView> {

    public CashierDetailPresenter(CashierDetailView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getQrCodeDetail(String money, int store_id) {

        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        if (!"".equals(money)) {
            httpParams.put("money", money);
        }
        OkGo.<BaseResponse<CashierCodeBean>>post(PAYMENT_CODE)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<CashierCodeBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<CashierCodeBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.showCashierDetail(response.body().data);
                        }
                    }
                });
    }
}
