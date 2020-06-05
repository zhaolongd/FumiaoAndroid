package com.fumiao.pay.mvp.store;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.store.StoreListBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/1/16 0016 11:01
 */
public class SelectStorePresenter extends BasePresenter<SelectStoreView> {

    public SelectStorePresenter(SelectStoreView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getStoreList() {
        OkGo.<BaseResponse<StoreListBean>>post(ALL_STORE_LIST)
                .execute(new JsonCallback<BaseResponse<StoreListBean>>(activity,false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<StoreListBean>> response) {
                        StoreListBean storeListBean = response.body().data;
                        if(storeListBean != null && mvpView != null){
                            mvpView.showStoreList(storeListBean);
                        }
                    }
                });
    }
}
