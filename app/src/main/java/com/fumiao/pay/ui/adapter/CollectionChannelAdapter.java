package com.fumiao.pay.ui.adapter;

import android.content.Context;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import com.fumiao.pay.R;
import com.fumiao.pay.bean.home.ChannelsBean;

import java.util.List;

public class CollectionChannelAdapter extends CoreRecycleAdapter {
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public CollectionChannelAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        ChannelsBean channelsBean = (ChannelsBean) bean;
        String paytype = channelsBean.getPaytype();
        String rate = channelsBean.getRate();
        holder.setText(R.id.channel_rate,  rate + "%");
        holder.setText(R.id.channel_paytype, paytype);
    }
}
