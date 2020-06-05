package com.fumiao.pay.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fumiao.core.uitls.L;
import com.fumiao.pay.R;
import com.fumiao.pay.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;



public class ScanQrCodeActivity extends BaseActivity implements QRCodeView.Delegate {

    @BindView(R.id.zbarview)
    ZBarView zbarview;
    @BindView(R.id.order_money)
    TextView orderMoney;
    String money;
    int scanQrStatus = 0; //1:需要绑定二维码
    @BindView(R.id.order_money_layout)
    LinearLayout orderMoneyLayout;
//    @BindView(R.id.instruction_layout)
//    LinearLayout instructionLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code, false);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        zbarview.setDelegate(this);
        zbarview.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZBarView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
        money = getIntent().getStringExtra("order_money");
        scanQrStatus = getIntent().getIntExtra("scan_status", 0);
        if (scanQrStatus == 1) {
            setTitle("绑定卡台");
            orderMoneyLayout.setVisibility(View.GONE);
//            instructionLayout.setVisibility(View.GONE);
        } else {
            orderMoneyLayout.setVisibility(View.VISIBLE);
//            instructionLayout.setVisibility(View.VISIBLE);
            setTitle("收款");
        }
        orderMoney.setText(money + "");

    }

    @Override
    protected void onResume() {
        zbarview.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
        super.onResume();
    }

    @Override
    protected void onStop() {
        //zbarview.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zbarview.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        zbarview.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
//        vibrate();
//        zbarview.startSpot(); // 延迟0.5秒后开始识别
        L.e("result:" + result);
        Intent intent = new Intent();
        intent.putExtra("qr_code", result);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        L.e("打开相机出错");
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }


//    @OnClick(R.id.user_instruction)
//    public void onViewClicked() {
//        jumpActivity(CollectUserHelpActivity.class);
//    }
}
