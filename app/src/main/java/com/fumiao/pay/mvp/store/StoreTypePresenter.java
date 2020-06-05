package com.fumiao.pay.mvp.store;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.store.CateListBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class StoreTypePresenter extends BasePresenter<StoreTypeView> {

    public StoreTypePresenter(StoreTypeView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getCateList() {
        OkGo.<BaseResponse<CateListBean>>post(CATE_LIST)
                .execute(new JsonCallback<BaseResponse<CateListBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<CateListBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.showCateList(response.body().data);
                        }
                    }
                });
    }
}
