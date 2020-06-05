package com.fumiao.pay.ui.activity.home;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fumiao.core.uitls.GlideTool;
import com.fumiao.core.uitls.KeyboardUtil;
import com.fumiao.core.uitls.PermissionUtil;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.TViewUtils;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.widget.CircleImageView;
import com.fumiao.core.widget.MoneyTextWatcher;
import com.fumiao.core.widget.MyKeyBoardView;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.main.ScanBean;
import com.fumiao.pay.mvp.home.CollectMoneyPresenter;
import com.fumiao.pay.mvp.home.CollectMoneyView;
import com.fumiao.pay.ui.activity.me.CashierDetailActivity;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.text.DecimalFormat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Author: XieBoss
 * Date: 2019/8/7 0007 11:18
 *
 * @Description:
 */
public class CollectMoneyActivity extends MvpActivity<CollectMoneyPresenter> implements CollectMoneyView {

    @BindView(R.id.home_head)
    CircleImageView homeHead;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_store)
    TextView tvStore;
    @BindView(R.id.ed_money)
    EditText edMoney;
    @BindView(R.id.ed_remarks)
    EditText edRemarks;
    @BindView(R.id.keyboard_view)
    MyKeyBoardView keyboardView;
    @BindView(R.id.btn_confirm)
    LinearLayout btnConfirm;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    KeyboardUtil keyboardUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_money);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected CollectMoneyPresenter createPresenter() {
        return new CollectMoneyPresenter(this, this);
    }

    public void init() {
        final Vibrator vibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        keyboardUtil = new KeyboardUtil(CollectMoneyActivity.this);
        keyboardUtil.setOnOkClick(new KeyboardUtil.OnOkClick() {
            @Override
            public void onOkClick() {
                if (TViewUtils.isEmpty(edMoney)) {
                    ToastUitl.error("请输入收款金额");
                    return;
                }
                if (Float.parseFloat(edMoney.getText().toString()) <= 0) {
                    ToastUitl.error("请输入大于零元");
                    return;
                }
                jumpScanQRCode();

            }
        });
        keyboardUtil.attachTo(edMoney);
        setTitle("收款");
        setRight("收款码", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpActivity(CashierDetailActivity.class);
            }
        });

        edMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyboardUtil.attachTo(edMoney);
                keyboardView.setVisibility(View.VISIBLE);
            }
        });

        edMoney.addTextChangedListener(new MoneyTextWatcher(edMoney) {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                vibrator.vibrate(50);//振动持续时间
                if (edMoney.getText().toString().trim().isEmpty()) {
                    btnConfirm.setBackgroundResource(R.drawable.btn_nopress_payment);
                } else {
                    btnConfirm.setBackgroundResource(R.drawable.btn_press_bg);
                }
            }
        });

        edMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                keyboardUtil.attachTo(edMoney);
                keyboardView.setVisibility(hasFocus ? View.VISIBLE : View.GONE);
            }
        });

        edRemarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                keyboardView.setVisibility(hasFocus ? View.GONE : View.VISIBLE);
            }
        });
        tvStoreName.setText(SPUtil.getInstance().getString(STORE_NAME));
        tvAddress.setText(SPUtil.getInstance().getString(STORE_ADDRESS));
        GlideTool.setImageResource(homeHead, SPUtil.getInstance().getString(STORE_IMAGE), R.mipmap.home_head);
    }

    @Override
    public void showPay(ScanBean data) {
        if(data != null){
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", data);
            bundle.putString("order_money", decimalToString(edMoney.getText().toString()));
            jumpActivity(PaySuccessActivity.class, bundle);
        }
    }

    @Override
    public void jumpScanQRCode() {
        RxPermissions rxPermissions = new RxPermissions(CollectMoneyActivity.this);
        rxPermissions.requestEach(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        PermissionUtil.getInstance().check(permission, context, "相机", new PermissionUtil.Callback() {
                            @Override
                            public void onSucceed() {
                                Bundle bundle = new Bundle();
                                bundle.putString("order_money", decimalToString(edMoney.getText().toString()));
                                jumpActivityForResult(ScanQrCodeActivity.class, bundle, QR_CODE_REQUEST);
                            }
                        });
                    }
                });
    }

    int QR_CODE_REQUEST = 556;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == QR_CODE_REQUEST) {
            mvpPresenter.scan(data.getStringExtra("qr_code"), store_id + "", edMoney.getText().toString(), edRemarks.getText().toString());
        }
    }

    public String decimalToString(String num){
        if(num.isEmpty()){
            return "";
        }
        Double cny = Double.parseDouble(num);//转换成Double
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(cny);
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        if (TViewUtils.isEmpty(edMoney)) {
            ToastUitl.error("请输入收款金额");
            return;
        }
        if (Float.parseFloat(edMoney.getText().toString()) <= 0) {
            ToastUitl.error("请输入大于零元");
            return;
        }
        jumpScanQRCode();
    }
}
