package com.fumiao.pay.mvp.store;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.pay.bean.store.CateListBean;
import com.fumiao.pay.bean.store.StoreDetailsBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.File;

/**
 * Created by chee on 2018/9/21.
 */
public class StoreSettingPresenter extends BasePresenter<StoreSettingView> {

    public StoreSettingPresenter(StoreSettingView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void editStore(int id, String image, String name, String cate_id, String cate_name, String province, String province_name, String city, String city_name, String area, String area_name, String address) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        if (!"".equals(image)) {
            httpParams.put("image", new File(image));
        }
//        if (!"".equals(cate_id)) {
//            httpParams.put("cate_id", cate_id);
//        }
//        if (!"".equals(cate_name)) {
//            httpParams.put("cate_name", cate_name);
//        }
//        httpParams.put("province", province);
//        httpParams.put("province_name", province_name);
//        httpParams.put("city", city);
//        httpParams.put("city_name", city_name);
//        httpParams.put("area", area);
//        httpParams.put("area_name", area_name);
//        httpParams.put("address", address);

        OkGo.<BaseResponse>post(STORE_ADD)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        if (mvpView != null) {
                            mvpView.editSuccess();
                        }
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
                            mvpView.showStoreDetails(response.body().data);
                        }
                    }
                });
    }


}
