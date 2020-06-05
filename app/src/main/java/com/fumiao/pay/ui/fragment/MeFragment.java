package com.fumiao.pay.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fumiao.core.uitls.AESUtils;
import com.fumiao.core.uitls.GlideTool;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.widget.CircleImageView;
import com.fumiao.core.widget.ItemArrowLayout;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpFragment;
import com.fumiao.pay.bean.home.CashierDetailBean;
import com.fumiao.pay.bean.store.MemberDetailsBean;
import com.fumiao.pay.mvp.me.MePresenter;
import com.fumiao.pay.mvp.me.MeView;
import com.fumiao.pay.ui.activity.me.CashierActivity;
import com.fumiao.pay.ui.activity.me.SettingActivity;
import com.fumiao.pay.ui.activity.store.MemberManageActivity;
import com.fumiao.pay.ui.activity.store.StoreManageActivity;
import com.fumiao.pay.ui.activity.store.StoreSettingActivity;
import com.fumiao.pay.ui.dialog.GuideDialog;
import org.json.JSONException;
import org.json.JSONObject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class MeFragment extends MvpFragment<MePresenter> implements MeView {
    Unbinder unbinder;
    @BindView(R.id.store_name)
    TextView storeName;
    @BindView(R.id.btn_shop)
    ItemArrowLayout btnShop;
    @BindView(R.id.btn_manage)
    ItemArrowLayout btnManage;
    @BindView(R.id.btn_help)
    ItemArrowLayout btnHelp;
    @BindView(R.id.btn_setting)
    ItemArrowLayout btnSetting;
    @BindView(R.id.btn_feedback)
    ItemArrowLayout btnFeedback;
    @BindView(R.id.shop_head)
    CircleImageView shopHead;
    @BindView(R.id.label_layout)
    LinearLayout labelLayout; //店长界面显示门店分类标签，店员界面显示职位和姓名
    @BindView(R.id.menu_layout)
    LinearLayout menuLayout; //店长界面显示的菜单栏
    @BindView(R.id.btn_merchant_info)
    ItemArrowLayout btnMerchantInfo;

    int COLLECT_CHANNEL_REQUEST = 1; //收银通道
    int CASHIER_CODE_REQUEST = 2;  //收款码界面

    private boolean isMember = false;

    GuideDialog guideDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_me, null);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    @Override
    protected MePresenter createPresenter() {
        return new MePresenter(this, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void init() {
        guideDialog = new GuideDialog(getContext());
        guideDialog.setCanceledOnTouchOutside(true);
        isMember = SPUtil.getInstance().getBoolean(IS_MENBER);
        storeName.setText(SPUtil.getInstance().getString(STORE_NAME));
        String cate = SPUtil.getInstance().getString(STORE_CATE_TAGS);
        if (isMember) {
            menuLayout.setVisibility(View.GONE);
            btnManage.setVisibility(View.GONE);
            btnMerchantInfo.setVisibility(View.GONE);
            shopHead.setImageResource(R.mipmap.home_head);
            mvpPresenter.getClerkDetails(SPUtil.getInstance().getInt(LOGIN_ID));
        } else {
            menuLayout.setVisibility(View.VISIBLE);
            btnManage.setVisibility(View.VISIBLE);
            btnMerchantInfo.setVisibility(View.GONE);
            GlideTool.setImageResource(shopHead, SPUtil.getInstance().getString(STORE_IMAGE), R.mipmap.home_head);
            labelLayout.removeAllViews();
            String[] cateLable = cate.split(",");
            if (cateLable.length == 0) {
                labelLayout.setVisibility(View.GONE);
            } else {
                labelLayout.setVisibility(View.VISIBLE);
            }
            for (int i = 0; i < cateLable.length; i++) {
                String lable = cateLable[i];
                if(!lable.equals("")){
                    final View view = LayoutInflater.from(getContext()).inflate(R.layout.item_me_type, null);
                    final TextView textView = view.findViewById(R.id.store_cate);
                    textView.setText(lable);
                    labelLayout.addView(view);
                }
            }
        }
    }

    @OnClick({R.id.lin_collection_tool, R.id.lin_store_info, R.id.btn_shop, R.id.btn_manage, R.id.btn_help, R.id.btn_setting, R.id.btn_merchant_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_collection_tool:
                jumpActivity(CashierActivity.class);
                break;
            case R.id.lin_store_info:
                Bundle bundle = new Bundle();
                bundle.putInt("store_id", SPUtil.getInstance().getInt(STORE_ID));
                jumpActivity(StoreSettingActivity.class, bundle);
                break;
            case R.id.btn_shop:
                jumpActivity(StoreManageActivity.class);
                break;
            case R.id.btn_manage:
                jumpActivity(MemberManageActivity.class);
                break;
            case R.id.btn_help:
                Toast.makeText(getActivity(), "功能暂未开放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_setting:
                jumpActivity(SettingActivity.class);
                break;
            case R.id.btn_merchant_info:
                mvpPresenter.getCashierDetail(SPUtil.getInstance().getInt(STORE_ID), COLLECT_CHANNEL_REQUEST);
                break;
        }
    }

    @Override
    public void cashierDetail(CashierDetailBean data, int request_code) {
       /* if (data != null) {
            int isOpenCashierAccount = data.getIsOpenCashierAccount(); // -1:未开通,0:审核中,1:正常，2:审核失败
            int isActivation = data.getIsActivation(); //0未绑码，1已绑码
            if (isOpenCashierAccount == 1 && isActivation == 0) {
                if (isMember) {
                    ToastUitl.show("请联系老板去激活卡台！");
                } else {
                    guideDialog.show("去激活", R.mipmap.bind_cashier_bg, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            guideDialog.dismiss();
                            RxPermissions rxPermissions = new RxPermissions(getActivity());
                            rxPermissions.requestEach(Manifest.permission.CAMERA)
                                    .subscribe(new Consumer<Permission>() {
                                        @Override
                                        public void accept(Permission permission) throws Exception {
                                            PermissionUtil.getInstance().check(permission, getContext(), "相机", new PermissionUtil.Callback() {
                                                @Override
                                                public void onSucceed() {
                                                    Bundle bundle = new Bundle();
                                                    bundle.putInt("scan_status", 1);
                                                    jumpActivityForResult(ScanQrCodeActivity.class, bundle, request_code);
                                                }
                                            });
                                        }
                                    });
                        }
                    });
                }
            } else if (isOpenCashierAccount == -1) {
                if (isMember) {
                    ToastUitl.show("请联系老板去开通收银通道！");
                } else {
                    guideDialog.show("去认证", R.mipmap.channel_authentication_bg, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            guideDialog.dismiss();
                            jumpActivity(ChannelApplyActivity.class);
                        }
                    });
                }
            } else if (isOpenCashierAccount == 1 && isActivation == 1) {
                if (request_code == CASHIER_CODE_REQUEST) {
                    jumpActivity(CashierActivity.class);
                }
            } else {
                if (isMember) {
                    ToastUitl.show("请联系老板去开通收银通道！");
                } else {
                    guideDialog.show("去认证", R.mipmap.channel_authentication_bg, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            guideDialog.dismiss();
                            jumpActivity(CollectionChannelActivity.class);
                        }
                    });
                }
            }
        }*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CASHIER_CODE_REQUEST) {
                String resultData = data.getStringExtra("qr_code");
                if (!resultData.isEmpty()) {
                    String qrCode = resultData.substring(resultData.lastIndexOf("/") + 1);
                    JSONObject bindCashierObject = new JSONObject();
                    try {
                        bindCashierObject.put("store_id", SPUtil.getInstance().getInt(STORE_ID));
                        bindCashierObject.put("serial_number", qrCode);
                        String jsonString = bindCashierObject.toString();
                        mvpPresenter.bindCashierQrcode(AESUtils.encrypt(jsonString, SPUtil.getInstance().getString("KEY")), requestCode);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void showClerkDetails(MemberDetailsBean data) {
        labelLayout.removeAllViews();
        labelLayout.setVisibility(View.VISIBLE);
        String[] lable = {data.getMember().getPosition(), data.getMember().getRealname()};
        for (int i = 0; i < lable.length; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_me_type, null);
            TextView textView = view.findViewById(R.id.store_cate);
            textView.setText(lable[i]);
            labelLayout.addView(view);
        }
    }

    @Override
    public void bindCashierQrcodeSuccess(int request_code) {
        if (request_code == CASHIER_CODE_REQUEST) {
            jumpActivity(CashierActivity.class);
        }
    }
}
