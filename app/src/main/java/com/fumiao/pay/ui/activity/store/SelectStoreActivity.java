package com.fumiao.pay.ui.activity.store;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.store.StoreListBean;
import com.fumiao.pay.mvp.store.SelectStorePresenter;
import com.fumiao.pay.mvp.store.SelectStoreView;
import com.fumiao.pay.widget.RecyclerViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhaolong.
 * Description: 选择商户下的门店
 * Date: 2020/1/16 0016 10:59
 */
public class SelectStoreActivity extends MvpActivity<SelectStorePresenter> implements SelectStoreView {

    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.srl_store)
    SmartRefreshLayout srlStore;

    private AllStoreListAdapter mAllStoreListAdapter;
    private List<StoreListBean.StoresBean> stores;
    int store_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_store);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setTitle("选择门店");
        store_id = getIntent().getIntExtra("store_id", 0);
        stores = new ArrayList<>();
        mAllStoreListAdapter = new AllStoreListAdapter(this, R.layout.item_store, stores);
        rvStore.setAdapter(mAllStoreListAdapter);
        rvStore.setLayoutManager(new LinearLayoutManager(this));
        rvStore.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL,2, ContextCompat.getColor(this,R.color.line)));
        mAllStoreListAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                StoreListBean.StoresBean store = stores.get(position);
                Intent intent = new Intent();
                intent.putExtra("store_id", store.getId());
                intent.putExtra("store_name", store.getName());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        srlStore.setEnableLoadMore(false);
        srlStore.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getStoreList();
                srlStore.finishRefresh();  //刷新完成
            }
        });
        mvpPresenter.getStoreList();
    }

    @Override
    protected SelectStorePresenter createPresenter() {
        return new SelectStorePresenter(this, this);
    }

    @Override
    public void showStoreList(StoreListBean data) {
        stores.clear();
        if(data.getStores() != null){
            stores.addAll(data.getStores());
        }
        if (data.getStores().size() == 0) {
            showEmpty(ContextCompat.getDrawable(this, R.mipmap.store_list_null), "暂无门店哦");
        }else {
            hideEmpty();
        }
        mAllStoreListAdapter.notifyDataSetChanged();
    }

    public class AllStoreListAdapter extends CoreRecycleAdapter {

        public AllStoreListAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
            super(context, layoutId, data);
        }

        @Override
        protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
            super.convert(holder, bean, position);
            StoreListBean.StoresBean storesBean = (StoreListBean.StoresBean) bean;
            holder.setText(R.id.tv_store_name, storesBean.getName());
            if(storesBean.getId() == store_id){
                holder.setVisibility(R.id.iv_selected, View.VISIBLE);
                holder.setTextColor(R.id.tv_store_name, R.color.colorAccent);
            }else {
                holder.setVisibility(R.id.iv_selected, View.INVISIBLE);
                holder.setTextColor(R.id.tv_store_name, R.color.font_content);
            }
        }
    }
}
