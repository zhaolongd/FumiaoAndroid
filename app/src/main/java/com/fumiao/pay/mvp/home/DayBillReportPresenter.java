package com.fumiao.pay.mvp.home;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.home.DayBillReportBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class DayBillReportPresenter extends BasePresenter<DayBillReportView> {

    public DayBillReportPresenter(DayBillReportView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getDayBillReport(int store_id, String search_day){

        HttpParams httpParams = new HttpParams();
        httpParams.put("store_id", store_id);
        httpParams.put("statistic_type","d");// d/日 w/周 m/月
        if(!search_day.isEmpty()){
            httpParams.put("search_day", search_day);
        }
        OkGo.<BaseResponse<DayBillReportBean>>post(STATISTIC_REPORT)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<DayBillReportBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<DayBillReportBean>> response) {
                        if (mvpView != null && response.body().data != null){
                            mvpView.showDayBillReport(response.body().data);
                        }
                    }
                });
    }
}
