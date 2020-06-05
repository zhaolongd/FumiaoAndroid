package com.fumiao.pay.mvp.me;

import com.fumiao.pay.bean.main.PhoneCodeBean;

/**
 * Author: XieBoss
 * Date: 2019/8/17 0017 14:16
 *
 * @Description:
 */
public interface ChangePhoneView {

    void codeState(String msg, PhoneCodeBean data);
    void changeSuccess(String msg);

}
