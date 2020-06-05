package com.fumiao.pay.ui.adapter;

import android.content.Context;
import android.view.View;

import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import com.fumiao.pay.R;
import com.fumiao.pay.bean.store.StoreListBean;

import java.util.List;

public class StoreListAdapter extends CoreRecycleAdapter {
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public StoreListAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        StoreListBean.StoresBean storesBean = (StoreListBean.StoresBean) bean;
        holder.setText(R.id.store_name, storesBean.getName());
        holder.setImageResource(R.id.store_img, storesBean.getImage(), R.mipmap.ic_head_image);
        if(storesBean.isClickable()){
            holder.setBg(R.id.store_list_layout, R.mipmap.current_store_bg);
            holder.setVisibility(R.id.current_store_bottom_layout, View.VISIBLE);
            holder.setVisibility(R.id.switch_store_bottom_layout, View.GONE);
        }else {
            holder.setBg(R.id.store_list_layout, R.mipmap.switch_store_bg);
            holder.setVisibility(R.id.current_store_bottom_layout, View.GONE);
            holder.setVisibility(R.id.switch_store_bottom_layout, View.VISIBLE);
        }
        holder.setText(R.id.store_addr, storesBean.getAddress()+"");

    }
}
