package com.fumiao.pay.ui.activity.store;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.fumiao.core.uitls.Callback;
import com.fumiao.core.uitls.EmojiFilter;
import com.fumiao.core.uitls.ImgUtils;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.SelectImageUtils;
import com.fumiao.core.uitls.TViewUtils;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.widget.CircleImageView;
import com.fumiao.core.widget.ItemArrowLayout;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.store.StoreAddBean;
import com.fumiao.pay.mvp.store.StoreAddPresenter;
import com.fumiao.pay.mvp.store.StoreAddView;
import com.fumiao.pay.tools.SelectAddrModule;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreAddActivity extends MvpActivity<StoreAddPresenter> implements StoreAddView {

    @BindView(R.id.store_head)
    CircleImageView storeHead;
    @BindView(R.id.store_head_layout)
    LinearLayout storeHeadLayout;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.store_type)
    ItemArrowLayout storeType;
    @BindView(R.id.addr)
    ItemArrowLayout addr;
    @BindView(R.id.ed_address)
    EditText edAddress;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    SelectAddrModule selectAddrModule;
    String province = "", province_name = "", city = "", city_name = "", area = "", area_name = "";
    int IMG_REQUEST = 200;
    String avatar_url = "";
    int STORE_TYPE = 201;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store, true);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setTitle("添加门店");
        selectAddrModule = new SelectAddrModule(this, new Callback<String>() {
            @Override
            public void onSuccess(String[] s) {
                province_name = s[0];
                province = s[1];
                city_name = s[2];
                city = s[3];
                area_name = s[4];
                area = s[5];
                addr.setDes(province_name + city_name + area_name);
            }
        });
        edAddress.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100), new EmojiFilter()});
        edName.setFilters(new InputFilter[]{new EmojiFilter()});
    }


    @Override
    protected StoreAddPresenter createPresenter() {
        return new StoreAddPresenter(this, this);
    }

    @OnClick({R.id.store_head_layout, R.id.store_type, R.id.addr, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.store_head_layout:
                SelectImageUtils.getInsingle(this).showWindow(storeHeadLayout, IMG_REQUEST, true);
                break;
            case R.id.store_type:
                if(cate_id != null && !"".equals(cate_id)){
                    Bundle bundle = new Bundle();
                    bundle.putString("cate_id", cate_id);
                    jumpActivityForResult(StoreTypeActivity.class, bundle, STORE_TYPE);
                }else {
                    jumpActivityForResult(StoreTypeActivity.class, STORE_TYPE);
                }
                break;
            case R.id.addr:
                selectAddrModule.showPickerView(new Callback<String>() {
                    @Override
                    public void onSuccess(String[] s) {
                        province_name = s[0];
                        province = s[1];
                        city_name = s[2];
                        city = s[3];
                        area_name = s[4];
                        area = s[5];
                        addr.setDes(province_name + city_name + area_name);
                    }
                }, addr, (RelativeLayout) findViewById(R.id.rootview));
                break;
            case R.id.btn_confirm:
                if (TViewUtils.isEmpty(edName)) {
                    ToastUitl.show("请填写门店名称");
                    return;
                }
                if (cate_name == null || cate_name.equals("")) {
                    ToastUitl.show("请选择主营类型");
                    return;
                }
                if (province_name == null || province_name.equals("")) {
                    ToastUitl.show("请填选择地区");
                    return;
                }
                if (TViewUtils.isEmpty(edAddress)) {
                    ToastUitl.show("请填写门店地址");
                    return;
                }

                if(avatar_url == null || "".equals(avatar_url)){
                    //给默认图片需要读写权限
                    RxPermissions rxPermission = new RxPermissions(StoreAddActivity.this);
                    rxPermission
                            .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                            .subscribe(granted -> {
                                if (granted) { // Always true pre-M
                                    avatar_url = ImgUtils.getPathFormDrawableRes(this, R.mipmap.home_head);
                                    mvpPresenter.addStore("", avatar_url, edName.getText().toString(), cate_id, cate_name, "", province, province_name, city, city_name, area, area_name, edAddress.getText().toString());
                                } else {
                                    ToastUitl.show(getString(R.string.lack_authority));
                                    return;
                                }
                            });
                }else {
                    mvpPresenter.addStore("", avatar_url, edName.getText().toString(), cate_id, cate_name, "", province, province_name, city, city_name, area, area_name, edAddress.getText().toString());
                }
                break;
        }
    }

    private String addressBuilder(String province, String city, String area) {
        String address = "";
        if (province != null && !province.isEmpty()) {
            address = province;
        }
        if (city != null && !city.isEmpty()) {
            if (address != null && !address.isEmpty()) {
                address = address + "-" + city;
            } else {
                address = city;
            }
        }
        if (area != null && !area.isEmpty()) {
            if (address != null && !address.isEmpty()) {
                address = address + "-" + area;
            } else {
                address = area;
            }
        }
        return address;
    }

    String cate_name;
    String cate_id;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK ) {
            if(requestCode == IMG_REQUEST){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                        avatar_url = selectList.get(0).getCompressPath();
                        Glide.with(StoreAddActivity.this).load(new File(avatar_url)).into(storeHead);
                    }
                }, 1500);
            }else if(requestCode == STORE_TYPE){
                cate_name = data.getStringExtra("cate_name");
                cate_id = data.getStringExtra("cate_id");
                storeType.setDes(cate_name+"");
            }
        }
    }

    @Override
    public void addSuccess(StoreAddBean storeAddBean) {
        ToastUitl.show("添加成功");
        SPUtil.getInstance().putInt(STORE_ID, storeAddBean.getStore_id());
        finish();
    }
}
