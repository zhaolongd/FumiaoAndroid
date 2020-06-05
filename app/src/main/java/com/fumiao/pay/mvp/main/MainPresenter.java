package com.fumiao.pay.mvp.main;

import android.app.Activity;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.pay.bean.store.StoreDetailsBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import cn.jpush.android.api.JPushInterface;


public class MainPresenter extends BasePresenter<MainView> {


    public MainPresenter(MainView mvpView, Activity activity) {
        super(mvpView, activity);
    }


    public void init(){
        OkGo.<BaseResponse>get(INIT_URL)
                .execute(new JsonCallback<BaseResponse>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {

                    }
                });
    }

    public void initPushRegistrationId(){
        OkGo.<BaseResponse>post(INIT_JPUSH_REGISTRANTION_ID)
                .params("registration_id", JPushInterface.getRegistrationID(activity))
                .params("platform_name", "android")
                .execute(new JsonCallback<BaseResponse>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {

                    }
                });
    }



    public void getStoreDetails(int id) {
        if (id==0){
            return;
        }
        OkGo.<BaseResponse<StoreDetailsBean>>get(STORE_DETAILS)
                .params("id", id)
                .execute(new JsonCallback<BaseResponse<StoreDetailsBean>>(activity,false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<StoreDetailsBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            saveStoreDetails(response.body().data);
                            mvpView.showStoreDetails(response.body().data);
                        }
                    }
                });
    }

    private void saveStoreDetails(StoreDetailsBean data){
        SPUtil.getInstance().putString(REASON, data.getReason());
        SPUtil.getInstance().putInt(STORE_MANAGER_ID, data.getStore().getManager_id());
        SPUtil.getInstance().putInt(STORE_STATUS, data.getStore().getStatus());
        SPUtil.getInstance().putString(STORE_NAME, data.getStore().getName());
        SPUtil.getInstance().putString(STORE_IMAGE, data.getStore().getImage());
        SPUtil.getInstance().putString(STORE_CATE_NAME, data.getStore().getCate_name());
        SPUtil.getInstance().putString(STORE_DESCRIPTION, data.getStore().getDescription());
        SPUtil.getInstance().putString(STORE_CATE_ID, data.getStore().getCate_id());
        SPUtil.getInstance().putInt(PAYMENT_STATUS, data.getPayment_status());
        SPUtil.getInstance().putString(STORE_CITY_NAME, data.getStore().getCity_name() + "");
        SPUtil.getInstance().putString(STORE_CITY, data.getStore().getCity() + "");
        SPUtil.getInstance().putString(STORE_PROVINCE, data.getStore().getProvince() + "");
        SPUtil.getInstance().putString(STORE_PROVINCE_NAME, data.getStore().getProvince_name());
        SPUtil.getInstance().putString(STORE_AREA, data.getStore().getArea() + "");
        SPUtil.getInstance().putString(STORE_AREA_NAME, data.getStore().getArea_name());
        SPUtil.getInstance().putString(STORE_ADDRESS, data.getStore().getAddress());
        List<String> cateTages = data.getStore().getCate_tags();
        final String regularExpression = ",";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cateTages.size(); i++) {
            stringBuilder.append(cateTages.get(i));
            if (i != cateTages.size() - 1) {
                stringBuilder.append(regularExpression);
            }
        }
        SPUtil.getInstance().putString(STORE_CATE_TAGS, stringBuilder.toString());
    }
}
