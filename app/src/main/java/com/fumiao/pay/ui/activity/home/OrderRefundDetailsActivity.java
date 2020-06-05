package com.fumiao.pay.ui.activity.home;

import android.os.Bundle;
import android.widget.TextView;
import com.fumiao.core.uitls.DateUtils;
import com.fumiao.core.widget.ItemArrowLayout;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.home.RefundDetailBean;
import com.fumiao.pay.mvp.home.OrderRefundDetailsPresenter;
import com.fumiao.pay.mvp.home.OrderRefundDetailsView;
import java.text.DecimalFormat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderRefundDetailsActivity extends MvpActivity<OrderRefundDetailsPresenter> implements OrderRefundDetailsView {

    @BindView(R.id.refund_money)
    TextView refundMoney;
    @BindView(R.id.pay_status)
    ItemArrowLayout payStatus;
    @BindView(R.id.refund_time)
    ItemArrowLayout refundTime;
    @BindView(R.id.refund_type)
    ItemArrowLayout refundType;
    @BindView(R.id.refund_store)
    ItemArrowLayout refundStore;
    @BindView(R.id.refund_name)
    ItemArrowLayout refundName;
    @BindView(R.id.order_no)
    ItemArrowLayout orderNo;
    @BindView(R.id.refund_order_no)
    ItemArrowLayout refundOrderNo;
    @BindView(R.id.order_detail)
    ItemArrowLayout orderDetail;
    private String refundOrderId;
    private RefundDetailBean refundDetailBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_order_details);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected OrderRefundDetailsPresenter createPresenter() {
        return new OrderRefundDetailsPresenter(this, this);
    }

    private void init() {
        setTitle(getString(R.string.refund_detail));
        refundOrderId =  getIntent().getStringExtra("id");
        mvpPresenter.getRefundOrderDetails(refundOrderId);
    }

    @Override
    public void showOrderRefundDetails(RefundDetailBean data) {
        if(data != null){
            refundDetailBean = data;
            refundMoney.setText("-" + doubleToString(data.getInfo().getMoney()));
            refundTime.setDes(DateUtils.stampToDate1(data.getInfo().getRefund_time() * 1000l));
            refundType.setDes(data.getInfo().getPay_type_name());
            refundStore.setDes(data.getInfo().getStore_name());
            if(data.getInfo().getCashier_id() == data.getInfo().getMember_id()){
                refundName.setDes(data.getInfo().getMember_name());
            }else {
                refundName.setDes(data.getInfo().getCashier_name());
            }
            refundOrderNo.setDes(data.getInfo().getRefund_orderid());
            orderNo.setDes(data.getInfo().getPay_orderid());
            payStatus.setDes(data.getInfo().getStatus_name());
        }

    }

    public static String doubleToString(double num){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }

    @OnClick(R.id.order_detail)
    public void onViewClicked() {
        if(refundDetailBean != null){
            Bundle bundle = new Bundle();
            bundle.putString("id", refundDetailBean.getInfo().getPay_orderid());
            jumpActivity(OrderDetailsActivity.class, bundle);
        }
    }
}
