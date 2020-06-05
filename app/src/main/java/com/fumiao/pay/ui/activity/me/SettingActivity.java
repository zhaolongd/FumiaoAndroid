package com.fumiao.pay.ui.activity.me;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.fumiao.core.uitls.AppManager;
import com.fumiao.core.uitls.AppUtils;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.widget.ItemArrowLayout;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.event.LoginEvent;
import com.fumiao.pay.mvp.me.SettingPresenter;
import com.fumiao.pay.mvp.me.SettingView;
import com.fumiao.pay.ui.activity.LoginActivity;
import com.fumiao.pay.ui.activity.store.StoreSettingActivity;
import com.fumiao.pay.ui.dialog.MsgDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chee on 2018/8/30.
 */
public class SettingActivity extends MvpActivity<SettingPresenter> implements SettingView {


    @BindView(R.id.btn_store_msg)
    ItemArrowLayout storeLoginid;
    @BindView(R.id.btn_change_phone)
    ItemArrowLayout btnChangePhone;
    @BindView(R.id.btn_change_pwd)
    ItemArrowLayout btnChangePwd;
    @BindView(R.id.switch_pay)
    Switch switchPay;
    @BindView(R.id.switch_pay_layout)
    LinearLayout switchPayLayout;
    @BindView(R.id.switch_voice)
    Switch switchVoice;
    @BindView(R.id.switch_notice)
    Switch switchNotice;
    @BindView(R.id.login_out)
    Button btnLogout;
    @BindView(R.id.btn_clear_app)
    ItemArrowLayout btnClearApp;
    @BindView(R.id.btn_change_manager_pwd)
    ItemArrowLayout btnChangeManagerPwd;
    @BindView(R.id.btn_about_version)
    ItemArrowLayout btnAboutVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter(this, this);
    }

    private void init() {
        setTitle(getString(R.string.setting));
        btnClearApp.setDes(AppUtils.getTotalCacheSize(this));
        storeLoginid.setDes(SPUtil.getInstance().getString(SHOPKEEPER_PHONE));
        boolean isMember = SPUtil.getInstance().getBoolean(IS_MENBER);
        if (isMember) {
            btnChangePhone.setVisibility(View.GONE);
            btnChangeManagerPwd.setVisibility(View.GONE);
            switchPayLayout.setVisibility(View.GONE);
        }
        msgDialog = new MsgDialog(this);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgDialog.show(getString(R.string.login_out), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mvpPresenter.logout();
                        msgDialog.dismiss();
                    }
                });
            }
        });

        //支付开关
        switchPay.setChecked(SPUtil.getInstance().getBoolean(this, STORE_PAY_SWITCH, true));
        switchPay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mvpPresenter.paySwitch(isChecked ? 1 : 0);
            }
        });
        //收款语音提醒
        switchVoice.setChecked(SPUtil.getInstance().getBoolean(this, VOICE_SWITCH, true));
        switchVoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mvpPresenter.voiceRemindSwitch(isChecked ? 1 : 0);
            }
        });

        //通知栏显示信息详情
        switchNotice.setChecked(SPUtil.getInstance().getBoolean(this, SWITCH_NOTICE, true));
        switchNotice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mvpPresenter.payPushDetails(isChecked ? 1 : 0);
            }
        });
        btnAboutVersion.setDes("版本 " + AppUtils.getVersionName(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        hintLoad();
    }


    MsgDialog msgDialog;

    @OnClick({R.id.btn_store_msg, R.id.btn_change_phone, R.id.btn_change_pwd, R.id.btn_change_manager_pwd, R.id.btn_clear_app, R.id.btn_about_version})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_store_msg:
                Bundle bundle = new Bundle();
                bundle.putInt("store_id", SPUtil.getInstance().getInt(STORE_ID));
                jumpActivity(StoreSettingActivity.class, bundle);
                break;
            case R.id.btn_change_phone:
                jumpActivity(ChangePhoneActivity.class);
                break;
            case R.id.btn_change_pwd:
                Bundle bundle2 = new Bundle();
                bundle2.putString("phone", SPUtil.getInstance().getString(USER_PHONE));
                jumpActivity(ChangePasswordActivity.class, bundle2);
                break;
            case R.id.btn_clear_app:
                msgDialog.show(getString(R.string.clear_all), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppUtils.clearAllCache(SettingActivity.this);
                        ToastUitl.success(getString(R.string.clear_success));
                        btnClearApp.setDes("0KB");
                        msgDialog.dismiss();
                    }
                });
                break;
            case R.id.btn_change_manager_pwd:
                Bundle bundle3 = new Bundle();
                bundle3.putString("phone", SPUtil.getInstance().getString(USER_PHONE));
                bundle3.putBoolean("change_manager_pwd", true);
                jumpActivity(ChangePasswordActivity.class, bundle3);
                break;
            case R.id.btn_about_version:
                jumpActivity(AboutActivity.class);
                break;
        }
    }

    @Override
    public void logoutSuccess() {
        ToastUitl.show(getString(R.string.logout_success));
        clearLoginData();
        AppManager.getAppManager().backToTag(MAIN_PAGE);
        EventBus.getDefault().post(new LoginEvent(LOGIN_EXIT));
        jumpActivity(LoginActivity.class);
    }

    private void clearLoginData() {
        SPUtil.getInstance().putInt(LOGIN_ID, 0);
        SPUtil.getInstance().putInt(STORE_ID, 0);
        AppUtils.clearAllCache(SettingActivity.this);
        btnClearApp.setDes("0KB");
    }

}
