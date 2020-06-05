package com.fumiao.pay.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fumiao.core.uitls.SPUtil;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.home.ReconciliationsBean;
import com.fumiao.pay.mvp.home.ReconciliationsPresenter;
import com.fumiao.pay.mvp.home.ReconciliationsView;
import com.fumiao.pay.ui.activity.store.SelectStoreActivity;
import com.fumiao.pay.ui.adapter.ReconciliationsListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReconciliationsActivity extends MvpActivity<ReconciliationsPresenter> implements ReconciliationsView {

    @BindView(R.id.reconciliations_list)
    ExpandableListView reconciliationsList;
    @BindView(R.id.reconciliations_refresh_Layout)
    SmartRefreshLayout reconciliationsRefreshLayout;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_filter)
    TextView tvFilter;

    ReconciliationsListAdapter adapter;
    private int store_id;
    private ArrayList<ReconciliationsBean.PaymentListBean> dataList;
    private int page = 0;
    private String query_day = "";
    private int SELECTED_STORE_REQUEST = 556;
    private String store_name;
    private int is_boss; //1:老板账号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reconciliations, false);
        ButterKnife.bind(this);
        init();
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
        tvTitle.setText(store_name);
        dataList = new ArrayList<>();
        adapter = new ReconciliationsListAdapter(this, dataList);
        reconciliationsList.setAdapter(adapter);
        //去掉默认带的箭头
        reconciliationsList.setGroupIndicator(null);
        reconciliationsList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                if (dataList.size() != 0 && dataList.get(i).getList().size() != 0) {
                    int payStatus = dataList.get(i).getList().get(i1).getPay_status();
                    Bundle bundle = new Bundle();
                    if (payStatus == 3 || payStatus == 31 || payStatus == 32) {
                        bundle.putString("id", dataList.get(i).getList().get(i1).getPay_orderid());
                        jumpActivity(OrderRefundDetailsActivity.class, bundle);
                    } else {
                        bundle.putString("id", dataList.get(i).getList().get(i1).getPay_orderid());
                        jumpActivity(OrderDetailsActivity.class, bundle);
                    }
                }
                return false;
            }
        });

        reconciliationsList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (sign == -1) {
                    // 展开被选的group
                    parent.expandGroup(groupPosition);
                    // 设置被选中的group置于顶端
                    parent.setSelectedGroup(groupPosition);
                    sign = groupPosition;
                } else if (sign == groupPosition) {
                    parent.collapseGroup(sign);
                    sign = -1;
                } else {
                    parent.collapseGroup(sign);
                    // 展开被选的group
                    parent.expandGroup(groupPosition);
                    // 设置被选中的group置于顶端
                    parent.setSelectedGroup(groupPosition);
                    sign = groupPosition;
                }
                return true;
            }
        });

        reconciliationsRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                query_day = "";
                reconciliationsRefreshLayout.setEnableLoadMore(true);
                mvpPresenter.getReconciliationsList(store_id, page, query_day);
                reconciliationsRefreshLayout.finishRefresh(true);  //刷新完成
            }
        });

        reconciliationsRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mvpPresenter.getReconciliationsList(store_id, page, query_day);
                reconciliationsRefreshLayout.finishLoadMore(true);//加载完成
            }
        });
        reconciliationsRefreshLayout.setEnableLoadMore(true);
        mvpPresenter.getReconciliationsList(store_id, page, query_day);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECTED_STORE_REQUEST) {
                store_id = data.getIntExtra("store_id", 0);
                store_name = data.getStringExtra("store_name");
                tvTitle.setText(store_name);
                //需要重新刷新数据
                page = 0;
                query_day = "";
                reconciliationsRefreshLayout.setEnableLoadMore(true);
                mvpPresenter.getReconciliationsList(store_id, page, query_day);
            }
        }
    }

    private int sign = -1;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected ReconciliationsPresenter createPresenter() {
        return new ReconciliationsPresenter(this, this);
    }

    @Override
    public void showReconciliationsData(ReconciliationsBean data) {
        if (data != null) {
            if (page == 0) {
                dataList.clear();
            }
            if (data.getIsEnd() == 1) {
                reconciliationsRefreshLayout.setEnableLoadMore(false);
            }
            query_day = data.getNextQueryDay();
            if (data.getHistoryPaymentList() != null) {
                dataList.addAll(data.getHistoryPaymentList());
            }
            if (dataList.size() == 0) {
                showEmpty(ContextCompat.getDrawable(this, R.mipmap.reconciliations_list_null), "暂无账本消息哦");
            } else {
                hideEmpty();
            }
            adapter.flashData(dataList);
        }
    }

    @OnClick({R.id.iv_back, R.id.rl_title, R.id.tv_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.tv_filter:
                jumpActivity(ReconciliationScreenActivity.class);
                break;
        }
    }
}
