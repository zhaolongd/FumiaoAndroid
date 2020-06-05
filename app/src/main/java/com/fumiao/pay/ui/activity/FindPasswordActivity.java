package com.fumiao.pay.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.fumiao.core.uitls.ApiSecurityUtils;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.widget.SmsCodeView;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.main.PhoneCodeBean;
import com.fumiao.pay.mvp.main.FindPasswordPresenter;
import com.fumiao.pay.mvp.main.FindPasswordView;
import com.fumiao.pay.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPasswordActivity extends MvpActivity<FindPasswordPresenter> implements FindPasswordView {

    @BindView(R.id.ed_phone)
    ClearEditText edPhone;
    @BindView(R.id.ed_phone_code)
    EditText edPhoneCode;
    @BindView(R.id.btn_get_phone_code)
    SmsCodeView btnGetPhoneCode;
    @BindView(R.id.ed_password)
    ClearEditText edPassword;
    @BindView(R.id.ed_confirm_password)
    ClearEditText edConfirmPassword;
    private String reg_sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        ButterKnife.bind(this);
    }

    @Override
    protected FindPasswordPresenter createPresenter() {
        return new FindPasswordPresenter(this, this);
    }

    @Override
    public void findSuccess(String msg) {
        ToastUitl.show(msg);
        finish();
    }

    @Override
    public void codeState(String msg, PhoneCodeBean data) {
        btnGetPhoneCode.start();
        ToastUitl.show(msg);
        reg_sign = data.getReg_sign();
    }

    @OnClick({R.id.btn_close, R.id.btn_confirm, R.id.btn_get_phone_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                finish();
                break;
            case R.id.btn_confirm:
                if (edPhone.isEmpty()) {
                    ToastUitl.show("请输入手机号");
                    return;
                }
                if (edPassword.getText().length()<=5){
                    ToastUitl.show("密码不能少于六位数");
                    return;
                }
                if (!edPassword.getText().equals(edConfirmPassword.getText())) {
                    ToastUitl.show("两次密码不一致，请确认后重新输入");
                    return;
                }

                String hmacString = ApiSecurityUtils.hmacSha256(ORIGIN_KEY, edPhone.getText().trim());
                if(!hmacString.equals(reg_sign)){
                    ToastUitl.show("手机号与验证码不匹配");
                    return;
                }
                mvpPresenter.changePassword(edPhone.getText().trim(), edPhoneCode.getText().toString().trim(), edConfirmPassword.getText(), reg_sign);
                break;
            case R.id.btn_get_phone_code:
                mvpPresenter.getPhoneCode(edPhone.getText());
                break;
        }
    }
}
