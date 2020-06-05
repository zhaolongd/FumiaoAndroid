package com.fumiao.pay.mvp.home;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.WeekCalendarBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class SelectWeekReportPresenter extends BasePresenter<SelectWeekReportView> {

    public SelectWeekReportPresenter(SelectWeekReportView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getWeekReportCalendar(int store_id){

        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        httpParams.put("statistic_type","w");// d/日 w/周 m/月
        OkGo.<BaseResponse<WeekCalendarBean>>post(REPORT_CALENDAR)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<WeekCalendarBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<WeekCalendarBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.showWeekCalendar(response.body().data);
                        }
                    }
                });
    }
}
