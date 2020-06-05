package com.fumiao.pay.mvp.main;

import android.app.Activity;
import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.RsaUtils;
import com.fumiao.pay.bean.main.LoginBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;



public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView mvpView, Activity activity) {
        super(mvpView, activity);
    }


    public void login(String username, String password) {
        OkGo.<BaseResponse<LoginBean>>post(REGISTER_LOGIN)
               .params("action_type", 1)
                .params("username", username)
                .params("password", RsaUtils.encryptByPublicKey(password))
                .execute(new JsonCallback<BaseResponse<LoginBean>>(activity,true, ORIGIN_KEY) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<LoginBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.loginSuccess(response.body().data);
                        }
                    }
                });
    }

}
