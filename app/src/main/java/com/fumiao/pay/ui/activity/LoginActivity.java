package com.fumiao.pay.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.main.LoginBean;
import com.fumiao.pay.event.LoginEvent;
import com.fumiao.pay.mvp.main.LoginPresenter;
import com.fumiao.pay.mvp.main.LoginView;
import com.fumiao.pay.widget.WebActivity;
import org.greenrobot.eventbus.EventBus;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import static com.fumiao.pay.widget.WebActivity.WEB_TITLE;
import static com.fumiao.pay.widget.WebActivity.WEB_URL;

public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.ed_user_account)
    EditText edUserAccount;
    @BindView(R.id.account_arrow)
    ImageView accountArrow;
    @BindView(R.id.ed_user_password)
    EditText edUserPassword;
    @BindView(R.id.password_status)
    ImageView icPasswordStatus;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.user_account_clear)
    ImageView userAccountClear;
    @BindView(R.id.password_clear)
    ImageView passwordClear;

    //密码是否显示和隐藏标识
    private boolean isShowPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        edUserAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edUserAccount.getText().toString().trim().isEmpty()){
                    userAccountClear.setVisibility(View.GONE);
                }else {
                    userAccountClear.setVisibility(View.VISIBLE);
                }
                if (edUserAccount.getText().toString().trim().isEmpty() || edUserPassword.getText().toString().trim().isEmpty()) {
                    btnLogin.setBackgroundResource(R.drawable.btn_nopress_bg);
                } else {
                    btnLogin.setBackgroundResource(R.drawable.btn_press_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edUserPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(edUserPassword.getText().toString().trim().isEmpty()){
                    passwordClear.setVisibility(View.GONE);
                }else {
                    passwordClear.setVisibility(View.VISIBLE);
                }
                if (edUserAccount.getText().toString().trim().isEmpty() || edUserPassword.getText().toString().trim().isEmpty()) {
                    btnLogin.setBackgroundResource(R.drawable.btn_nopress_bg);
                } else {
                    btnLogin.setBackgroundResource(R.drawable.btn_press_bg);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //保存账号和密码
        edUserAccount.setText(SPUtil.getInstance().getString(SHOPKEEPER_PHONE));
        edUserPassword.setText(SPUtil.getInstance().getString(SHOPKEEPER_PASSWORD));
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this, this);
    }

    @Override
    public void loginSuccess(LoginBean data) {
        ToastUitl.show(getString(R.string.login_success));
        SPUtil.getInstance().putInt(LOGIN_ID, data.getInfo().getId());
        SPUtil.getInstance().putString(KEY, data.getInfo().getChk_key());
        //type 1：店长登录 2：店员登录
        if (data.getInfo().getType() == 2) {
            SPUtil.getInstance().putBoolean(IS_MENBER, true);
        } else {
            SPUtil.getInstance().putBoolean(IS_MENBER, false);
        }
        SPUtil.getInstance().putInt(STORE_ID, data.getInfo().getStore_id());
        SPUtil.getInstance().putInt(IS_BOSS, data.getInfo().getIs_boss());
        SPUtil.getInstance().putString(SHOPKEEPER_PHONE, edUserAccount.getText().toString());
        SPUtil.getInstance().putString(SHOPKEEPER_PASSWORD, edUserPassword.getText().toString());
        SPUtil.getInstance().putString(USER_PHONE, data.getInfo().getPhone());
        SPUtil.getInstance().putBoolean(STORE_PAY_SWITCH, data.getInfo().getPay_switch() == 1 ? true : false);
        SPUtil.getInstance().putBoolean(VOICE_SWITCH, data.getInfo().getVoice_remind_switch() == 1 ? true : false);
        SPUtil.getInstance().putBoolean(SWITCH_PUSH, data.getInfo().getPush_switch() == 1 ? true : false);
        SPUtil.getInstance().putBoolean(SWITCH_NOTICE, data.getInfo().getPush_detail() == 1 ? true : false);
//        AppManager.getAppManager().backToTag(MAIN_PAGE);
        jumpActivity(MainActivity.class);
        EventBus.getDefault().post(new LoginEvent(LOGIN_SUCCESS));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 过滤按键动作
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.btn_login, R.id.password_status, R.id.btn_service_agreement, R.id.user_account_clear, R.id.password_clear, R.id.tv_forget_pd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (edUserAccount.getText().toString().isEmpty() || edUserPassword.getText().toString().isEmpty()) {
                    ToastUitl.show(getString(R.string.account_password_empty_tips));
                    return;
                }
                mvpPresenter.login(edUserAccount.getText().toString(), edUserPassword.getText().toString());
                break;

            case R.id.password_status:
                isShowPassword = isShowPassword ? false : true;
                updatePasswordView(isShowPassword);
                break;
            case R.id.btn_service_agreement:
                Bundle bundle = new Bundle();
                bundle.putString(WEB_TITLE, "福喵用户服务协议");
                bundle.putString(WEB_URL, REGISTRATION_PROTOCOL);
                jumpActivity(WebActivity.class, bundle);
                break;

            case R.id.user_account_clear:
                userAccountClear.setVisibility(View.GONE);
                edUserAccount.setText("");
                btnLogin.setBackgroundResource(R.drawable.btn_nopress_bg);
                break;
            case R.id.password_clear:
                passwordClear.setVisibility(View.GONE);
                edUserPassword.setText("");
                btnLogin.setBackgroundResource(R.drawable.btn_nopress_bg);
                break;
            case R.id.tv_forget_pd:
                jumpActivity(FindPasswordActivity.class);
                break;
        }
    }


    private void updatePasswordView(boolean isShowPassword) {
        if (isShowPassword) {
            //显示密码
            edUserPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            icPasswordStatus.setImageResource(R.mipmap.ic_show_pd);
        } else {
            //隐藏密码
            edUserPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            icPasswordStatus.setImageResource(R.mipmap.ic_hide_pd);
        }
        edUserPassword.setSelection(edUserPassword.getText().length());
    }
}
