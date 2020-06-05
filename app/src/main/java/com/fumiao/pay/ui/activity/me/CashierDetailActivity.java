package com.fumiao.pay.ui.activity.me;

import android.Manifest;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fumiao.core.uitls.AESUtils;
import com.fumiao.core.uitls.ImgUtils;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.home.CashierCodeBean;
import com.fumiao.pay.bean.home.CashierDetailBean;
import com.fumiao.pay.mvp.me.CashierDetailPresenter;
import com.fumiao.pay.mvp.me.CashierDetailView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CashierDetailActivity extends MvpActivity<CashierDetailPresenter> implements CashierDetailView {

    @BindView(R.id.qr_code)
    ImageView qrCode;
    @BindView(R.id.qr_code_layout)
    FrameLayout qrCodeLayout;
    @BindView(R.id.store_addr)
    TextView storeAddr;
    private int store_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        store_id = SPUtil.getInstance().getInt(STORE_ID);
        setTitle("收款码");
        mvpPresenter.getQrCodeDetail("", store_id);
    }

    @Override
    protected CashierDetailPresenter createPresenter() {
        return new CashierDetailPresenter(this, this);
    }

    @Override
    public void showCode(byte[] data) {
//        if (data != null) {
//            Glide.with(this).load(data).into(qrCode);
//        }
    }

    @Override
    public void showCashierDetail(CashierCodeBean cashierCodeBean) {
        if(cashierCodeBean != null){
            byte[] data = AESUtils.decryptTobyte( cashierCodeBean.getCode_url(), SPUtil.getInstance().getString( "KEY"));
            if (data != null) {
                Glide.with(this).load(data).into(qrCode);
            }
        }
    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        RxPermissions rxPermission = new RxPermissions(CashierDetailActivity.this);
        rxPermission
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        if (ImgUtils.saveImageToGallery(CashierDetailActivity.this, ImgUtils.getViewBitmap(qrCodeLayout))) {
                            ToastUitl.show(getString(R.string.keep_success));
                        } else {
                            ToastUitl.show(getString(R.string.keep_fail));
                        }
                    } else {
                        ToastUitl.show(getString(R.string.lack_authority));
                    }
                });
    }
}
