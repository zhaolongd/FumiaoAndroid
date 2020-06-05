package com.fumiao.pay.ui.activity.me;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fumiao.core.uitls.ApiSecurityUtils;
import com.fumiao.core.uitls.AppManager;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.widget.ClearText;
import com.fumiao.core.widget.KeyEditText;
import com.fumiao.core.widget.SmsCodeView;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.main.PhoneCodeBean;
import com.fumiao.pay.event.LoginEvent;
import com.fumiao.pay.mvp.me.ChangePhonePresenter;
import com.fumiao.pay.mvp.me.ChangePhoneView;
import com.fumiao.pay.ui.activity.LoginActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chee on 2018/8/30.
 */
public class ChangePhoneActivity extends MvpActivity<ChangePhonePresenter> implements ChangePhoneView {


    @BindView(R.id.new_user_phone)
    KeyEditText newUserPhone;
    @BindView(R.id.new_phone_code)
    KeyEditText newPhoneCode;
    @BindView(R.id.btn_get_phone_code)
    SmsCodeView btnGetPhoneCode;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.new_psd)
    ClearText newPsd;
    private String reg_sign;
    private boolean isPhone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected ChangePhonePresenter createPresenter() {
        return new ChangePhonePresenter(this, this);
    }

    private void init() {
        setTitle("更换登录手机号");
        String phone = SPUtil.getInstance().getString(SHOPKEEPER_PHONE);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newUserPhone.getText().toString().isEmpty()) {
                    ToastUitl.show(newUserPhone.getHint().toString());
                    return;
                }
                if (newPhoneCode.getText().toString().isEmpty()) {
                    ToastUitl.show(newPhoneCode.getHint().toString());
                    return;
                }
                if (newPsd.getText().toString().isEmpty()) {
                    ToastUitl.show(newPsd.getHint().toString());
                    return;
                }
                String hmacString = ApiSecurityUtils.hmacSha256(ORIGIN_KEY, newUserPhone.getText().toString().trim());
                if(!hmacString.equals(reg_sign)){
                    ToastUitl.show(getString(R.string.phone_verification_mismatch));
                    return;
                }
                mvpPresenter.changePhone(newUserPhone.getText(), newPhoneCode.getText(), newPsd.getText(), phone);
            }
        });

    }

    @Override
    public void codeState(String msg, PhoneCodeBean data) {
        btnGetPhoneCode.start();
        reg_sign = data.getReg_sign();
        ToastUitl.show(msg);
    }

    @Override
    public void changeSuccess(String msg) {
        ToastUitl.show(msg);
        if (isPhone){
            clearLoginData();
            AppManager.getAppManager().backToTag(MAIN_PAGE);
            EventBus.getDefault().post(new LoginEvent(LOGIN_EXIT));
            jumpActivity(LoginActivity.class);
        }else {
            finish();
        }


    }

    private void clearLoginData() {
        SPUtil.getInstance().putInt(LOGIN_ID, 0);
        SPUtil.getInstance().putInt(STORE_ID, 0);
    }

    @OnClick(R.id.btn_get_phone_code)
    public void onViewClicked() {
        mvpPresenter.getVerificationCode(newUserPhone.getText());
    }

}
