package com.fumiao.pay.ui.adapter;

import android.content.Context;

import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import com.fumiao.pay.R;
import com.fumiao.pay.bean.home.DayCalendarBean;

import java.text.DecimalFormat;
import java.util.List;

public class DayCalendarAdapter extends CoreRecycleAdapter {

    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public DayCalendarAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean) {
        super.convert(holder, bean);
        DayCalendarBean.DaysBean dateBean = (DayCalendarBean.DaysBean) bean;
        if (dateBean.getDate() != null){
            holder.setText(R.id.amount_sum,getString(R.string.money_icon)+ floatToString(dateBean.getAmount()));
            holder.setText(R.id.data, dateBean.getDay());
        }
    }

    public String floatToString(float num){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }
}
