package com.fumiao.pay.mvp.main;

import com.fumiao.pay.bean.main.LoginBean;
import com.fumiao.pay.mvp.base.BaseView;



public interface LoginView extends BaseView {
    void loginSuccess(LoginBean data);
}
