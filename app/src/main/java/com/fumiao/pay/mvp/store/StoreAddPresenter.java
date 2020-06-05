package com.fumiao.pay.mvp.store;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.store.StoreAddBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.File;

public class StoreAddPresenter extends BasePresenter<StoreAddView> {
    public StoreAddPresenter(StoreAddView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void addStore(String id, String image, String name, String cate_id, String cate_name, String description, String province, String province_name, String city, String city_name, String area, String area_name, String address) {

        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        if (!"".equals(image)) {
            httpParams.put("image", new File(image));
        }
        httpParams.put("name", name);
        httpParams.put("cate_id", cate_id);
        httpParams.put("cate_name", cate_name);
        httpParams.put("description", description);
        httpParams.put("province", province);
        httpParams.put("province_name", province_name);
        httpParams.put("city", city);
        httpParams.put("city_name", city_name);
        httpParams.put("area", area);
        httpParams.put("area_name", area_name);
        httpParams.put("address", address);

        OkGo.<BaseResponse<StoreAddBean>>post(STORE_ADD)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<StoreAddBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<StoreAddBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.addSuccess(response.body().data);
                        }
                    }
                });
    }
}
