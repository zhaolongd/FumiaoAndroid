package com.fumiao.pay.ui.activity.me;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
import com.fumiao.pay.mvp.me.ChangePasswordPresenter;
import com.fumiao.pay.mvp.me.ChangePasswordView;
import com.fumiao.pay.ui.activity.LoginActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chee on 2018/8/30.
 */
public class ChangePasswordActivity extends MvpActivity<ChangePasswordPresenter> implements ChangePasswordView {

    @BindView(R.id.user_phone)
    KeyEditText userPhone;
    @BindView(R.id.phone_code)
    KeyEditText phoneCode;
    @BindView(R.id.login_password)
    ClearText loginPassword;
    @BindView(R.id.login_password1)
    ClearText loginPassword1;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.ed_img_code)
    EditText edImgCode;
    @BindView(R.id.img_code)
    ImageView imgCode;
    @BindView(R.id.btn_get_phone_code)
    SmsCodeView btnGetPhoneCode;

    private boolean isChangePassword = false;
    private String reg_sign;
    private boolean isChangeManagerPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected ChangePasswordPresenter createPresenter() {
        return new ChangePasswordPresenter(this, this);
    }

    private void init() {
        setTitle("找回密码");
        String phone = getIntent().getStringExtra("phone");
        isChangeManagerPwd = getIntent().getBooleanExtra("change_manager_pwd", false);
        userPhone.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        if (phone != null && !"".equals(phone)) {
            userPhone.getEditText().setFocusable(false);
            userPhone.setText(phone);
            if(isChangeManagerPwd){
                setTitle("修改安全密码");
            }else {
                setTitle("修改密码");
            }
            isChangePassword = true;
        }
        //mvpPresenter.getImgCode();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneCode.getText().toString().isEmpty()) {
                    ToastUitl.show(phoneCode.getHint().toString());
                    return;
                }
                if (loginPassword.getText().length()<=5){
                    ToastUitl.show("密码不能少于六位数");
                    return;
                }
                if (!loginPassword.getText().equals(loginPassword1.getText())) {
                    ToastUitl.show("两次密码不一致，请确认后重新输入");
                    return;
                }

                String hmacString = ApiSecurityUtils.hmacSha256(ORIGIN_KEY, userPhone.getText().toString().trim());
                if(!hmacString.equals(reg_sign)){
                    ToastUitl.show("手机号与验证码不匹配");
                    return;
                }
                if(isChangeManagerPwd){
                    mvpPresenter.changeManagerPassword(userPhone.getText(), phoneCode.getText(), loginPassword1.getText(), reg_sign);
                }else {
                    mvpPresenter.changePassword(userPhone.getText(), phoneCode.getText(), loginPassword1.getText(), reg_sign);
                }

            }
        });


       /* imgCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.getImgCode();
            }
        });*/
        btnGetPhoneCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.getPhoneCode(userPhone.getText(), edImgCode.getText().toString());
            }
        });
    }

    @Override
    public void changeSuccess(String msg) {
        ToastUitl.show(msg);
        if(isChangePassword && !isChangeManagerPwd){
            clearLoginData();
            AppManager.getAppManager().backToTag(MAIN_PAGE);
            EventBus.getDefault().post(new LoginEvent(LOGIN_EXIT));
            jumpActivity(LoginActivity.class);
        }else {
            finish();
        }
    }

    private void clearLoginData(){
        SPUtil.getInstance().putInt(LOGIN_ID, 0);
        SPUtil.getInstance().putInt(STORE_ID, 0);
    }

    @Override
    public void showImgCode(Bitmap body) {
        imgCode.setImageBitmap(body);
    }

    @Override
    public void codeState(String msg, PhoneCodeBean data) {
        btnGetPhoneCode.start();
        ToastUitl.show(msg);
        reg_sign = data.getReg_sign();
    }

}
