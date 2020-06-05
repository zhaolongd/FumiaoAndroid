package com.fumiao.pay.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import com.fumiao.core.uitls.Callback;
import com.fumiao.pay.R;
import com.fumiao.pay.bean.home.ReconciliationScreenBean;

import java.util.List;

/**
 * 对账筛选项
 */
public class ScreenAdapter extends CoreRecycleAdapter {

    Callback<ItemState> callback;

    /**
     * @param context  //上下文
     * @param layoutId //布局id
     * @param data     //数据源
     */
    public ScreenAdapter(Context context, int layoutId, List<? extends CoreBean> data, Callback<ItemState> callback) {
        super(context, layoutId, data);
        this.callback = callback;
    }

    @Override
    protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, final int position) {
        super.convert(holder, bean, position);
        final TextView radioButton = holder.getConvertView().findViewById(R.id.item_screen_radio);
        if (bean instanceof ReconciliationScreenBean.PayTypeBean) {
            ReconciliationScreenBean.PayTypeBean screenBean = (ReconciliationScreenBean.PayTypeBean) bean;
            holder.setText(R.id.item_screen_radio, screenBean.getName());
        } else if (bean instanceof ReconciliationScreenBean.PayWayBean) {
            ReconciliationScreenBean.PayWayBean screenBean = (ReconciliationScreenBean.PayWayBean) bean;
            holder.setText(R.id.item_screen_radio, screenBean.getName());
        }else if(bean instanceof ReconciliationScreenBean.PayStatusBean){
            ReconciliationScreenBean.PayStatusBean screenBean = (ReconciliationScreenBean.PayStatusBean) bean;
            holder.setText(R.id.item_screen_radio, screenBean.getName());
        }else if(bean instanceof ReconciliationScreenBean.StoreBean){
            ReconciliationScreenBean.StoreBean screenBean = (ReconciliationScreenBean.StoreBean) bean;
            holder.setText(R.id.item_screen_radio, screenBean.getName());
            if(screenBean.isSelect()){
                radioButton.setBackgroundResource(R.drawable.round_orange_bg);
                radioButton.setTextColor(context.getResources().getColor(R.color.white));
            }else {
                radioButton.setBackgroundResource(R.drawable.round_gray_bg);
                radioButton.setTextColor(context.getResources().getColor(R.color.font_content));
            }
        }
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bean instanceof ReconciliationScreenBean.StoreBean){
                    ReconciliationScreenBean.StoreBean screenBean = (ReconciliationScreenBean.StoreBean) bean;
                    ItemState itemState = new ItemState();
                    itemState.position = position;
                    itemState.state = !screenBean.isSelect();
                    callback.onSuccess(itemState);
                }else {
                    radioButton.setSelected(!radioButton.isSelected());
                    ItemState itemState = new ItemState();
                    itemState.position = position;
                    itemState.state=radioButton.isSelected();
                    if(radioButton.isSelected()){
                        radioButton.setBackgroundResource(R.drawable.round_orange_bg);
                        radioButton.setTextColor(context.getResources().getColor(R.color.white));
                    }else {
                        radioButton.setBackgroundResource(R.drawable.round_gray_bg);
                        radioButton.setTextColor(context.getResources().getColor(R.color.font_content));
                    }
                    callback.onSuccess(itemState);
                }
            }
        });
    }

    public class ItemState {
       public int position;
        public boolean state;
    }
}
