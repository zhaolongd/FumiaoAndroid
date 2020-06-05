package com.fumiao.pay.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.fumiao.core.uitls.StatusBarUtils;
import com.fumiao.pay.R;
import com.fumiao.pay.ui.adapter.ViewPageAdapter;
import com.fumiao.pay.ui.fragment.GuideFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author XieBoss
 * @version 1.0
 * @date 2019/8/18 16:05
 */
public class GuideActivity extends FragmentActivity {
    @BindView(R.id.viewpage)
    ViewPager mViewPager;
    private List<Fragment> mFragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        StatusBarUtils.changeToLightStatusBar(this);

        //创建Fragment
        for (int i = 0; i < 3; i++){
            GuideFragment guideFragment = new GuideFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index",i);
            guideFragment.setArguments(bundle);
            mFragments.add(guideFragment);
        }
        mViewPager.setAdapter(new ViewPageAdapter(getSupportFragmentManager(), mFragments));
    }

}
