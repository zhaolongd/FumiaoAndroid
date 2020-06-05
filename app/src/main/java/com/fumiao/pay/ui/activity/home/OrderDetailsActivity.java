package com.fumiao.pay.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.uitls.AESUtils;
import com.fumiao.core.uitls.DateUtils;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.uitls.Utils;
import com.fumiao.core.widget.ItemArrowLayout;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.home.OrderDetailsBean;
import com.fumiao.pay.bean.home.RefundOrderBean;
import com.fumiao.pay.event.OrdersRefreshEvent;
import com.fumiao.pay.mvp.home.OrderDetailsPresenter;
import com.fumiao.pay.mvp.home.OrderDetailsView;
import com.fumiao.pay.ui.adapter.RefundOrderAdapter;
import com.fumiao.pay.ui.dialog.MsgDialog;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailsActivity extends MvpActivity<OrderDetailsPresenter> implements OrderDetailsView {

    @BindView(R.id.tv_payment_title)
    TextView tvPaymentTitle;
    @BindView(R.id.tv_real_money)
    TextView tvRealMoney;
    @BindView(R.id.order_status)
    ItemArrowLayout orderStatus;
    @BindView(R.id.order_money)
    ItemArrowLayout orderMoney;
    @BindView(R.id.order_time)
    ItemArrowLayout orderTime;
    @BindView(R.id.order_payment_method)
    ItemArrowLayout orderPaymentMethod;
    @BindView(R.id.order_payment_user)
    ItemArrowLayout orderPaymentUser;
    @BindView(R.id.order_store_name)
    ItemArrowLayout orderStoreName;
    @BindView(R.id.order_no)
    ItemArrowLayout orderNo;
    @BindView(R.id.other_no)
    ItemArrowLayout otherNo;
    @BindView(R.id.out_transaction_no)
    ItemArrowLayout outTransactionNo;
    @BindView(R.id.refund_layout)
    LinearLayout refundLayout;
    MsgDialog msgDialog;
    @BindView(R.id.tv_refund)
    TextView tvRefund;
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.balance_arrow)
    ImageView balanceArrow;
    @BindView(R.id.balance_rate)
    TextView balanceRate;
    @BindView(R.id.balance_poundage)
    TextView balancePoundage;
    @BindView(R.id.poundage_layout)
    LinearLayout poundageLayout;
    @BindView(R.id.balance_layout)
    LinearLayout balanceLayout;
    @BindView(R.id.refund_recy)
    RecyclerView refundRecy;
    private OrderDetailsBean orderDetailsBean;
    private final static int REFUND = 1;
    String order_id;
    boolean isMember = false;
    //费率是否显示和隐藏标识
    private boolean isShowRate = false;

    private RefundOrderAdapter mRefundOrderAdapter;
    private ArrayList<RefundOrderBean> refundData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setTitle("交易详情");
        msgDialog = new MsgDialog(this);
        order_id = getIntent().getStringExtra("id");
        isMember = SPUtil.getInstance().getBoolean(IS_MENBER);
        refundData = new ArrayList<>();
        mRefundOrderAdapter = new RefundOrderAdapter(this, R.layout.item_refund_order, refundData);
        refundRecy.setLayoutManager(new LinearLayoutManager(this));
        refundRecy.setAdapter(mRefundOrderAdapter);
        mRefundOrderAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id", refundData.get(position).getPay_orderid());
                jumpActivity(OrderRefundDetailsActivity.class, bundle);
            }
        });
        mvpPresenter.getOrderDetails(order_id);
    }

    @Override
    protected OrderDetailsPresenter createPresenter() {
        return new OrderDetailsPresenter(this, this);
    }

    @Override
    public void showOrderDetails(OrderDetailsBean data) {
        orderDetailsBean = data;
        orderStoreName.setDes(data.getPayment().getStore_name());
        orderNo.setDes(data.getPayment().getPay_orderid());
        orderMoney.setDes(getString(R.string.money_icon) + data.getPayment().getPay_amount());
        if (data.getPayment().getIs_refund() == 0) {
            tvRealMoney.setText(getString(R.string.money_icon) + data.getPayment().getPay_amount());
            tvRefund.setVisibility(View.GONE);
        } else {
            String money = getString(R.string.money_icon) + data.getPayment().getPay_amount();
            String realMoneyContent = "<s>" + money + "</s>";
            tvRealMoney.setText(Html.fromHtml(realMoneyContent));
            tvRefund.setVisibility(View.VISIBLE);
        }
        //结算金额
        balance.setText(context.getString(R.string.money_icon) + data.getPayment().getPay_actualamount());
        //手续费费率
        balanceRate.setText("手续费（" + data.getPayment().getPay_rate() + "）");
        //手续费
        balancePoundage.setText(context.getString(R.string.money_icon) + data.getPayment().getPay_poundage());
        otherNo.setDes(data.getPayment().getTransaction_id());

        orderPaymentMethod.setDes(data.getPayment().getPaytype_name());
        orderStoreName.setDes(data.getPayment().getStore_name());
        orderTime.setDes(DateUtils.stampToDate1(data.getPayment().getPay_applydate() * 1000l));
        orderPaymentUser.setDes(data.getPayment().getPayer_name());
        outTransactionNo.setDes(data.getPayment().getOut_transaction_id());
        if (data.getPayment().getPaytype_name().contains("微信")) {
            outTransactionNo.setKey("微信单号");
        } else {
            outTransactionNo.setKey("支付宝单号");
        }
        refundData.clear();
        refundData.addAll(data.getRefund_order_list());
        mRefundOrderAdapter.notifyDataSetChanged();

        /**
         * 0：待支付
         * 1：已支付
         * 2：已取销
         */
        switch (data.getPayment().getPay_status()) {
            case 0:
                orderStatus.setDes(getString(R.string.wait_pay));
                incompleteOrderStatus();
                break;
            case 1:
                orderStatus.setDes(getString(R.string.pay_success));
                break;
            case 2:
                orderStatus.setDes(getString(R.string.pay_fail));
                incompleteOrderStatus();
                break;
        }

        if (data.getPayment().getPay_status() == 0) {
            //待支付状态需显示关闭按钮
            setRight(getString(R.string.cancel_paid), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    msgDialog.show("是否取消交易？", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mvpPresenter.requestOrderCancel(AESUtils.encrypt(orderDetailsBean.getPayment().getPay_orderid(), SPUtil.getInstance().getString("KEY")));
                            msgDialog.dismiss();
                        }
                    });
                }
            });
            baseRightText.setVisibility(View.VISIBLE);
        } else if (data.getPayment().getPay_status() == 1) {
                if(Float.parseFloat(data.getPayment().getRefundable_amount()) > 0 ){
                    //已支付状态需显示退款按钮
                    setRight(getString(R.string.refund), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("order", orderDetailsBean);
                            jumpActivityForResult(OrderRefundActivity.class, bundle, REFUND);
                        }
                    });
                    baseRightText.setVisibility(View.VISIBLE);
                }
        } else {
            baseRightText.setVisibility(View.INVISIBLE);
        }
        if(refundData != null && refundData.size() > 0){
            refundLayout.setVisibility(View.VISIBLE);
        }else {
            refundLayout.setVisibility(View.GONE);
        }

        //店员登录取消和退款都不显示
        if (isMember) {
            baseRightText.setVisibility(View.INVISIBLE);
        }
    }

    //未完成状态订单显示样式需更新
    private void incompleteOrderStatus() {
        tvRealMoney.setText(context.getString(R.string.money_icon) + doubleToString(0));
        String money = context.getString(R.string.money_icon) + orderDetailsBean.getPayment().getPay_amount();
        String orderMoneyContent = "<s>" + money + "</s>";
        orderMoney.setDes(Html.fromHtml(orderMoneyContent));
        balance.setText(context.getString(R.string.money_icon) + doubleToString(0));
    }

    public static String doubleToString(double num) {
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }

    @OnClick({R.id.balance_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.balance_layout:
                isShowRate = isShowRate ? false : true;
                if (isShowRate) {
                    poundageLayout.setVisibility(View.VISIBLE);
                    balanceArrow.setImageResource(R.mipmap.ic_arrow_up);
                } else {
                    poundageLayout.setVisibility(View.GONE);
                    balanceArrow.setImageResource(R.mipmap.ic_arrow_down);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REFUND) {
            mvpPresenter.getOrderDetails(order_id);
        }
    }

    @Override
    public void cancelOrderSuccess() {
        ToastUitl.show("取消成功");
        EventBus.getDefault().post(new OrdersRefreshEvent(CANCEL_ORDER_SUCCESS));
        finish();
    }
}
