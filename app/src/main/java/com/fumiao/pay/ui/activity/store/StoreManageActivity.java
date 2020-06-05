package com.fumiao.pay.ui.activity.store;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.uitls.Utils;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.store.StoreListBean;
import com.fumiao.pay.mvp.store.StoreManagePresenter;
import com.fumiao.pay.mvp.store.StoreManageVeiw;
import com.fumiao.pay.widget.SpacesItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreManageActivity extends MvpActivity<StoreManagePresenter> implements StoreManageVeiw {

    @BindView(R.id.store_recy)
    RecyclerView storeRecy;

    StoreListAdapter storeListAdapter;
    List<StoreListBean.StoresBean> storesBeans;
    int store_id = 0;
    @BindView(R.id.store_refresh_Layout)
    SmartRefreshLayout storeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manage, false);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        storeRefreshLayout.autoRefresh();//自动刷新
    }

    private void init() {
        setTitle("全部门店");
        setRight("添加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpActivity(StoreAddActivity.class);
            }
        });
        storesBeans = new ArrayList<>();
        storeListAdapter = new StoreListAdapter(this, R.layout.item_store_list, storesBeans);
        storeRecy.setAdapter(storeListAdapter);
        storeRecy.setLayoutManager(new LinearLayoutManager(this));
        storeRecy.addItemDecoration(new SpacesItemDecoration(Utils.dip2px(this, 15), Utils.dip2px(this, 15),0,Utils.dip2px(this, 10)));
        storeListAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("store_id", storesBeans.get(position).getId());
//                bundle.putSerializable("store_bean", storesBeans.get(position));
                jumpActivity(StoreSettingActivity.class, bundle);
                storeListAdapter.notifyDataSetChanged();
            }
        });
        storeRefreshLayout.setEnableLoadMore(false);
        storeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getStoreList();
                storeRefreshLayout.finishRefresh();  //刷新完成
            }
        });
    }

    @Override
    protected StoreManagePresenter createPresenter() {
        return new StoreManagePresenter(this, this);
    }

    @Override
    public void showStoreList(StoreListBean data) {
        if(data != null){
            storesBeans.clear();
            if(data.getStores() != null){
                storesBeans.addAll(data.getStores());
            }
            if (data.getStores().size() == 0) {
                showEmpty(ContextCompat.getDrawable(this, R.mipmap.store_list_null), "暂无门店哦");
            }else {
                hideEmpty();
            }
            storeListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setDefaultStoreSuccess(int storeId) {
        ToastUitl.show("门店切换成功");
        storeRefreshLayout.autoRefresh();
        SPUtil.getInstance().putInt(STORE_ID, storeId);
    }

    class StoreListAdapter extends CoreRecycleAdapter {
        public StoreListAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
            super(context, layoutId, data);
        }

        @Override
        protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
            super.convert(holder, bean, position);
            final StoreListBean.StoresBean storesBean = (StoreListBean.StoresBean) bean;
            holder.setText(R.id.store_name, storesBean.getName());
            holder.setImageResource(R.id.store_img, storesBean.getImage(), R.mipmap.ic_head_image);
            if (storesBean.getIs_default() == 1) {
                holder.setBg(R.id.store_list_layout, R.mipmap.current_store_bg);
                holder.setVisibility(R.id.current_store_bottom_layout, View.VISIBLE);
                holder.setVisibility(R.id.switch_store_bottom_layout, View.GONE);
            } else {
                holder.setBg(R.id.store_list_layout, R.mipmap.switch_store_bg);
                holder.setVisibility(R.id.current_store_bottom_layout, View.GONE);
                holder.setVisibility(R.id.switch_store_bottom_layout, View.VISIBLE);
            }
            holder.setText(R.id.store_addr, storesBean.getAddress() + "");

            //底部切换门店点击
            holder.setOnClickListener(R.id.switch_store_bottom_layout, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mvpPresenter.setDefaultStore(storesBean.getId());
                }
            });

            //编辑门店点击
            holder.setOnClickListener(R.id.edit_store_layout, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("store_id", storesBean.getId());
                    bundle.putBoolean("edit_store", true);
                    jumpActivity(StoreSettingActivity.class, bundle);
                }
            });
        }

    }
}
