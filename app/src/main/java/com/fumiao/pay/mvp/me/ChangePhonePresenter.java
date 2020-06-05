package com.fumiao.pay.mvp.me;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.RsaUtils;
import com.fumiao.pay.bean.main.PhoneCodeBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import static com.fumiao.core.uitls.RsaUtils.encryptByPublicKey;

/**
 * Author: XieBoss
 * Date: 2019/8/17 0017 14:15
 *
 * @Description:
 */
public class ChangePhonePresenter extends BasePresenter<ChangePhoneView> {
    public ChangePhonePresenter(ChangePhoneView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getVerificationCode(String phone) {
        OkGo.<BaseResponse<PhoneCodeBean>>post(GET_PHONE_CODE)
                .params("phone", phone)
                .execute(new JsonCallback<BaseResponse<PhoneCodeBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<PhoneCodeBean>> response) {
                        mvpView.codeState(response.body().msg, response.body().data);
                    }
                });
    }

    public void changePhone(String phone, String code,String manager_pwd,String used_phone) {
        OkGo.<BaseResponse>post(CHANGE_PHONE)
                .params("phone", phone)
                .params("code", code)
                .params("manager_pwd", RsaUtils.encryptByPublicKey(manager_pwd))
                .params("used_phone", used_phone)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        mvpView.changeSuccess(response.body().msg);
                    }
                });
    }
}
