package com.fumiao.pay.mvp.home;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.WeekBillReportBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class WeekBillReportPresenter extends BasePresenter<WeekBillReportView> {

    public WeekBillReportPresenter(WeekBillReportView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getWeekBillReport(int store_id, String search_month, String search_week){

        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        httpParams.put("statistic_type","w");// d/日 w/周 m/月
        if(!search_month.isEmpty()){
            httpParams.put("search_month", search_month);
        }
        if(!search_week.isEmpty()){
            httpParams.put("search_week", search_week);
        }
        OkGo.<BaseResponse<WeekBillReportBean>>post(STATISTIC_REPORT)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<WeekBillReportBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<WeekBillReportBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.showWeekBillReport(response.body().data);
                        }
                    }
                });
    }
}
