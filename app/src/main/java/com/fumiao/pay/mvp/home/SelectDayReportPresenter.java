package com.fumiao.pay.mvp.home;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.DayCalendarBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class SelectDayReportPresenter extends BasePresenter<SelectDayReportView> {
    public SelectDayReportPresenter(SelectDayReportView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getDayReportCalendar(int store_id){

        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        httpParams.put("statistic_type","d");// d/日 w/周 m/月
        OkGo.<BaseResponse<DayCalendarBean>>post(REPORT_CALENDAR)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<DayCalendarBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<DayCalendarBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.showDayCalendar(response.body().data);
                        }
                    }
                });
    }
}
