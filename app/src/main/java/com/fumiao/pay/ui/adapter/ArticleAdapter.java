package com.fumiao.pay.ui.adapter;

import android.content.Context;

import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import com.fumiao.pay.R;
import com.fumiao.pay.bean.home.ArticleBean;
import com.fumiao.pay.bean.home.HomeMenuBean;

import java.util.List;

public class ArticleAdapter extends CoreRecycleAdapter {
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public ArticleAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        ArticleBean articleBean = (ArticleBean) bean;
        holder.setText(R.id.article_title,articleBean.getTitle());
        holder.setImageResource(R.id.iv_article, articleBean.getImage());
    }
}
