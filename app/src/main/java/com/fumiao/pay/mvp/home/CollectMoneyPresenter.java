package com.fumiao.pay.mvp.home;

import android.app.Activity;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.main.ScanBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class CollectMoneyPresenter extends BasePresenter<CollectMoneyView> {
    public CollectMoneyPresenter(CollectMoneyView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void scan(String auth_code, String store_id, String money,String attach) {
        OkGo.<BaseResponse<ScanBean>>post(SCAN_CODE)
                .params("auth_code", auth_code)
                .params("store_id", store_id)
                .params("money", money)
                .params("attach",attach)
                .execute(new JsonCallback<BaseResponse<ScanBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<ScanBean>> response) {
                        if (mvpView != null && response.body().data != null)
                            mvpView.showPay(response.body().data);
                    }
                });
    }
}
