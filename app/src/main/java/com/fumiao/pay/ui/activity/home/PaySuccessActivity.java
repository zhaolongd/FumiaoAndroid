package com.fumiao.pay.ui.activity.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.DateUtils;
import com.fumiao.core.widget.ItemArrowLayout;
import com.fumiao.pay.R;
import com.fumiao.pay.app.BaseActivity;
import com.fumiao.pay.bean.main.PaySuccessBean;
import com.fumiao.pay.bean.main.ScanBean;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wang.avi.AVLoadingIndicatorView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaySuccessActivity extends BaseActivity {

    @BindView(R.id.loadView)
    AVLoadingIndicatorView loadView;
    @BindView(R.id.pay_tips)
    TextView payTips;
    ScanBean scanBean;
    int time;
    @BindView(R.id.pay_loading_layout)
    LinearLayout payLoadingLayout;
    @BindView(R.id.iv_status)
    ImageView ivStatus;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.order_money)
    ItemArrowLayout orderMoney;
    @BindView(R.id.pay_status_layout)
    LinearLayout payStatusLayout;
    @BindView(R.id.order_no)
    ItemArrowLayout orderNo;
    @BindView(R.id.order_time)
    ItemArrowLayout orderTime;

    private PaySuccessBean.OrderInfoBean orderInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setTitle("收款结果");
        payLoadingLayout.setVisibility(View.VISIBLE);
        payStatusLayout.setVisibility(View.GONE);
        loadView.show();
        scanBean = (ScanBean) getIntent().getSerializableExtra("data");
        handler.postDelayed(thread, 0);
    }

    Handler handler = new Handler();
    Thread thread = new Thread() {
        @Override
        public void run() {
            super.run();
            time++;
            //超过一分钟不支付显示超时
            if (time >= 20) {
                handler.removeCallbacks(thread);
                payLoadingLayout.setVisibility(View.GONE);
                payStatusLayout.setVisibility(View.VISIBLE);
                ivStatus.setImageResource(R.mipmap.ic_fail);
                tvStatus.setText("请求超时");
                return;
            }
            OkGo.<BaseResponse<PaySuccessBean>>post(scanBean.getQuery_url())
                    .params("order_id", scanBean.getOrder_id())
                    .execute(new JsonCallback<BaseResponse<PaySuccessBean>>(PaySuccessActivity.this, false) {
                        @Override
                        public void onSuccessCallback(Response<BaseResponse<PaySuccessBean>> response) {
                            updateView(response.body().data.getOrder_info());
                            switch (response.body().data.getStatus()) {
                                case 1:
                                    handler.removeCallbacks(thread);
                                    payLoadingLayout.setVisibility(View.GONE);
                                    payStatusLayout.setVisibility(View.VISIBLE);
                                    ivStatus.setImageResource(R.mipmap.ic_success);
                                    tvStatus.setText("收款成功");
                                    break;
                                case 2:
                                    handler.postDelayed(thread, 3000);
                                    //payTips.setText("等待支付结果");
                                    break;
                                case 3:
                                    handler.removeCallbacks(thread);
                                    payLoadingLayout.setVisibility(View.GONE);
                                    payStatusLayout.setVisibility(View.VISIBLE);
                                    ivStatus.setImageResource(R.mipmap.ic_fail);
                                    tvStatus.setText("收款失败");
                                    break;

                                case 4:
                                    handler.removeCallbacks(thread);
                                    payLoadingLayout.setVisibility(View.GONE);
                                    payStatusLayout.setVisibility(View.VISIBLE);
                                    ivStatus.setImageResource(R.mipmap.ic_fail);
                                    tvStatus.setText("支付取消");
                                    break;
                                case 9:
                                    handler.postDelayed(thread, 3000);
                                    //payTips.setText("等待支付结果");
                                    break;
                                case 0:
                                    //订单号有误
                                    handler.removeCallbacks(thread);
                                    payLoadingLayout.setVisibility(View.GONE);
                                    payStatusLayout.setVisibility(View.VISIBLE);
                                    ivStatus.setImageResource(R.mipmap.ic_fail);
                                    tvStatus.setText("收款失败");
                                    break;
                            }
                        }

                        @Override
                        public void onError(Response<BaseResponse<PaySuccessBean>> response) {
                            super.onError(response);
                            if (time >= 20) {
                                updateView(response.body().data.getOrder_info());
                                payLoadingLayout.setVisibility(View.GONE);
                                payStatusLayout.setVisibility(View.VISIBLE);
                                ivStatus.setImageResource(R.mipmap.ic_fail);
                                tvStatus.setText("请求超时");
                                return;
                            }
                            handler.postDelayed(thread, 3000);
                        }
                    });
        }
    };

    private void updateView(PaySuccessBean.OrderInfoBean data){
        if(data != null){
            orderMoney.setDes(data.getPayamount()+"元");
            orderNo.setDes(data.getPayorderid());
            orderTime.setDes(DateUtils.strToDate2(data.getPay_successdate()));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(thread);
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        finish();
    }
}
