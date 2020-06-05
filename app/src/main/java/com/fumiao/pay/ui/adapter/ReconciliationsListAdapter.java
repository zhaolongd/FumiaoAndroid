package com.fumiao.pay.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fumiao.core.uitls.DateUtils;
import com.fumiao.pay.R;
import com.fumiao.pay.bean.home.ReconciliationsBean;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ReconciliationsListAdapter extends BaseExpandableListAdapter {

    // 定义一个Context
    private Context context;
    // 定义一个LayoutInflater
    private LayoutInflater mInflater;
    // 定义一个List来保存列表数据
    private ArrayList<ReconciliationsBean.PaymentListBean> dataList = new ArrayList<>();

    public ReconciliationsListAdapter(Context context, ArrayList<ReconciliationsBean.PaymentListBean> datas) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.dataList = datas;
    }

    // 刷新数据
    public void flashData(ArrayList<ReconciliationsBean.PaymentListBean> datas) {
        this.dataList = datas;
        this.notifyDataSetChanged();
    }

    // 获取一级列表的数据
    @Override
    public int getGroupCount() {
        return dataList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if (dataList.get(i).getList().size() == 0) {
            return 1;
        }
//        //如果不为0，按正常的流程跑
        return dataList.get(i).getList().size();
    }

    // 获取一级列表的数据
    @Override
    public Object getGroup(int i) {
        return dataList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return dataList.get(i).getList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        HodlerViewGroup hodlerViewGroup;
        if (view == null) {
            hodlerViewGroup = new HodlerViewGroup();
            view = mInflater.inflate(R.layout.item_reconciliations_group, viewGroup, false);
            hodlerViewGroup.tvDay = (TextView) view.findViewById(R.id.tv_day);
            hodlerViewGroup.tvTotalAmount = (TextView) view.findViewById(R.id.tv_total_amount);
            hodlerViewGroup.tvTotalOrders = (TextView) view.findViewById(R.id.tv_total_orders);

            // 新建一个TextView对象，用来显示一级标签上的大体描述的信息
            hodlerViewGroup.groupState = (ImageView) view.findViewById(R.id.group_state);
            view.setTag(hodlerViewGroup);
        } else {
            hodlerViewGroup = (HodlerViewGroup) view.getTag();
        }
        // 一级列表右侧判断箭头显示方向
        if (b) {
            hodlerViewGroup.groupState.setImageResource(R.mipmap.ic_arrow_up);
            hodlerViewGroup.tvDay.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            hodlerViewGroup.tvTotalAmount.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            hodlerViewGroup.tvTotalOrders.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            hodlerViewGroup.groupState.setImageResource(R.mipmap.ic_arrow_down);
            hodlerViewGroup.tvDay.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            hodlerViewGroup.tvTotalAmount.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            hodlerViewGroup.tvTotalOrders.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
        /**
         * 设置相应控件的内容
         */
        // 设置标题上的文本信息
        String day = dataList.get(i).getDay();
        hodlerViewGroup.tvDay.setText(day.substring(0, 4) + "年" + day.substring(4, 6) + "月" + day.substring(6) + "日");
        hodlerViewGroup.tvTotalAmount.setText(doubleToString(dataList.get(i).getTotal_amount())+ "元");
        hodlerViewGroup.tvTotalOrders.setText(dataList.get(i).getTotal_orders() +"笔");
        // 返回一个布局对象
        return view;
    }

    // 定义一个 一级列表的view类
    private class HodlerViewGroup {
        TextView tvDay;
        TextView tvTotalAmount;
        TextView tvTotalOrders;
        ImageView groupState;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        HolderView childrenView;
        if (view == null) {
            childrenView = new HolderView();
            // 获取子视图的布局文件
            view = mInflater.inflate(R.layout.item_reconciliations_child, viewGroup, false);
            childrenView.ivType = (ImageView) view.findViewById(R.id.iv_type);
            childrenView.tvState = (TextView) view.findViewById(R.id.tv_state);
            childrenView.tvDate = (TextView) view.findViewById(R.id.tv_date);
            childrenView.tvMoney = (TextView) view.findViewById(R.id.tv_money);
            childrenView.tvStoreName = (TextView) view.findViewById(R.id.tv_store_name);
            childrenView.tvFoot = (TextView) view.findViewById(R.id.tv_foot);
            childrenView.tvEmpty = (TextView) view.findViewById(R.id.tv_empty);
            childrenView.itemOrder = (RelativeLayout) view.findViewById(R.id.item_order);

            // 这个函数是用来将holderview设置标签,相当于缓存在view当中
            view.setTag(childrenView);
        } else {
            childrenView = (HolderView) view.getTag();
        }

        //二级列表为空 显示空数据view
        if(dataList.get(i).getList().size() == 0){
            childrenView.tvEmpty.setVisibility(View.VISIBLE);
            childrenView.itemOrder.setVisibility(View.GONE);
            childrenView.tvFoot.setVisibility(View.GONE);
            return view;
        }else {
            childrenView.tvEmpty.setVisibility(View.GONE);
            childrenView.itemOrder.setVisibility(View.VISIBLE);
        }

        if(getChildrenCount(i) -1 == i1){
            childrenView.tvFoot.setVisibility(View.VISIBLE);
        }else {
            childrenView.tvFoot.setVisibility(View.GONE);
        }

        /**
         * 设置相应控件的内容
         */
        ReconciliationsBean.DataBean bean = dataList.get(i).getList().get(i1);
        childrenView.tvStoreName.setText(bean.getStore_name());
        childrenView.tvDate.setText(DateUtils.strToDate1(bean.getPay_applydate()));
        childrenView.tvMoney.setText(bean.getPay_amount()+"元");
        setImageResource(childrenView.ivType, bean.getPaytype_icon());
        if(bean.getPay_status() == 0){
            childrenView.tvState.setText("等待支付");
            childrenView.tvState.setTextColor(context.getResources().getColor(R.color.font_content));
            childrenView.tvMoney.setTextColor(context.getResources().getColor(R.color.font_content));
        }else if(bean.getPay_status() == 1){
            childrenView.tvState.setText("收款成功");
            childrenView.tvState.setTextColor(context.getResources().getColor(R.color.font_content));
            childrenView.tvMoney.setTextColor(context.getResources().getColor(R.color.font_content));
        }else if(bean.getPay_status() == 2){
            childrenView.tvState.setText("收款失败");
            childrenView.tvState.setTextColor(context.getResources().getColor(R.color.font_content));
            childrenView.tvMoney.setTextColor(context.getResources().getColor(R.color.font_content));
        }else if(bean.getPay_status() == 3){
            childrenView.tvState.setText("退款成功");
            childrenView.tvState.setTextColor(context.getResources().getColor(R.color.fail_color));
            childrenView.tvMoney.setTextColor(context.getResources().getColor(R.color.fail_color));
        }else if(bean.getPay_status() == 31){
            childrenView.tvState.setText("退款中");
            childrenView.tvState.setTextColor(context.getResources().getColor(R.color.fail_color));
            childrenView.tvMoney.setTextColor(context.getResources().getColor(R.color.fail_color));
        }else if(bean.getPay_status() == 32){
            childrenView.tvState.setText("退款失败");
            childrenView.tvState.setTextColor(context.getResources().getColor(R.color.fail_color));
            childrenView.tvMoney.setTextColor(context.getResources().getColor(R.color.fail_color));
        }
        return view;
    }

    public void setImageResource(ImageView img, String url) {
        RequestOptions requestOptions = RequestOptions.errorOf(com.fumiao.core.R.drawable.ic_img);
        requestOptions.placeholder(com.fumiao.core.R.drawable.ic_img);
        Glide.with(context).load(url).apply(requestOptions).into(img);
    }

    // 保存二级列表的视图类
    private class HolderView {
        ImageView ivType;
        TextView tvState;
        TextView tvDate;
        TextView tvMoney;
        TextView tvStoreName;
        TextView tvFoot;
        TextView tvEmpty;
        RelativeLayout itemOrder;
    }

    public static String doubleToString(double num){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
