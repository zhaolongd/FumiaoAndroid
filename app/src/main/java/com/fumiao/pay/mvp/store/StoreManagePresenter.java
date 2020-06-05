package com.fumiao.pay.mvp.store;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.store.StoreListBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class StoreManagePresenter extends BasePresenter<StoreManageVeiw> {
    public StoreManagePresenter(StoreManageVeiw mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getStoreList() {
        OkGo.<BaseResponse<StoreListBean>>post(STORE_LIST)
                .execute(new JsonCallback<BaseResponse<StoreListBean>>(activity,false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<StoreListBean>> response) {
                        StoreListBean storeListBean = response.body().data;
                        if(storeListBean != null && !activity.isFinishing()){
                            mvpView.showStoreList(storeListBean);
                        }
                    }
                });
    }

    public void setDefaultStore(final int id) {
        OkGo.<BaseResponse>post(SET_DEFAULT_STORE)
                .params("id", id)
                .execute(new JsonCallback<BaseResponse>(activity,true) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        mvpView.setDefaultStoreSuccess(id);
                    }
                });
    }
}
