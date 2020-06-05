package com.fumiao.pay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpFragment;
import com.fumiao.pay.bean.me.HelpCenterBean;
import com.fumiao.pay.mvp.msg.MsgPresenter;
import com.fumiao.pay.mvp.msg.MsgView;
import com.fumiao.pay.ui.activity.msg.StationInformActivity;
import com.fumiao.pay.widget.WebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import static com.fumiao.pay.widget.WebActivity.WEB_TITLE;
import static com.fumiao.pay.widget.WebActivity.WEB_URL;

public class MsgFragment extends MvpFragment<MsgPresenter> implements MsgView {
    Unbinder unbinder;
    @BindView(R.id.rl_service)
    RelativeLayout mRlService;
    @BindView(R.id.rl_inform)
    RelativeLayout mRlInform;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_msg, null);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    protected MsgPresenter createPresenter() {
        return new MsgPresenter(this, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_service, R.id.rl_inform})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_service:
                Bundle bundle = new Bundle();
                bundle.putString(WEB_TITLE, "福喵客服");
                bundle.putString(WEB_URL, MY_SERVICE);
                jumpActivity(WebActivity.class, bundle);
                break;
            case R.id.rl_inform:
                jumpActivity(StationInformActivity.class);
                break;
        }
    }

    @Override
    public void showInstationList(HelpCenterBean data) {

    }

    @Override
    public void loadArticleDataFail() {

    }
}
