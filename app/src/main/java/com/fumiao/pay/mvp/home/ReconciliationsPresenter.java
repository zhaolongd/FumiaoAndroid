package com.fumiao.pay.mvp.home;

import android.app.Activity;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.ReconciliationsBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class ReconciliationsPresenter extends BasePresenter<ReconciliationsView>{

    public ReconciliationsPresenter(ReconciliationsView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    /**
     * store_id 当前门店ID
     *query_day 查询日期的起始日期格式：Ymd
     */
    public void getReconciliationsList(int store_id, int page, String query_day){
        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        httpParams.put("page", page);
        if(query_day != null && !query_day.equals("")){
            httpParams.put("query_day", query_day);
        }
        OkGo.<BaseResponse<ReconciliationsBean>>get(BILL_STATISTICS)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<ReconciliationsBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<ReconciliationsBean>> response) {
                        if (mvpView != null && response.body().data != null){
                            mvpView.showReconciliationsData(response.body().data);
                        }
                    }
                });
    }
}
