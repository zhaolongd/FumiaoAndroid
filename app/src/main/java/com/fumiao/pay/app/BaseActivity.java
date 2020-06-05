package com.fumiao.pay.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.fumiao.core.app.CoreActivity;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.StatusBarUtils;
import com.fumiao.core.widget.LoadingDialog;
import com.fumiao.pay.R;
import com.fumiao.pay.config.HttpConfig;
import com.fumiao.pay.config.KeyConfig;
import com.fumiao.pay.event.LoginEvent;
import com.fumiao.pay.ui.activity.LoginActivity;
import com.fumiao.pay.ui.dialog.MsgDialog;

import org.greenrobot.eventbus.EventBus;

import qiu.niorgai.StatusBarCompat;

public class BaseActivity extends CoreActivity implements HttpConfig, KeyConfig {
    private boolean isScroll = true;
    public ImageView baseLeft, baseRight;
    public TextView baseTitle, baseRightText;
    public ViewGroup root, baseTitleLayout;
    public FrameLayout contentView;
    public View topMod, leftView, rightView;
    private LoadingDialog loadingDialog;
    public int store_id;
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    public final Object mHandlerToken = hashCode();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(this);
        store_id = SPUtil.getInstance().getInt(STORE_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        store_id = SPUtil.getInstance().getInt(STORE_ID);
    }

    public void setContentView(View view, boolean isScroll) {
        this.isScroll = isScroll;
        setContentView(view);
    }

    public void setContentView(int layoutResID, boolean isScroll) {
        this.isScroll = isScroll;
        setContentView(layoutResID);
    }

    public void setContentViewNative(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        root = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_base, null);
        topMod = root.findViewById(R.id.top_mod);
        leftView = root.findViewById(R.id.left_view);
        rightView = root.findViewById(R.id.right_view);
        if (isScroll) {
            contentView = root.findViewById(R.id.base_scroll_content);
            contentView.setVisibility(View.VISIBLE);
        } else {
            contentView = root.findViewById(R.id.base_content);
            contentView.setVisibility(View.VISIBLE);
        }
        contentView.addView(view);
        initTitle();
        super.setContentView(root);
    }

    @Override
    public void setContentView(int layoutResID) {
        root = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_base, null);
        topMod = root.findViewById(R.id.top_mod);
        leftView = root.findViewById(R.id.left_view);
        rightView = root.findViewById(R.id.right_view);
        if (isScroll) {
            contentView = root.findViewById(R.id.base_scroll_content);
            NestedScrollView scrollView = root.findViewById(R.id.base_scroll);
            scrollView.setVisibility(View.VISIBLE);
            contentView.setVisibility(View.VISIBLE);
        } else {
            contentView = root.findViewById(R.id.base_content);
            contentView.setVisibility(View.VISIBLE);
        }
        contentView.addView(LayoutInflater.from(this).inflate(layoutResID, null));
        initTitle();
        super.setContentView(root);
        StatusBarUtils.changeToLightStatusBar(this);
    }

    private void initTitle() {
        if (root == null) {
            return;
        }
        baseLeft = root.findViewById(R.id.base_left);
        baseTitle = root.findViewById(R.id.base_title);
        baseRight = root.findViewById(R.id.base_right);
        baseRightText = root.findViewById(R.id.base_right_text);
        baseTitleLayout = root.findViewById(R.id.base_title_layout);
        baseLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setTitle(int titleRid) {
        setTitle(getString(titleRid));
    }

    public void setTitle(String title) {
        if (baseTitle != null) {
            baseTitle.setText(title);
            baseTitleLayout.setVisibility(View.VISIBLE);
            topMod.setVisibility(View.VISIBLE);
        }
    }

    public void setTitle(String title, View.OnClickListener clickListener) {
        if (baseTitle != null) {
            baseTitle.setText(title);
            baseTitleLayout.setVisibility(View.VISIBLE);
            topMod.setVisibility(View.VISIBLE);
            baseTitle.setOnClickListener(clickListener);
        }
    }

    public void setRight(int icon, View.OnClickListener clickListener) {
        baseRight.setImageResource(icon);
        baseRight.setOnClickListener(clickListener);
        baseRight.setVisibility(View.VISIBLE);
    }

    public void setRight(String rightText, View.OnClickListener clickListener) {
        baseRightText.setText(rightText);
        baseRightText.setTextSize(16);
        baseRightText.setTextColor(getResources().getColor(R.color.edit_content));
        baseRightText.setOnClickListener(clickListener);
        baseRightText.setVisibility(View.VISIBLE);
    }

    public void setLeft(int icon, View.OnClickListener clickListener) {
        baseLeft.setImageResource(icon);
        baseLeft.setOnClickListener(clickListener);
        baseLeft.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoad() {
        if (this.isFinishing()) {
            return;
        }
        handler.removeCallbacks(showLoading);
        loadingDialog.show();
    }

    Handler handler = new Handler();
    Thread showLoading = new Thread() {
        @Override
        public void run() {
            super.run();
            loadingDialog.dismiss();
        }
    };

    @Override
    public void hintLoad() {
        if (this.isFinishing()) {
            return;
        }
        //延迟100毫秒关闭，避免连续请求 出现闪屏情况
        handler.postDelayed(showLoading, 300);
    }

    /**
     * 延迟执行
     */
    public final boolean post(Runnable r) {
        return postDelayed(r, 0);
    }


    /**
     * 延迟一段时间执行
     */
    public final boolean postDelayed(Runnable r, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return postAtTime(r, SystemClock.uptimeMillis() + delayMillis);
    }

    /**
     * 在指定的时间执行
     */
    public final boolean postAtTime(Runnable r, long uptimeMillis) {
        // 发送和这个 Activity 相关的消息回调
        return HANDLER.postAtTime(r, mHandlerToken, uptimeMillis);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadingDialog.dismiss();
    }


    MsgDialog msgDialog;
    @Override
    public void toLogin() {
        SPUtil.getInstance().putInt(LOGIN_ID, 0);
        SPUtil.getInstance().putInt(STORE_ID, 0);
        SPUtil.getInstance().putString(SHOPKEEPER_PHONE, "");
        SPUtil.getInstance().putString(SHOPKEEPER_PASSWORD, "");
        if (msgDialog!=null&&msgDialog.isShowing()){
            return;
        }
        msgDialog = new MsgDialog(this);
        if(!isFinishing()){
            msgDialog.show("你的手机在另一个地方登录，请重新登录", "", "重新登录", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jumpActivity(LoginActivity.class);
                    EventBus.getDefault().post(new LoginEvent(LOGIN_EXIT));
                    msgDialog.dismiss();
                }
            });
        }
    }
}
