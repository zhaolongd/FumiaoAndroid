package com.fumiao.pay.ui.adapter;

import android.content.Context;

import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import com.fumiao.core.uitls.DateUtils;
import com.fumiao.pay.R;
import com.fumiao.pay.bean.home.ReconciliationsResultBean;

import java.text.DecimalFormat;
import java.util.List;

public class ReconciliationsResultAdapter  extends CoreRecycleAdapter {
    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public ReconciliationsResultAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
        super.convert(holder, bean, position);
        ReconciliationsResultBean.PaymentListBean.DataBean dataBean = (ReconciliationsResultBean.PaymentListBean.DataBean) bean;
        holder.setText(R.id.tv_money, dataBean.getPay_amount()+"元");
        holder.setText(R.id.tv_date, DateUtils.strToDate2(dataBean.getPay_applydate()));
        holder.setImageResource(R.id.iv_type, dataBean.getPaytype_icon());
        holder.setText(R.id.tv_store_name, dataBean.getStore_name());
        if(dataBean.getPay_status() == 0){
            holder.setText(R.id.tv_state,"等待支付");
            holder.setTextColor(R.id.tv_money, R.color.font_content);
        }else if(dataBean.getPay_status() == 1){
            holder.setText(R.id.tv_state,"收款成功");
            holder.setTextColor(R.id.tv_money, R.color.font_content);
        }else if(dataBean.getPay_status() == 2){
            holder.setText(R.id.tv_state,"收款失败");
            holder.setTextColor(R.id.tv_money, R.color.font_content);
        }else if(dataBean.getPay_status() == 3){
            holder.setText(R.id.tv_state,"退款成功");
            holder.setTextColor(R.id.tv_money, R.color.fail_color);
        }
    }

    public static String doubleToString(double num){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }
}
