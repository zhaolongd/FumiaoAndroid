package com.fumiao.pay.mvp.store;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.RsaUtils;
import com.fumiao.pay.bean.store.MemberDetailsBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

public class MemberDetailPresenter extends BasePresenter<MemberDetailView> {

    public MemberDetailPresenter(MemberDetailView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getMemberDetail(int id) {
        OkGo.<BaseResponse<MemberDetailsBean>>get(MEMBER_PROFILE)
                .params("id", id)
                .execute(new JsonCallback<BaseResponse<MemberDetailsBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<MemberDetailsBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.showMemberDetail(response.body().data);
                        }
                    }
                });
    }

    public void editMember(int id, String password, String username, String realname, String position) {

        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        httpParams.put("password", RsaUtils.encryptByPublicKey(password));
        httpParams.put("phone", username);
        httpParams.put("realname", realname);
        httpParams.put("position", position);
        OkGo.<BaseResponse>post(CLERK_EDIT)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        if (mvpView != null) {
                            mvpView.editeMemberSuccess();
                        }
                    }
                });
    }
}
