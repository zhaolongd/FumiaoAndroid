package com.fumiao.pay.ui.activity.me;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.Utils;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.home.CashierCodeBean;
import com.fumiao.pay.bean.me.CashierDeviceBean;
import com.fumiao.pay.mvp.me.CashierPresenter;
import com.fumiao.pay.mvp.me.CashierView;
import com.fumiao.pay.ui.dialog.GuideDialog;
import com.fumiao.pay.widget.SpacesItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CashierActivity extends MvpActivity<CashierPresenter> implements CashierView {

    @BindView(R.id.cashier_recy)
    RecyclerView cashierRecy;
    @BindView(R.id.refresh_Layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_device_size)
    TextView tvDeviceSize;
    private int store_id;
    private List<CashierDeviceBean.DeviceBean> deviceBeans;
    private CashierListAdapter cashierListAdapter;
    GuideDialog guideDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier, false);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        store_id = SPUtil.getInstance().getInt(STORE_ID);
        guideDialog = new GuideDialog(this);
        guideDialog.setCanceledOnTouchOutside(true);
        deviceBeans = new ArrayList<>();
        cashierListAdapter = new CashierListAdapter(this, R.layout.item_cashier_list, deviceBeans);
        cashierRecy.setLayoutManager(new LinearLayoutManager(this));
        cashierRecy.addItemDecoration(new SpacesItemDecoration(Utils.dip2px(this, 10), Utils.dip2px(this, 10), 0, Utils.dip2px(this, 10)));
        cashierRecy.setAdapter(cashierListAdapter);
        setTitle("收款工具");
        setRight("扫码添加", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mvpPresenter.getCashierCode(store_id);
            }
        });

        cashierListAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                CashierDeviceBean.DeviceBean dataBean = deviceBeans.get(position);
                int device_type = dataBean.getDevice_type(); //1二维码绑定盒子，2单独二维码，3单独盒子，4电子码
                 if(device_type == 2 || device_type == 4){
                     jumpActivity(CashierDetailActivity.class);
                }
            }
        });

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getCashierList(store_id);
                refreshLayout.finishRefresh();  //刷新完成
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvpPresenter.getCashierList(store_id);
    }

    @Override
    protected CashierPresenter createPresenter() {
        return new CashierPresenter(this, this);
    }

    @Override
    public void cashierList(CashierDeviceBean data) {
        if(data != null){
            if(data.getDevice_list() != null){
                deviceBeans.clear();
                deviceBeans.addAll(data.getDevice_list());
            }
            tvDeviceSize.setText(deviceBeans.size() + "");
            if (data.getDevice_list().size() == 0) {
                showEmpty(ContextCompat.getDrawable(this, R.mipmap.cashier_list_null), "暂无设备和收款码哦");
            }else {
                hideEmpty();
            }
            cashierListAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void cashierCode(CashierCodeBean data) {
//        if(data != null){
//            int is_activation = data.getIs_activation(); // 1：未激活 2：已激活
//        }
        jumpActivity(CashierAddActivity.class);
    }


    class CashierListAdapter extends CoreRecycleAdapter {
        public CashierListAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
            super(context, layoutId, data);
        }

        @Override
        protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
            super.convert(holder, bean, position);
            final CashierDeviceBean.DeviceBean dataBean = (CashierDeviceBean.DeviceBean) bean;
            int device_type = dataBean.getDevice_type(); //1二维码绑定盒子，2单独二维码，3单独盒子，4电子码
            if(device_type == 1 || device_type == 3){
                holder.setText(R.id.tv_store_name, dataBean.getName());
                holder.setText(R.id.tv_no, "SN:" + dataBean.getDevice_sn());
                holder.setText(R.id.tv_store, "所属门店：" + dataBean.getName());
                holder.setImageResource(R.id.iv_device_bg,  R.mipmap.box_device_bg);
                holder.setVisibility(R.id.ll_look_cashier, View.GONE);

            }else if(device_type == 2 || device_type == 4){
                holder.setText(R.id.tv_store_name, dataBean.getName());
                holder.setText(R.id.tv_no, "编号:" + dataBean.getSerial_number());
                holder.setText(R.id.tv_store, "所属门店：" + dataBean.getName());
                holder.setImageResource(R.id.iv_device_bg,  R.mipmap.cashier_bg);
                holder.setVisibility(R.id.ll_look_cashier, View.VISIBLE);
            }
        }
    }
}
