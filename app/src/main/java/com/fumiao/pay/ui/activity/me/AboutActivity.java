package com.fumiao.pay.ui.activity.me;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.fumiao.core.uitls.AppUtils;
import com.fumiao.core.uitls.Callback;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.uitls.UpdateUtil;
import com.fumiao.pay.R;
import com.fumiao.pay.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {
    @BindView(R.id.btn_check_version)
    Button btnCheckVersion;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setTitle(getString(R.string.about_app));
        tvVersion.setText(getString(R.string.current_version) + AppUtils.getVersionName(this));
    }

    @OnClick(R.id.btn_check_version)
    public void onViewClicked() {
        UpdateUtil.getSingle().checkVersion(this, BASE_URL, CHECK_UPDATE, AppUtils.getVersionName(this), true, new Callback() {
            @Override
            public void onSuccess(Object[] t) {
                if (t[0].equals("")) {
                    ToastUitl.show(getString(R.string.version_tips));
                }
            }
        });
    }
}
