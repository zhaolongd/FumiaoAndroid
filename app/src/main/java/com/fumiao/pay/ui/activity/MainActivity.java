package com.fumiao.pay.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fumiao.core.uitls.AppManager;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.StatusBarUtils;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.store.StoreDetailsBean;
import com.fumiao.pay.event.HomeTopEvent;
import com.fumiao.pay.event.LoginEvent;
import com.fumiao.pay.mvp.main.MainPresenter;
import com.fumiao.pay.mvp.main.MainView;
import com.fumiao.pay.ui.fragment.HomeFragment;
import com.fumiao.pay.ui.fragment.MeFragment;
import com.fumiao.pay.ui.fragment.MsgFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    int login_id;
    int store_id;
    @BindView(R.id.main_content)
    FrameLayout mainContent;
    @BindView(R.id.btn_home)
    LinearLayout btnHome;
    @BindView(R.id.btn_msg)
    LinearLayout btnMsg;
    @BindView(R.id.btn_me)
    LinearLayout btnMe;
    HomeFragment homeFragment;
    MeFragment meFragment;
    MsgFragment msgFragment;
    FragmentManager fm = getSupportFragmentManager();
    @BindView(R.id.btn_home_top)
    LinearLayout btnHomeTop;
    //菜单栏选项到首页
    private boolean isReset = false;
    private boolean isMember = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, false);
        ButterKnife.bind(this);
        setTag(MAIN_PAGE);
        init();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this, this);
    }

    private void init() {
        StatusBarCompat.translucentStatusBar(this, true);
        EventBus.getDefault().register(this);
        select(0);
        mvpPresenter.init();
        login_id = SPUtil.getInstance().getInt(LOGIN_ID);
        isMember = SPUtil.getInstance().getBoolean(IS_MENBER);
        if (login_id == 0) {
            jumpActivity(LoginActivity.class);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        login_id = SPUtil.getInstance().getInt(LOGIN_ID);
        store_id = SPUtil.getInstance().getInt(STORE_ID);
        isMember = SPUtil.getInstance().getBoolean(IS_MENBER);
        if(isReset){
            select(0);
            isReset = false;
        }
        if (login_id != 0) {
            if(store_id != 0){
                mvpPresenter.getStoreDetails(store_id);
            }
        }
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), R.string.quit_app_tips,
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getAppManager().closeApp();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void select(int p) {
        FragmentTransaction transaction = fm.beginTransaction();
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (msgFragment != null) {
            transaction.hide(msgFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }

        btnReset();
        switch (p) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main_content, homeFragment).show(homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                changeBtnState(btnHome, true);
                StatusBarUtils.cancelLightStatusBar(this);
                break;
            case 1:
                if (msgFragment == null) {
                    msgFragment = new MsgFragment();
                    transaction.add(R.id.main_content, msgFragment).show(msgFragment);
                } else {
                    transaction.show(msgFragment);
                }
                changeBtnState(btnMsg, true);
                StatusBarUtils.changeToLightStatusBar(this);
                break;
            case 2:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.main_content, meFragment).show(meFragment);
                } else {
                    transaction.show(meFragment);
                }
                changeBtnState(btnMe, true);
                StatusBarUtils.cancelLightStatusBar(this);
                break;
        }
        transaction.commit();
    }

    private void btnReset() {
        changeBtnState(btnHome, false);
        changeBtnState(btnMsg, false);
        changeBtnState(btnMe, false);
        btnHomeTop.setVisibility(View.GONE);
        btnHome.setVisibility(View.VISIBLE);
    }

    private void changeBtnState(LinearLayout linearLayout, boolean state) {
        ImageView imageView = (ImageView) linearLayout.getChildAt(0);
        TextView textView = (TextView) linearLayout.getChildAt(1);
        imageView.setSelected(state);
        textView.setTextColor(getResources().getColor(state ? R.color.colorAccent : R.color.font_des));
    }


    public void onMenuClicks(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                select(0);
                break;
            case R.id.btn_msg:
                select(1);
                break;
            case R.id.btn_me:
                select(2);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(HomeTopEvent event) {
        //1:首页回到顶部显示 2：首页回到顶部隐藏
        if (event.getMsg() == 1) {
            if(btnHomeTop.getVisibility() == View.GONE){
                btnHomeTop.setVisibility(View.VISIBLE);
                btnHome.setVisibility(View.GONE);
            }
        } else if (event.getMsg() == 2) {
            if(btnHomeTop.getVisibility() == View.VISIBLE){
            btnHomeTop.setVisibility(View.GONE);
            btnHome.setVisibility(View.VISIBLE);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(LoginEvent event){
        if(event.getMsg() == LOGIN_SUCCESS){
            login_id = SPUtil.getInstance().getInt(LOGIN_ID);
            store_id = SPUtil.getInstance().getInt(STORE_ID);
            isMember = SPUtil.getInstance().getBoolean(IS_MENBER);
            mvpPresenter.initPushRegistrationId();
            if(isMember){
                if (meFragment != null) {
                    meFragment.init();
                }
            }
        }else if(event.getMsg() == LOGIN_EXIT){
            isReset = true;
        }
    }

    @OnClick(R.id.btn_home_top)
    public void onViewClicked() {
        EventBus.getDefault().post(new HomeTopEvent(3));
    }

    @Override
    public void showStoreDetails(StoreDetailsBean data) {
        refreshFragment(data);
    }

    public void refreshFragment(StoreDetailsBean data) {
        if (meFragment != null) {
            if(!isMember){
                meFragment.init();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState); //阻止activity保存fragment的状态
    }

    //重新让新的Fragment指向了原本未被销毁的fragment
    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        if(homeFragment == null && fragment instanceof HomeFragment){
            homeFragment = (HomeFragment)fragment;
        }
        if(msgFragment == null && fragment instanceof MsgFragment){
            msgFragment = (MsgFragment)fragment;
        }
        if(meFragment == null && fragment instanceof MeFragment){
            meFragment = (MeFragment)fragment;
        }
    }
}
