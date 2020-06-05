package com.fumiao.pay.mvp.home;

import android.app.Activity;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.ReconciliationScreenBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class ReconciliationScreenPresenter extends BasePresenter<ReconciliationScreenView>{
    public ReconciliationScreenPresenter(ReconciliationScreenView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void sreenOrder() {
        OkGo.<BaseResponse<ReconciliationScreenBean>>post(ORDER_SREEN)
                .execute(new JsonCallback<BaseResponse<ReconciliationScreenBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<ReconciliationScreenBean>> response) {
                        if (mvpView != null && response.body().data != null){
                            mvpView.initScreenData(response.body().data);
                        }
                    }
                });

    }
}
