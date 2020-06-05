package com.fumiao.pay.mvp.home;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.MonthCalendarBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class SelectMonthReportPresenter extends BasePresenter<SelectMonthReportView> {

    public SelectMonthReportPresenter(SelectMonthReportView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getMonthReportCalendar(int store_id){

        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        httpParams.put("statistic_type","m");// d/日 w/周 m/月
        OkGo.<BaseResponse<MonthCalendarBean>>post(REPORT_CALENDAR)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<MonthCalendarBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<MonthCalendarBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.showMonthCalendar(response.body().data);
                        }
                    }
                });
    }
}
