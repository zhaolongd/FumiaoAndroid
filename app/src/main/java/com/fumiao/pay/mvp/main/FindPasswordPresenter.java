package com.fumiao.pay.mvp.main;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.RsaUtils;
import com.fumiao.pay.bean.main.PhoneCodeBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class FindPasswordPresenter extends BasePresenter<FindPasswordView>{
    public FindPasswordPresenter(FindPasswordView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getPhoneCode(String phone) {
        OkGo.<BaseResponse<PhoneCodeBean>>post(GET_PHONE_CODE)
                .params("phone", phone)
                .params("source", "editpwd")
                .execute(new JsonCallback<BaseResponse<PhoneCodeBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<PhoneCodeBean>> response) {
                        mvpView.codeState(response.body().msg, response.body().data);
                    }
                });
    }

    public void changePassword(String phone, String code, String password, String reg_sign) {
        OkGo.<BaseResponse>post(CHANGE_PASSWORD)
                .params("phone", phone)
                .params("code", code)
                .params("password", RsaUtils.encryptByPublicKey(password))
                .params("reg_sign", reg_sign)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        mvpView.findSuccess(response.body().msg);
                    }
                });
    }
}
