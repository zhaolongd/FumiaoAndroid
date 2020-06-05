package com.fumiao.pay.mvp.me;

import android.graphics.Bitmap;

import com.fumiao.pay.bean.main.PhoneCodeBean;
import com.fumiao.pay.mvp.base.BaseView;

/**
 * Created by chee on 2018/9/12.
 */
public interface ChangePasswordView extends BaseView {
    void changeSuccess(String msg);

    void showImgCode(Bitmap body);

    void codeState(String msg, PhoneCodeBean data);
}
