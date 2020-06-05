package com.fumiao.pay.mvp.store;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.pay.bean.store.MemberListBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class MemberManagePresenter extends BasePresenter<MemberManageView> {

    public MemberManagePresenter(MemberManageView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getMemberList(int store_id){
        OkGo.<BaseResponse<MemberListBean>>get(MEMBER_LIST)
                .params("store_id",store_id)
                .execute(new JsonCallback<BaseResponse<MemberListBean>>(activity, false) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<MemberListBean>> response) {
                        MemberListBean memberListBean = response.body().data;
                        if(memberListBean != null && mvpView != null){
                            mvpView.showMemberList(memberListBean);
                        }
                    }
                });
    }

    public void delMember(int id) {
        OkGo.<BaseResponse>post(DEL_MEMBER)
                .params("mid", id)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        if(mvpView != null){
                            mvpView.delMemberSuccess();
                        }
                    }
                });
    }
}
