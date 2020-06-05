package com.fumiao.pay.mvp.msg;

import android.app.Activity;
import android.util.Log;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.me.HelpCenterBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class MsgPresenter extends BasePresenter<MsgView> {
    public MsgPresenter(MsgView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getArticleList(int pagesize, int page) {
        OkGo.<BaseResponse<HelpCenterBean>>get(HELP_LIST)
                .params("type", 1)
                .params("pagesize", pagesize)
                .params("page", page)
                .execute(new JsonCallback<BaseResponse<HelpCenterBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<HelpCenterBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.showInstationList(response.body().data);
                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<HelpCenterBean>> response) {
                        super.onError(response);
                        mvpView.loadArticleDataFail();
                    }
                });

    }

}
