package com.fumiao.pay.ui.adapter;

import android.content.Context;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import com.fumiao.core.uitls.DateUtils;
import com.fumiao.pay.R;
import com.fumiao.pay.bean.home.RefundOrderBean;
import java.util.List;

public class RefundOrderAdapter extends CoreRecycleAdapter{
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public RefundOrderAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean) {
        super.convert(holder, bean);
        final RefundOrderBean dataBean = (RefundOrderBean) bean;
        holder.setText(R.id.refund_date, DateUtils.stampToDate1(dataBean.getPay_applydate() * 1000l));
        holder.setText(R.id.refund_price, dataBean.getPay_amount() + "元");

    }
}
