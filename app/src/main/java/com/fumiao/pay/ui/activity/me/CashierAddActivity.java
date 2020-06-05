package com.fumiao.pay.ui.activity.me;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import com.fumiao.core.uitls.AESUtils;
import com.fumiao.core.uitls.PermissionUtil;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.mvp.me.CashierAddPresenter;
import com.fumiao.pay.mvp.me.CashierAddView;
import com.fumiao.pay.ui.activity.home.ScanQrCodeActivity;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import org.json.JSONException;
import org.json.JSONObject;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class CashierAddActivity extends MvpActivity<CashierAddPresenter> implements CashierAddView{

    private int store_id;
    int QR_CODE_REQUEST = 556;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_add);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected CashierAddPresenter createPresenter() {
        return new CashierAddPresenter(this, this);
    }

    private void init() {
        store_id = SPUtil.getInstance().getInt(STORE_ID);
        setTitle("添加二维码");
    }

    @OnClick(R.id.btn_comfirm)
    public void onViewClicked() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        PermissionUtil.getInstance().check(permission, CashierAddActivity.this, "相机", new PermissionUtil.Callback() {
                            @Override
                            public void onSucceed() {
                                Bundle bundle = new Bundle();
                                bundle.putInt("scan_status", 1);
                                jumpActivityForResult(ScanQrCodeActivity.class, bundle, QR_CODE_REQUEST);
                            }
                        });
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK ) {
            if (requestCode == QR_CODE_REQUEST){
                String resultData = data.getStringExtra("qr_code");
                if(!resultData.isEmpty()){
                    String qrCode = resultData.substring(resultData.lastIndexOf("/")+1);
                    JSONObject bindCashierObject = new JSONObject();
                    try {
                        bindCashierObject.put("store_id", store_id);
                        bindCashierObject.put("serial_number", qrCode);
                        String jsonString = bindCashierObject.toString();
                        mvpPresenter.bindCashierQrcode(AESUtils.encrypt(jsonString , SPUtil.getInstance().getString( "KEY")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void bindCashierQrcodeSuccess() {

    }
}
