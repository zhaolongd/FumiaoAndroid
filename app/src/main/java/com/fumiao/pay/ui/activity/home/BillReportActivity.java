package com.fumiao.pay.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fumiao.core.uitls.SPUtil;
import com.fumiao.pay.R;
import com.fumiao.pay.app.BaseActivity;
import com.fumiao.pay.event.BillRefreshEvent;
import com.fumiao.pay.event.OrdersRefreshEvent;
import com.fumiao.pay.ui.activity.store.SelectStoreActivity;
import com.fumiao.pay.ui.fragment.DayBillReportFragment;
import com.fumiao.pay.ui.fragment.MonthBillReportFragment;
import com.fumiao.pay.ui.fragment.WeekBillReportFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BillReportActivity extends BaseActivity {

    @BindView(R.id.day_report_line)
    TextView dayReportLine;
    @BindView(R.id.week_report_line)
    TextView weekReportLine;
    @BindView(R.id.month_report_line)
    TextView monthReportLine;
    @BindView(R.id.bill_content)
    FrameLayout billContent;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;

    DayBillReportFragment dayDayBillReportFragment;
    WeekBillReportFragment weekDayBillReportFragment;
    MonthBillReportFragment monthDayBillReportFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private int SELECTED_STORE_REQUEST = 556;
    private int store_id;
    private String store_name;
    private int is_boss; //1:老板账号

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_report, false);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init() {
        store_id = SPUtil.getInstance().getInt(STORE_ID);
        is_boss = SPUtil.getInstance().getInt(IS_BOSS);
        if(is_boss == 1){
            ivArrow.setVisibility(View.VISIBLE);
        }else {
            ivArrow.setVisibility(View.GONE);
        }
        store_name = SPUtil.getInstance().getString(STORE_NAME);
        tvTitle.setText(store_name+ "报表");
        fragmentManager = getSupportFragmentManager();
        setMyTabHost(1);
    }

    @OnClick({R.id.day_bill, R.id.week_bill, R.id.month_bill, R.id.iv_back, R.id.rl_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.day_bill:
                setMyTabHost(1);
                break;
            case R.id.week_bill:
                setMyTabHost(2);
                break;
            case R.id.month_bill:
                setMyTabHost(3);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_title:
                if(is_boss == 1){
                    Bundle bundle = new Bundle();
                    bundle.putInt("store_id", store_id);
                    jumpActivityForResult(SelectStoreActivity.class, bundle, SELECTED_STORE_REQUEST);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECTED_STORE_REQUEST) {
                store_id = data.getIntExtra("store_id", 0);
                store_name = data.getStringExtra("store_name");
                tvTitle.setText(store_name+ "报表");
                EventBus.getDefault().post(new BillRefreshEvent(store_id));
            }
        }
    }

    private void setMyTabHost(int index) {
        transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        clearText();
        switch (index) {
            case 1:
                dayReportLine.setVisibility(View.VISIBLE);
                if (dayDayBillReportFragment == null) {
                    dayDayBillReportFragment = new DayBillReportFragment();
                    transaction.add(R.id.bill_content, dayDayBillReportFragment);
                } else {
                    transaction.show(dayDayBillReportFragment);
                }
                break;
            case 2:
                weekReportLine.setVisibility(View.VISIBLE);
                if (weekDayBillReportFragment == null) {
                    weekDayBillReportFragment = new WeekBillReportFragment();
                    transaction.add(R.id.bill_content, weekDayBillReportFragment);
                } else {
                    transaction.show(weekDayBillReportFragment);
                }
                break;
            case 3:
                monthReportLine.setVisibility(View.VISIBLE);
                if (monthDayBillReportFragment == null) {
                    monthDayBillReportFragment = new MonthBillReportFragment();
                    transaction.add(R.id.bill_content, monthDayBillReportFragment);
                } else {
                    transaction.show(monthDayBillReportFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void clearText() {
        dayReportLine.setVisibility(View.INVISIBLE);
        weekReportLine.setVisibility(View.INVISIBLE);
        monthReportLine.setVisibility(View.INVISIBLE);
    }

    /**
     * 隐藏所有的fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (dayDayBillReportFragment != null) {
            transaction.hide(dayDayBillReportFragment);
        }
        if (weekDayBillReportFragment != null) {
            transaction.hide(weekDayBillReportFragment);
        }
        if (monthDayBillReportFragment != null) {
            transaction.hide(monthDayBillReportFragment);
        }
    }
}
