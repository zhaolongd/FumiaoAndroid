package com.fumiao.pay.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.uitls.AESUtils;
import com.fumiao.core.uitls.PermissionUtil;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.uitls.UnitTools;
import com.fumiao.core.uitls.Utils;
import com.fumiao.core.widget.NoScrollRecyclerView;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpFragment;
import com.fumiao.pay.bean.home.ArticleBean;
import com.fumiao.pay.bean.home.BannerBean;
import com.fumiao.pay.bean.home.CashierCodeBean;
import com.fumiao.pay.bean.home.HomeBean;
import com.fumiao.pay.bean.home.HomeMenuBean;
import com.fumiao.pay.bean.home.PaymentRecordListBean;
import com.fumiao.pay.bean.home.TodayPaymentBean;
import com.fumiao.pay.event.HomeTopEvent;
import com.fumiao.pay.mvp.home.HomePresenter;
import com.fumiao.pay.mvp.home.HomeView;
import com.fumiao.pay.ui.activity.home.BillReportActivity;
import com.fumiao.pay.ui.activity.home.CollectMoneyActivity;
import com.fumiao.pay.ui.activity.home.OrderDetailsActivity;
import com.fumiao.pay.ui.activity.home.ReconciliationsActivity;
import com.fumiao.pay.ui.activity.home.ScanQrCodeActivity;
import com.fumiao.pay.ui.activity.me.CashierDetailActivity;
import com.fumiao.pay.ui.adapter.ArticleAdapter;
import com.fumiao.pay.ui.adapter.HomeAdapter;
import com.fumiao.pay.ui.adapter.WebBannerAdapter;
import com.fumiao.pay.ui.dialog.GuideDialog;
import com.fumiao.pay.widget.BannerLayout;
import com.fumiao.pay.widget.MarqueeView;
import com.fumiao.pay.widget.MyNestedScrollView;
import com.fumiao.pay.widget.WebActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import static android.app.Activity.RESULT_OK;
import static com.fumiao.pay.widget.WebActivity.WEB_TITLE;
import static com.fumiao.pay.widget.WebActivity.WEB_URL;

public class HomeFragment extends MvpFragment<HomePresenter> implements HomeView {

    Unbinder unbinder;
    @BindView(R.id.menu_rcy)
    NoScrollRecyclerView menuRcy;
    ArrayList<HomeMenuBean> homeMenuBeans = new ArrayList<>();
    ArrayList<ArticleBean> articleBeans = new ArrayList<>();
    ArrayList<BannerBean> bannerBeans = new ArrayList<>();
    HomeAdapter homeAdapter;

    ArticleAdapter articleAdapter;
    WebBannerAdapter webBannerAdapter;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.article_rcy)
    NoScrollRecyclerView articleRcy;
    @BindView(R.id.home_scroll_layout)
    MyNestedScrollView homeScrollLayout;
    @BindView(R.id.top_tools_layout)
    LinearLayout topToolsLayout;
    @BindView(R.id.bill_transaction_layout)
    LinearLayout billTransactionLayout;
    @BindView(R.id.banner_layout)
    BannerLayout bannerLayout;
    @BindView(R.id.refresh_Layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_order_count)
    TextView tvOrderCount;
    @BindView(R.id.tv_order_money_sum)
    TextView tvOrderMoneySum;
    @BindView(R.id.article_title_layout)
    LinearLayout articleTitleLayout;
    @BindView(R.id.home_main_layout)
    LinearLayout homeMainLayout;

    private int billTransactionTop;
    private int windowHeight; //屏幕的高度
    private int store_id = 0;

    int COLLECT_MONEY_REQUEST = 1; //收款界面
    int CASHIER_REQUEST = 2;  //收银台界面
    GuideDialog guideDialog;

    private ArrayList<PaymentRecordListBean.PaymentRecordBean> paymentRecordBeans = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    private void init() {
        guideDialog = new GuideDialog(getContext());
        guideDialog.setCanceledOnTouchOutside(true);
        EventBus.getDefault().register(this);
        windowHeight = Utils.getWindowHeight(getActivity());
        mvpPresenter.getHomeData();
        homeAdapter = new HomeAdapter(getContext(), R.layout.item_home_menu, homeMenuBeans);
        menuRcy.setAdapter(homeAdapter);
        menuRcy.setLayoutManager(new GridLayoutManager(getContext(), 4));
        menuRcy.setHasFixedSize(true);
        menuRcy.setNestedScrollingEnabled(false);

        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Bundle bundle = new Bundle();
                bundle.putString("id", paymentRecordBeans.get(position).getPay_orderid());
                jumpActivity(OrderDetailsActivity.class, bundle);
            }
        });

        homeAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                if (homeMenuBeans.get(position).getAndroid_url().contains("CashierDetailActivity")) {
                    mvpPresenter.getCashierDetail(SPUtil.getInstance().getInt(STORE_ID), CASHIER_REQUEST);
                } else {
                    jumpActivity(homeMenuBeans.get(position).getAndroid_url());
                }
            }
        });

        articleAdapter = new ArticleAdapter(getContext(), R.layout.item_home_article, articleBeans);

        articleAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(WEB_TITLE, articleBeans.get(position).getTitle());
                bundle.putString(WEB_URL, articleBeans.get(position).getUrl());
                jumpActivity(WebActivity.class, bundle);
            }
        });
        articleRcy.setAdapter(articleAdapter);
        articleRcy.setLayoutManager(new LinearLayoutManager(getContext()));
        articleRcy.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        billTransactionTop = UnitTools.dp2px(getContext(), 250);
        homeScrollLayout.setOnScrollListener(new MyNestedScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
//                if (scrollY >= billTransactionTop) {
//                    topToolsLayout.setVisibility(View.VISIBLE);
//                } else {
//                    topToolsLayout.setVisibility(View.GONE);
//                }

                if (scrollY > windowHeight) {
                    EventBus.getDefault().post(new HomeTopEvent(1));
                } else {
                    EventBus.getDefault().post(new HomeTopEvent(2));
                }
            }
        });

        webBannerAdapter = new WebBannerAdapter(getContext(), bannerBeans);
        webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString(WEB_TITLE, bannerBeans.get(position).getTitle());
                bundle.putString(WEB_URL, bannerBeans.get(position).getUrl());
                jumpActivity(WebActivity.class, bundle);
            }
        });
        bannerLayout.setAdapter(webBannerAdapter);

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getHomeData();
                getTodayPayment();
                getPaymentRecord();
                refreshLayout.finishRefresh();  //刷新完成
            }
        });
    }

    private void getPaymentRecord() {
        store_id = SPUtil.getInstance().getInt(STORE_ID);
        if (store_id != 0) {
            mvpPresenter.getPaymentRecord(store_id);
        }
    }


    private void getTodayPayment() {
        store_id = SPUtil.getInstance().getInt(STORE_ID);
        if (store_id != 0) {
            mvpPresenter.getTodayPayment(store_id);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(HomeTopEvent event) {
        if (event.getMsg() == 3) {
            homeScrollLayout.post(new Runnable() {
                @Override
                public void run() {
                    homeScrollLayout.fling(0);
                    homeScrollLayout.smoothScrollTo(0, 0);
                }
            });
        }
    }

    @OnClick({R.id.btn_collect_money, R.id.btn_reconciliations, R.id.btn_analysis})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_collect_money:
               mvpPresenter.getCashierDetail(SPUtil.getInstance().getInt(STORE_ID), COLLECT_MONEY_REQUEST);
                break;
            case R.id.btn_reconciliations:
                jumpActivity(ReconciliationsActivity.class);
                break;
            case R.id.btn_analysis:
                jumpActivity(BillReportActivity.class);
                break;
        }
    }

    @Override
    public void showHomeView(HomeBean data) {
        if (data != null) {
            //顶部界面背景图需网络加载
            Glide.with(getActivity())
                    .asBitmap()
                    .load(data.getBgi())
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            Drawable drawable = new BitmapDrawable(resource);
                            homeMainLayout.setBackgroundDrawable(drawable);
                        }
                    });
            homeMenuBeans.clear();
            articleBeans.clear();
            bannerBeans.clear();
            homeMenuBeans.addAll(data.getApplication());
            homeAdapter.notifyDataSetChanged();
            if (data.getBanner_list().size() > 0) {
                bannerLayout.setVisibility(View.VISIBLE);
                bannerBeans.addAll(data.getBanner_list());
                //刷新广告栏指示器
                bannerLayout.refreshIndicator();
                bannerLayout.setAutoPlaying(true);
                webBannerAdapter.notifyDataSetChanged();
            } else {
                bannerLayout.setVisibility(View.GONE);
            }

            if (data.getArticle_list().size() > 0) {
                articleTitleLayout.setVisibility(View.VISIBLE);
                articleBeans.addAll(data.getArticle_list());
                articleAdapter.notifyDataSetChanged();
            } else {
                articleTitleLayout.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showTodayPayment(TodayPaymentBean data) {
        tvOrderCount.setText("今日交易" + data.getOrder_count() + "笔，总金额为");
        tvOrderMoneySum.setText(doubleToString(data.getOrder_money_sum()) + "元");
    }

    @Override
    public void cashierDetail(CashierCodeBean data, int request_code) {
        if (data != null) {
            boolean isMember = SPUtil.getInstance().getBoolean(IS_MENBER);
            int is_activation = data.getIs_activation(); // 1：未激活 2：已激活
            if (is_activation == 1) {
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
            } else if (is_activation == 2) {
                if (request_code == COLLECT_MONEY_REQUEST) {
                    jumpActivity(CollectMoneyActivity.class);
                } else if (request_code == CASHIER_REQUEST) {
                    jumpActivity(CashierDetailActivity.class);
                }
            }
        }
    }

    @Override
    public void bindCashierQrcodeSuccess(int request_code) {
        if (request_code == COLLECT_MONEY_REQUEST) {
            jumpActivity(CollectMoneyActivity.class);
        } else if (request_code == CASHIER_REQUEST) {
            jumpActivity(CashierDetailActivity.class);
        }
    }

    @Override
    public void showPaymentRecord(PaymentRecordListBean listData) {
        if (listData != null) {
            paymentRecordBeans.clear();
            paymentRecordBeans.addAll(listData.getArrived_list());
            marqueeView.startWithList(paymentRecordBeans);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CASHIER_REQUEST || requestCode == COLLECT_MONEY_REQUEST) {
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
    public void onResume() {
        super.onResume();
        getTodayPayment();
        getPaymentRecord();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getTodayPayment();
            getPaymentRecord();
        }
    }

    public String doubleToString(double num) {
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }
}
