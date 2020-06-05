package com.fumiao.pay.mvp.me;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

/**
 * Created by chee on 2018/9/12.
 */
public class SettingPresenter extends BasePresenter<SettingView> {
    public SettingPresenter(SettingView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void logout() {
        OkGo.<BaseResponse>get(LOGOUT)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        if (mvpView != null) {
                            mvpView.logoutSuccess();
                        }
                    }
                });
    }

    public void paySwitch(final int pay_switch) {
        OkGo.<BaseResponse>post(STORE_PAY)
                .params("field","pay_switch")
                .params("value", pay_switch)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        SPUtil.getInstance().putBoolean(STORE_PAY_SWITCH, pay_switch == 1 ? true:false );
                        ToastUitl.show("设置成功！");
                    }
                });
    }

    public void payPushSwitch(final int push_switch) {
        OkGo.<BaseResponse>post(STORE_PAY)
                .params("field","push_switch")
                .params("value", push_switch)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        SPUtil.getInstance().putBoolean(SWITCH_PUSH, push_switch == 1 ? true:false);
                        ToastUitl.show("设置成功！");
                    }
                });
    }

    public void voiceRemindSwitch(final int voice_remind_switch) {
        OkGo.<BaseResponse>post(STORE_PAY)
                .params("field","voice_remind_switch")
                .params("value", voice_remind_switch)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        SPUtil.getInstance().putBoolean(VOICE_SWITCH, voice_remind_switch  == 1 ? true:false);
                        ToastUitl.show("设置成功！");
                    }
                });
    }

    public void payPushDetails(final int push_detail) {
        OkGo.<BaseResponse>post(STORE_PAY)
                .params("field","push_detail")
                .params("value", push_detail)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        SPUtil.getInstance().putBoolean(SWITCH_NOTICE, push_detail  == 1 ? true:false);
                        ToastUitl.show("设置成功！");
                    }
                });
    }
}
