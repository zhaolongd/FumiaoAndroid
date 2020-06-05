package com.fumiao.pay.ui.activity.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.uitls.Utils;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.home.ReconciliationsResultBean;
import com.fumiao.pay.mvp.home.ReconciliationsResultPresenter;
import com.fumiao.pay.mvp.home.ReconciliationsResultView;
import com.fumiao.pay.ui.adapter.ReconciliationsResultAdapter;
import com.fumiao.pay.widget.SpacesItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReconciliationsResultActivity extends MvpActivity<ReconciliationsResultPresenter> implements ReconciliationsResultView {

    String start_time;
    String end_time;
    int store_id;
    String pay_type;
    String pay_way;
    String pay_status;
    String out_transaction_id;
    int pagesize = 20, page = 1;
    @BindView(R.id.tv_order_sum)
    TextView tvOrderSum;
    @BindView(R.id.tv_order_count)
    TextView tvOrderCount;
    @BindView(R.id.reconciliations_rcy)
    RecyclerView reconciliationsRcy;
    ReconciliationsResultAdapter resultAdapter;
    ArrayList<ReconciliationsResultBean.PaymentListBean.DataBean> data = new ArrayList<>();
    @BindView(R.id.refresh_Layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reconciliations_result, false);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setTitle("筛选结果");
        start_time = getIntent().getStringExtra("start_time");
        end_time = getIntent().getStringExtra("end_time");
        store_id = getIntent().getIntExtra("store_id", 0);
        pay_type = getIntent().getStringExtra("pay_type");
        pay_way = getIntent().getStringExtra("pay_way");
        pay_status = getIntent().getStringExtra("pay_status");
        out_transaction_id = getIntent().getStringExtra("out_transaction_id");
        resultAdapter = new ReconciliationsResultAdapter(this, R.layout.item_reconciliations_child, data);
        reconciliationsRcy.setAdapter(resultAdapter);
        reconciliationsRcy.setLayoutManager(new LinearLayoutManager(this));
        reconciliationsRcy.addItemDecoration(new SpacesItemDecoration(0, 0, 0, Utils.dip2px(this, 10)));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                refreshLayout.setEnableLoadMore(true);
                mvpPresenter.getList(out_transaction_id, start_time, end_time, store_id, pay_type, pay_way, pay_status, pagesize, page);
                refreshLayout.finishRefresh(true);  //刷新完成
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mvpPresenter.getList(out_transaction_id, start_time, end_time, store_id, pay_type, pay_way, pay_status, pagesize, page);
                refreshLayout.finishLoadMore(true);//加载完成
            }
        });

        resultAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                if (data.size()!= 0 && data.size()!=0) {
                    int payStatus = data.get(position).getPay_status();
                    Bundle bundle = new Bundle();
                    if(payStatus == 3){
                        bundle.putString("id", data.get(position).getPay_orderid());
                        jumpActivity(OrderRefundDetailsActivity.class, bundle);
                    }else {
                        bundle.putString("id", data.get(position).getPay_orderid());
                        jumpActivity(OrderDetailsActivity.class, bundle);
                    }
                }
            }
        });

        mvpPresenter.getList(out_transaction_id, start_time, end_time, store_id, pay_type, pay_way, pay_status, pagesize, page);
    }

    @Override
    protected ReconciliationsResultPresenter createPresenter() {
        return new ReconciliationsResultPresenter(this, this);
    }

    @Override
    public void showReconciliationsResultList(ReconciliationsResultBean dataBean) {
        if (page == 1) {
            data.clear();
        }
        if (page == 1 && dataBean.getPayment_list().getData().size() == 0){
            tvEmpty.setVisibility(View.VISIBLE);
            return;
        }
        if (dataBean.getPayment_list().getData().size() < pagesize) {
            refreshLayout.setEnableLoadMore(false);
        }
        tvOrderSum.setText("总计" + dataBean.getStatistic().getOrder_money_sum() + "元");
        tvOrderCount.setText(dataBean.getStatistic().getOrder_count() + "笔");
        data.addAll(dataBean.getPayment_list().getData());
        tvEmpty.setVisibility(View.GONE);
        resultAdapter.notifyDataSetChanged();
    }
}
