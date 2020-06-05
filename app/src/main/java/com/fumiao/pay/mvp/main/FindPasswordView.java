package com.fumiao.pay.mvp.main;

import com.fumiao.pay.bean.main.PhoneCodeBean;
import com.fumiao.pay.mvp.base.BaseView;


public interface FindPasswordView extends BaseView {

    void findSuccess(String msg);

    void codeState(String msg, PhoneCodeBean data);
}
