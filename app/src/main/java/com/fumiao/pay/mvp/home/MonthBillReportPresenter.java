package com.fumiao.pay.mvp.home;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.MonthBillReportBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class MonthBillReportPresenter extends BasePresenter<MonthBillReportView> {
    public MonthBillReportPresenter(MonthBillReportView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getMonthBillReport(int store_id, String search_month){

        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        httpParams.put("statistic_type","m");// d/日 w/周 m/月
        if(!search_month.isEmpty()){
            httpParams.put("search_month", search_month);
        }
        OkGo.<BaseResponse<MonthBillReportBean>>post(STATISTIC_REPORT)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<MonthBillReportBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<MonthBillReportBean>> response) {
                        if (mvpView != null && response.body().data != null){
                            mvpView.showMonthBillReport(response.body().data);
                        }
                    }
                });
    }
}
