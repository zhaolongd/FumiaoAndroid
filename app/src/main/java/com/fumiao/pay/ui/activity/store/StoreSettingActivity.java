package com.fumiao.pay.ui.activity.store;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.fumiao.core.uitls.GlideTool;
import com.fumiao.core.uitls.SelectImageUtils;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.widget.CircleImageView;
import com.fumiao.core.widget.ItemArrowLayout;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.store.StoreDetailsBean;
import com.fumiao.pay.mvp.store.StoreSettingPresenter;
import com.fumiao.pay.mvp.store.StoreSettingView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import java.io.File;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreSettingActivity extends MvpActivity<StoreSettingPresenter> implements StoreSettingView {
    @BindView(R.id.store_head)
    CircleImageView storeHead;
    @BindView(R.id.store_head_layout)
    LinearLayout storeHeadLayout;
    @BindView(R.id.store_name)
    ItemArrowLayout storeName;
    @BindView(R.id.store_type)
    ItemArrowLayout storeType;
    @BindView(R.id.addr)
    ItemArrowLayout addr;
    @BindView(R.id.ed_address)
    EditText edAddress;

    int IMG_REQUEST = 200;
    String avatar_url = "";
    int STORE_TYPE = 201;

    String cate_name;
    String cate_id;
    int id;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private boolean isEditStore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_setting);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected StoreSettingPresenter createPresenter() {
        return new StoreSettingPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void init() {
        setTitle(R.string.store_info_setting);
        id = getIntent().getIntExtra("store_id", 0);
        if(id != 0){
            mvpPresenter.getStoreDetails(id);
        }

        if (isEditStore) {
            setTitle(R.string.store_info_setting);
            btnConfirm.setVisibility(View.VISIBLE);
        }else{
            setTitle("门店信息");
            storeType.setClickable(false);
            addr.setClickable(false);
            edAddress.setEnabled(false);
            btnConfirm.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == IMG_REQUEST) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                        avatar_url = selectList.get(0).getCompressPath();
                        Glide.with(StoreSettingActivity.this).load(new File(avatar_url)).into(storeHead);
                        mvpPresenter.editStore(id, avatar_url, "", "", "", "", "", "", "", "", "", "");
                    }
                }, 1000);
            } else if (requestCode == STORE_TYPE) {
                cate_name = data.getStringExtra("cate_name");
                cate_id = data.getStringExtra("cate_id");
                storeType.setDes(cate_name + "");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SelectImageUtils.getInsingle(this).clear();
    }

    @Override
    public void editSuccess() {
        ToastUitl.show("修改成功");
    }

    @Override
    public void showStoreDetails(StoreDetailsBean data) {
        if(data != null){
            List<String> cateTages = data.getStore().getCate_tags();
            final String regularExpression = ",";
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < cateTages.size(); i++) {
                stringBuilder.append(cateTages.get(i));
                if (i != cateTages.size() - 1) {
                    stringBuilder.append(regularExpression);
                }
            }
            cate_name = stringBuilder.toString();
            cate_id = data.getStore().getCate_id();
            id = data.getStore().getId();
            GlideTool.setImageResource(storeHead, data.getStore().getImage(), R.mipmap.home_head);
            storeName.setDes(data.getStore().getName());
            storeType.setDes(cate_name);
            addr.setDes(data.getStore().getProvince_name() + data.getStore().getCity_name() + data.getStore().getArea_name());
            edAddress.setText(data.getStore().getAddress());
        }
    }


    @OnClick({R.id.store_head_layout, R.id.btn_confirm, R.id.addr, R.id.store_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.store_head_layout:
                SelectImageUtils.getInsingle(this).showWindow(storeHeadLayout, IMG_REQUEST, true);
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
}
