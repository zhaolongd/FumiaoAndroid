package com.fumiao.pay.mvp.me;

import android.app.Activity;
import android.graphics.Bitmap;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.RsaUtils;
import com.fumiao.pay.bean.main.PhoneCodeBean;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by chee on 2018/9/12.
 */
public class ChangePasswordPresenter extends BasePresenter<ChangePasswordView> {
    public ChangePasswordPresenter(ChangePasswordView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void getImgCode() {
        OkGo.<Bitmap>get(IMG_CODE)
                .tag(activity).params("id", "phone_code")
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Response<Bitmap> response) {
                        mvpView.showImgCode(response.body());
                    }
                });
    }

    public void getPhoneCode(String phone, String img_code) {
        OkGo.<BaseResponse<PhoneCodeBean>>post(GET_PHONE_CODE)
                .params("phone", phone)
                .params("code", img_code)
                .params("source", "editpwd")
                .execute(new JsonCallback<BaseResponse<PhoneCodeBean>>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse<PhoneCodeBean>> response) {
                        if (mvpView != null && response.body().data != null) {
                            mvpView.codeState(response.body().msg, response.body().data);
                        }
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
                        if (mvpView != null) {
                            mvpView.changeSuccess(response.body().msg);
                        }
                    }
                });
    }

    public void changeManagerPassword(String phone, String code, String manager_pwd, String reg_sign) {
        OkGo.<BaseResponse>post(CHANGE_MANAGER_PASSWORD)
                .params("phone", phone)
                .params("code", code)
                .params("manager_pwd", RsaUtils.encryptByPublicKey(manager_pwd))
                .params("reg_sign", reg_sign)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        if (mvpView != null) {
                            mvpView.changeSuccess(response.body().msg);
                        }
                    }
                });
    }


}
