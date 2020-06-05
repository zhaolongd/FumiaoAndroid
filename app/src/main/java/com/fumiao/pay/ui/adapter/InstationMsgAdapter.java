package com.fumiao.pay.ui.adapter;

import android.content.Context;
import android.util.Log;

import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import com.fumiao.core.uitls.DateUtils;
import com.fumiao.pay.R;
import com.fumiao.pay.bean.me.HelpCenterBean;

import java.util.List;

/**
 * Created by chee on 2018/8/30.
 */
public class InstationMsgAdapter extends CoreRecycleAdapter {

    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public InstationMsgAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        HelpCenterBean.ArticlesBean.DataBean dataBean = (HelpCenterBean.ArticlesBean.DataBean) bean;
        holder.setText(R.id.tv_title,dataBean.getTitle());
        holder.setText(R.id.tv_content,dataBean.getBrief());
        holder.setText(R.id.tv_date, DateUtils.dateToStr(dataBean.getShow_create_time()+"000","yyyy-MM-dd hh:mm:ss"));
    }
}
