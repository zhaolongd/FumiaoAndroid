package com.fumiao.pay.mvp.me;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.store.StoreDetailsBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Created by chee on 2018/9/18.
 */
public class StoreInfoPresenter extends BasePresenter<StoreInfoView> {
    public StoreInfoPresenter(StoreInfoView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getStoreDetails(int id) {
        OkGo.<BaseResponse<StoreDetailsBean>>get(STORE_DETAILS)
                .params("id", id)
                .execute(new JsonCallback<BaseResponse<StoreDetailsBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<StoreDetailsBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.showStoreDetails(response.body().data);
                        }
                    }
                });
    }
}
