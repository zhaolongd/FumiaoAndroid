package com.fumiao.pay.mvp.me;

import android.app.Activity;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class CashierAddPresenter extends BasePresenter<CashierAddView>{


    public CashierAddPresenter(CashierAddView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void bindCashierQrcode(String data){
        OkGo.<BaseResponse<String>>post(BIND_CASHIER_QRCODE)
                .params("data", data)
                .execute(new JsonCallback<BaseResponse<String>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<String>> response) {
                        if (mvpView != null) {
                            mvpView.bindCashierQrcodeSuccess();
                            ToastUitl.show(response.body().data);
                        }
                    }
                });
    }
}
