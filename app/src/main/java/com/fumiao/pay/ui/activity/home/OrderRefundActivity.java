package com.fumiao.pay.ui.activity.home;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fumiao.core.uitls.AESUtils;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.widget.ItemArrowLayout;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.home.OrderDetailsBean;
import com.fumiao.pay.bean.home.RefundBean;
import com.fumiao.pay.event.OrdersRefreshEvent;
import com.fumiao.pay.mvp.home.OrderRefundPresenter;
import com.fumiao.pay.mvp.home.OrderRefundView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderRefundActivity extends MvpActivity<OrderRefundPresenter> implements OrderRefundView {

    @BindView(R.id.order_no)
    ItemArrowLayout orderNo;
    @BindView(R.id.order_store_name)
    ItemArrowLayout orderStoreName;
    @BindView(R.id.order_money)
    ItemArrowLayout orderMoney;
    @BindView(R.id.refund_amount_edt)
    EditText refundAmountEdt;
    @BindView(R.id.yuan_unit)
    TextView yuanUnit;
    @BindView(R.id.oil_edit_text)
    EditText passwordEdt;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.refundable_amount)
    ItemArrowLayout refundableAmount;

    private OrderDetailsBean orderDetailsBean;
    String password;
    String refundAmount;
    String orderId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_refund);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setTitle("退款");
        refundAmountEdt.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(".") && dest.toString().length() == 0) {
                    return "0.";
                }
                if (dest.toString().contains(".")) {
                    int index = dest.toString().indexOf(".");
                    int length = dest.toString().substring(index).length();
                    if (length == 3) {
                        return "";
                    }
                }
                return null;
            }
        }});
        orderDetailsBean = (OrderDetailsBean) getIntent().getSerializableExtra("order");
        if (orderDetailsBean != null) {
            orderId = orderDetailsBean.getPayment().getPay_orderid();
            orderNo.setDes(orderDetailsBean.getPayment().getPay_orderid());
            orderStoreName.setDes(orderDetailsBean.getPayment().getStore_name());
            refundAmount = orderDetailsBean.getPayment().getRefundable_amount();
            refundableAmount.setDes(refundAmount + "元");
            orderMoney.setDes(orderDetailsBean.getPayment().getPay_amount() + "元");
            refundAmountEdt.setText(refundAmount);
//            refundAmountEdt.setEnabled(false);
        }
    }

    @Override
    protected OrderRefundPresenter createPresenter() {
        return new OrderRefundPresenter(this, this);
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        if (refundAmountEdt.getText().toString().isEmpty()) {
            ToastUitl.show("请输入的退款金额");
            return;
        } else if (Float.parseFloat(refundAmountEdt.getText().toString()) == 0) {
            ToastUitl.show("退款金额不能为0");
            return;
        } else if (Float.parseFloat(refundAmountEdt.getText().toString()) > Float.parseFloat(refundAmount)) {
            ToastUitl.show("请输入的退款金额小于交易金额");
            return;
        }
        password = passwordEdt.getText().toString();
        if (password.isEmpty()) {
            ToastUitl.show("请输入登录密码");
            return;
        }
        refundAmount = refundAmountEdt.getText().toString();
        JSONObject refundRequestObject = new JSONObject();
        try {
            refundRequestObject.put("pay_orderid", orderId);
            refundRequestObject.put("money", refundAmount);
            refundRequestObject.put("manager_pwd", password);
            String jsonString = refundRequestObject.toString();
            mvpPresenter.requestRefund(AESUtils.encrypt(jsonString, SPUtil.getInstance().getString("KEY")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refundSuccess(RefundBean data) {
        ToastUitl.show(data.getMsg());
        EventBus.getDefault().post(new OrdersRefreshEvent(REFUND_SUCCESS));
        setResult(RESULT_OK);
        finish();
    }
}
