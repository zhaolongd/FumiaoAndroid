package com.fumiao.pay.ui.activity.home;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.fumiao.core.uitls.Callback;
import com.fumiao.core.uitls.DateUtils;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.widget.KeyEditText;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.home.ReconciliationScreenBean;
import com.fumiao.pay.mvp.home.ReconciliationScreenPresenter;
import com.fumiao.pay.mvp.home.ReconciliationScreenView;
import com.fumiao.pay.ui.adapter.ScreenAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReconciliationScreenActivity extends MvpActivity<ReconciliationScreenPresenter> implements ReconciliationScreenView {

    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_store)
    TextView tvStore;
    @BindView(R.id.store_rcy)
    RecyclerView storeRcy;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.pay_type_rcy)
    RecyclerView payTypeRcy;
    @BindView(R.id.tv_pay_way)
    TextView tvPayWay;
    @BindView(R.id.pay_way_rcy)
    RecyclerView payWayRcy;
    @BindView(R.id.tv_pay_status)
    TextView tvPayStatus;
    @BindView(R.id.pay_status_rcy)
    RecyclerView payStatusRcy;
    @BindView(R.id.btn_reset)
    TextView btnReset;
    @BindView(R.id.btn_comfirm)
    TextView btnComfirm;
    @BindView(R.id.rl_start_time)
    RelativeLayout rlStartTime;
    @BindView(R.id.rl_end_time)
    RelativeLayout rlEndTime;

    ScreenAdapter storeAdapter;
    ScreenAdapter payTypeAdapter;
    ScreenAdapter payWayAdapter;
    ScreenAdapter payStatusAdapter;

    ArrayList<ReconciliationScreenBean.StoreBean> storeScreen;
    ArrayList<ReconciliationScreenBean.PayTypeBean> payTypeScreen;
    ArrayList<ReconciliationScreenBean.PayWayBean> payWayScreen;
    ArrayList<ReconciliationScreenBean.PayStatusBean> payStatusScreen;

    ArrayList<ReconciliationScreenBean.StoreBean> store = new ArrayList<>();
    ArrayList<ReconciliationScreenBean.PayTypeBean> payType = new ArrayList<>();
    ArrayList<ReconciliationScreenBean.PayWayBean> payWay = new ArrayList<>();
    ArrayList<ReconciliationScreenBean.PayStatusBean> payStatus = new ArrayList<>();
    @BindView(R.id.search_order_id)
    KeyEditText searchOrderId; //需要搜索的订单id

    private TimePickerView pvTime;
    private String startTime = "";
    private String endTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reconciliation_screen);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setTitle("筛选");
        startTime = DateUtils.getPreHourTime(3); //当前时间前三小时
        endTime = DateUtils.getStringToday();
        tvStartTime.setText(startTime);
        tvEndTime.setText(endTime);
        mvpPresenter.sreenOrder();
    }

    @Override
    protected ReconciliationScreenPresenter createPresenter() {
        return new ReconciliationScreenPresenter(this, this);
    }

    @Override
    public void initScreenData(ReconciliationScreenBean data) {
        storeScreen = new ArrayList<>();
        payTypeScreen = new ArrayList<>();
        payWayScreen = new ArrayList<>();
        payStatusScreen = new ArrayList<>();
        storeScreen.addAll(data.getStore().getList());
        payTypeScreen.addAll(data.getPayType().getList());
        payWayScreen.addAll(data.getPayWay().getList());
        payStatusScreen.addAll(data.getPayStatus().getList());
        initList();
    }

    private void initList() {
        storeAdapter = new ScreenAdapter(this, R.layout.item_screen, storeScreen, new Callback<ScreenAdapter.ItemState>() {
            @Override
            public void onSuccess(ScreenAdapter.ItemState... t) {
                ScreenAdapter.ItemState itemState = t[0];
                if (itemState.state) {
                    store.clear();
                    for (int i = 0; i < storeScreen.size(); i++) {
                        storeScreen.get(i).setSelect(false);
                    }
                    storeScreen.get(itemState.position).setSelect(itemState.state);
                    store.add(storeScreen.get(itemState.position));
                } else {
                    storeScreen.get(itemState.position).setSelect(itemState.state);
                    store.remove(storeScreen.get(itemState.position));
                }
                storeAdapter.notifyDataSetChanged();
            }
        });

        payTypeAdapter = new ScreenAdapter(this, R.layout.item_screen, payTypeScreen, new Callback<ScreenAdapter.ItemState>() {
            @Override
            public void onSuccess(ScreenAdapter.ItemState... t) {
                ScreenAdapter.ItemState itemState = t[0];
                if (itemState.state) {
                    payType.add(payTypeScreen.get(itemState.position));
                } else {
                    payType.remove(payTypeScreen.get(itemState.position));
                }
            }
        });

        payWayAdapter = new ScreenAdapter(this, R.layout.item_screen, payWayScreen, new Callback<ScreenAdapter.ItemState>() {
            @Override
            public void onSuccess(ScreenAdapter.ItemState... t) {
                ScreenAdapter.ItemState itemState = t[0];
                if (itemState.state) {
                    payWay.add(payWayScreen.get(itemState.position));
                } else {
                    payWay.remove(payWayScreen.get(itemState.position));
                }
            }
        });

        payStatusAdapter = new ScreenAdapter(this, R.layout.item_screen, payStatusScreen, new Callback<ScreenAdapter.ItemState>() {
            @Override
            public void onSuccess(ScreenAdapter.ItemState... t) {
                ScreenAdapter.ItemState itemState = t[0];
                if (itemState.state) {
                    payStatus.add(payStatusScreen.get(itemState.position));
                } else {
                    payStatus.remove(payStatusScreen.get(itemState.position));
                }
            }
        });

        storeRcy.setAdapter(storeAdapter);
        storeRcy.setLayoutManager(new GridLayoutManager(this, 3));

        payTypeRcy.setAdapter(payTypeAdapter);
        payTypeRcy.setLayoutManager(new GridLayoutManager(this, 3));

        payWayRcy.setAdapter(payWayAdapter);
        payWayRcy.setLayoutManager(new GridLayoutManager(this, 3));

        payStatusRcy.setAdapter(payStatusAdapter);
        payStatusRcy.setLayoutManager(new GridLayoutManager(this, 3));

        storeAdapter.notifyDataSetChanged();
        payTypeAdapter.notifyDataSetChanged();
        payWayAdapter.notifyDataSetChanged();
        payStatusAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.btn_reset, R.id.btn_comfirm, R.id.rl_start_time, R.id.rl_end_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_reset:
                searchOrderId.setText("");
                startTime = DateUtils.getPreHourTime(3); //当前时间前三小时
                endTime = DateUtils.getStringToday();
                tvStartTime.setText(startTime);
                tvEndTime.setText(endTime);
                for (int i = 0; i < storeScreen.size(); i++) {
                    storeScreen.get(i).setSelect(false);
                }
                storeId = 0;
                payTypeResultString = "";
                payWayResultString = "";
                payStatusResultString = "";
                store.clear();
                payType.clear();
                payWay.clear();
                payStatus.clear();
                initList();
                break;
            case R.id.btn_comfirm:
                Bundle bundle = new Bundle();
                setScreenData();
                bundle.putString("start_time", DateUtils.dateToStamp(tvStartTime.getText().toString()));
                bundle.putString("end_time", DateUtils.dateToStamp(tvEndTime.getText().toString()));
                bundle.putInt("store_id", storeId);
                bundle.putString("pay_type", payTypeResultString);
                bundle.putString("pay_way", payWayResultString);
                bundle.putString("pay_status", payStatusResultString);
                bundle.putString("out_transaction_id", searchOrderId.getText());
                jumpActivity(ReconciliationsResultActivity.class, bundle);
                break;
            case R.id.rl_start_time:
                showTimeDialog("开始时间", 0);
                break;
            case R.id.rl_end_time:
                showTimeDialog("结束时间", 1);
                break;
        }
    }

    int storeId;
    String payTypeResultString = "";
    String payWayResultString = "";
    String payStatusResultString = "";

    private void setScreenData() {
        payTypeResultString = "";
        payWayResultString = "";
        payStatusResultString = "";
        if (store.size() != 0) {
            storeId = store.get(0).getId();
        }

        if (payType.size() != 0) {
            StringBuilder paytypeResult = new StringBuilder();
            boolean flag = false;
            for (int i = 0; i < payType.size(); i++) {
                if (flag) {
                    paytypeResult.append(",");
                } else {
                    flag = true;
                }
                paytypeResult.append(payType.get(i).getId() + "");
            }
            payTypeResultString = paytypeResult.toString();
        }

        if (payWay.size() != 0) {
            StringBuilder paywayResult = new StringBuilder();
            boolean flag = false;
            for (int i = 0; i < payWay.size(); i++) {
                if (flag) {
                    paywayResult.append(",");
                } else {
                    flag = true;
                }
                paywayResult.append(payWay.get(i).getId() + "");
            }
            payWayResultString = paywayResult.toString();
        }

        if (payStatus.size() != 0) {
            StringBuilder payStatusResult = new StringBuilder();
            boolean flag = false;
            for (int i = 0; i < payStatus.size(); i++) {
                if (flag) {
                    payStatusResult.append(",");
                } else {
                    flag = true;
                }
                payStatusResult.append(payStatus.get(i).getId() + "");
            }
            payStatusResultString = payStatusResult.toString();
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    private void showTimeDialog(final String title, final int type) {//Dialog 模式下，在底部弹出
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (type == 1) {
                    if (DateUtils.dateCompare(getTime(date), startTime)) {
                        endTime = getTime(date);
                        tvEndTime.setText(endTime);
                    } else {
                        ToastUitl.show("请选择大于开始时间");
                    }
                } else {
                    if (DateUtils.dateCompare(endTime, getTime(date))) {
                        startTime = getTime(date);
                        tvStartTime.setText(startTime);
                    } else {
                        ToastUitl.show("请选择小于结束时间");
                    }
                }
            }
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
            @Override
            public void onTimeSelectChanged(Date date) {
            }
        }).setLayoutRes(R.layout.dialog_time, new CustomListener() {
            @Override
            public void customLayout(View v) {
                TextView titleTv = v.findViewById(R.id.tvTitle);
                titleTv.setText(title);
                v.findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pvTime.returnData();
                        pvTime.dismiss();
                    }
                });
                v.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pvTime.dismiss();
                    }
                });
            }
        }).setType(new boolean[]{true, true, true, true, true, false})
                .setDividerColor(this.getResources().getColor(R.color.light_pink))
                .setTextColorCenter(this.getResources().getColor(R.color.colorAccent))
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
        pvTime.show(rlStartTime);
    }
}
