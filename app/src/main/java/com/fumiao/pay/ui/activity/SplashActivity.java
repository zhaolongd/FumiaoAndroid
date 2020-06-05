package com.fumiao.pay.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import com.fumiao.core.uitls.AppUtils;
import com.fumiao.core.uitls.Callback;
import com.fumiao.core.uitls.UpdateUtil;
import com.fumiao.pay.R;
import com.fumiao.pay.app.BaseActivity;


public class SplashActivity extends BaseActivity{
    boolean isFirstIn = false;
    private boolean isFirst = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.isTaskRoot()) {
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                finish();
                return;
            }
        }
        setContentView(R.layout.activity_splash,false);
        setTag(SPLASH_PAGE);
        
        final SharedPreferences sharedPreferences = getSharedPreferences("is_first_in_data",MODE_PRIVATE);
        isFirstIn = sharedPreferences.getBoolean("isFirstIn",true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateUtil.getSingle().checkVersion(this, BASE_URL, CHECK_UPDATE, AppUtils.getVersionName(this), false, new Callback() {
            @Override
            public void onSuccess(Object[] t) {
                if(isFirst){
                    isFirst = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (isFirstIn) {
                                jumpActivity(GuideActivity.class);
                                finish();
                            } else {
                                jumpActivity(MainActivity.class);
                                finish();
                            }
                        }
                    }, 1000);
                }
            }
        });
    }
}
