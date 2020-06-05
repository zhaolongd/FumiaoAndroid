package com.fumiao.pay.mvp.home;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.ReconciliationsResultBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class ReconciliationsResultPresenter extends BasePresenter<ReconciliationsResultView>{

    public ReconciliationsResultPresenter(ReconciliationsResultView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getList(String out_transaction_id, String start_time, String end_time, int store_id, String payType, String payWay, String payStatus, int pagesize, int page) {
        HttpParams httpParams = new HttpParams();
        if(out_transaction_id != null && !out_transaction_id.equals("")){
            httpParams.put("out_transaction_id", out_transaction_id);
        }
        httpParams.put("start_time", start_time);
        httpParams.put("end_time", end_time);
        if (store_id != 0) {
            httpParams.put("store_id", store_id);
        }
        httpParams.put("paytype", payType);
        httpParams.put("payway", payWay);
        httpParams.put("pay_status", payStatus);
        httpParams.put("pagesize", pagesize);
        httpParams.put("page", page);
        OkGo.<BaseResponse<ReconciliationsResultBean>>get(PAYMENT_LIST)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<ReconciliationsResultBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<ReconciliationsResultBean>> response) {
                        if (mvpView != null && response.body().data != null){
                            mvpView.showReconciliationsResultList(response.body().data);
                        }

                    }
                });
    }
}
