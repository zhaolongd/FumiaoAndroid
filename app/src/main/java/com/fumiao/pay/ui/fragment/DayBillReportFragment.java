package com.fumiao.pay.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpFragment;
import com.fumiao.pay.bean.home.DayBillReportBean;
import com.fumiao.pay.bean.home.PieChartBean;
import com.fumiao.pay.event.BillRefreshEvent;
import com.fumiao.pay.mvp.home.DayBillReportPresenter;
import com.fumiao.pay.mvp.home.DayBillReportView;
import com.fumiao.pay.tools.DecimalFormatter;
import com.fumiao.pay.tools.MyBarDataSet;
import com.fumiao.pay.ui.activity.home.SelectDayReportActivity;
import com.fumiao.pay.widget.PieChart;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class DayBillReportFragment extends MvpFragment<DayBillReportPresenter> implements DayBillReportView {

    Unbinder unbinder;
    @BindView(R.id.trendBarChart)
    BarChart trendBarChart;
    @BindView(R.id.pieChartView)
    PieChart pieChartView;
    @BindView(R.id.bill_date)
    TextView billDate;
    @BindView(R.id.select_date_layout)
    LinearLayout selectDateLayout;
    @BindView(R.id.bill_number)
    TextView billNumber;
    @BindView(R.id.bill_total)
    TextView billTotal;
    @BindView(R.id.refund_number)
    TextView refundNumber;
    @BindView(R.id.refund_total)
    TextView refundTotal;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.total_amount_sum)
    TextView totalAmountSum;
    @BindView(R.id.compareBarChart)
    BarChart compareBarChart;
    @BindView(R.id.high_amount_time)
    TextView highAmountTime;
    @BindView(R.id.high_amount_detail)
    TextView highAmountDetail;
    @BindView(R.id.amount_compare_detail)
    TextView amountCompareDetail;

    int SELECT_CANLENDA = 1;
    @BindView(R.id.report_chart_layout)
    LinearLayout reportChartLayout;
    @BindView(R.id.empty_layout)
    LinearLayout emptyLayout;
    @BindView(R.id.compare_arrow)
    ImageView compareArrow;
    @BindView(R.id.compare_amount)
    TextView tvCompareAmount;
    private int store_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_bill_report, null);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    private void init() {
        store_id = SPUtil.getInstance().getInt(STORE_ID);
        mvpPresenter.getDayBillReport(store_id, "");
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getDayBillReport(store_id, search_day);
            }
        });
        initBarChartView(trendBarChart, 24, 8);
        initBarChartView(compareBarChart, 2, 2);
        initPieChartView();
    }


    List<String> xAxisValue = new ArrayList<>();
    List<String> xCompareAxisValue = new ArrayList<>();

    private void initBarChartView(BarChart barChart, int num, int showNum) {
        barChart.setDrawValueAboveBar(true);//true文字绘画在bar上
        //设置描述不显示
        barChart.getDescription().setEnabled(false);
        barChart.setPinchZoom(false);//false只能单轴缩放
        barChart.setDrawGridBackground(false);
        barChart.getAxisLeft().setDrawZeroLine(true);
        //去掉左侧Y轴刻度
        barChart.getAxisLeft().setDrawLabels(false);
        //去掉左侧Y轴
        barChart.getAxisLeft().setDrawAxisLine(false);
        //去掉中间竖线
        barChart.getXAxis().setDrawGridLines(false);
        //去掉中间横线
        barChart.getAxisLeft().setDrawGridLines(false);

        //不使用右侧Y轴
        barChart.getAxisRight().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setAxisLineColor(getResources().getColor(R.color.colorAccent));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLabels(true); //标签文字
        xAxis.setGranularity(1f); // 间隔尺寸1
        //设置Y轴
        barChart.getAxisRight().setEnabled(false);
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setZeroLineColor(getResources().getColor(R.color.colorAccent));
        leftAxis.setAxisLineColor(getResources().getColor(R.color.colorAccent));
        leftAxis.setDrawLabels(false); //标签文字
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        leftAxis.setAxisMinimum(0f);//不设置会有两条Y线情况
        //获取图例对象
        Legend legend = barChart.getLegend();
        legend.setEnabled(false); //不显示
        //手机屏幕上显示showNum剩下的滑动直方图然后显示
        float ratio = (float) num / (float) showNum;
        //显示的时候是按照多大的比率缩放显示，1f表示不放大缩小
        barChart.zoom(ratio, 1f, 0, 0);
        //设置是否可以缩放
        barChart.setScaleEnabled(false);
        //设置是否可以触摸
        barChart.setTouchEnabled(true);
        //设置是否可以拖拽
        barChart.setDragEnabled(true);
        barChart.setFitBars(true); // 让X轴与所有的数据条适配
    }


    List<PieChartBean> pieChartBeanList = new ArrayList<>();

    private void initPieChartView() {
        pieChartBeanList.clear();
        pieChartBeanList.add(new PieChartBean("微信  ￥0", 50f, R.color.wechatColor));
        pieChartBeanList.add(new PieChartBean("支付宝  ￥0", 50f, R.color.alipayColor));
        pieChartView.setDate(pieChartBeanList);
    }

    @Override
    protected DayBillReportPresenter createPresenter() {
        return new DayBillReportPresenter(this, getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    private int hour = 0;
    private float maxVal = 0;
    private float mimVal = 0;

    private void setTrendBarChartData(List<DayBillReportBean.TrendSumBean> trendBeans) {
        if (trendBeans != null) {
            ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
            mimVal = 0;
            maxVal = 0;
            hour = 0;
            for (int i = 0; i < trendBeans.size(); i++) {
                float val = trendBeans.get(i).getPay_amount_sum();
                if (val > maxVal) {
                    hour = i;
                    maxVal = val;
                }
                if(val < mimVal){
                    mimVal = val;
                }
                yVals1.add(new BarEntry(i, val));
            }
            XAxis xAxis = trendBarChart.getXAxis();
            xAxisValue.clear();
            for (int i = 0; i < yVals1.size(); i++) {
                xAxisValue.add(i + "点");
            }
            YAxis leftAxis = trendBarChart.getAxisLeft();
            if(mimVal == 0){
                leftAxis.setStartAtZero(true);
            }else {
                leftAxis.setStartAtZero(false);
            }
            xAxis.setLabelCount(xAxisValue.size());
            xAxis.setTextSize(10f); // 文本大小14
            xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValue));
            highAmountDetail.setText(hour + "时左右 " + floatToString(maxVal) + "元");
            MyBarDataSet set1 = new MyBarDataSet(yVals1, null, maxVal);
            set1.setDrawIcons(false);
            set1.setColors(new int[]{getResources().getColor(R.color.dark_orange_color), getResources().getColor(R.color.light_orange_color)});
            set1.setHighlightEnabled(false);
            BarData data = new BarData(set1);
            data.setValueTextSize(10f);
            data.setValueFormatter(new DecimalFormatter());
            data.setBarWidth(0.3f);
            trendBarChart.setData(data);
            trendBarChart.invalidate(); // 刷新
        }
    }

    private void setCompareBarChartData() {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        yVals1.add(new BarEntry(0, compareAmount));
        yVals1.add(new BarEntry(1, totalAmount));
        XAxis xAxis = compareBarChart.getXAxis();
        xCompareAxisValue.clear();
        xCompareAxisValue.add(yesterdayDate);
        xCompareAxisValue.add(todayDate);
        xAxis.setLabelCount(xCompareAxisValue.size());
        xAxis.setTextSize(10f); // 文本大小14
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xCompareAxisValue));
        MyBarDataSet set1 = new MyBarDataSet(yVals1, null, 0);
        set1.setDrawIcons(false);
        set1.setColors(new int[]{getResources().getColor(R.color.dark_orange_color), getResources().getColor(R.color.light_orange_color)});
        set1.setHighlightEnabled(false);
        BarData data = new BarData(set1);
        data.setValueTextSize(10f);
        data.setValueFormatter(new DecimalFormatter());
        data.setBarWidth(0.3f);
        compareBarChart.setData(data);
        compareBarChart.invalidate(); // 刷新
    }

    private void setPieChartData(List<DayBillReportBean.PaytypeBean> paytypeBeans) {
        if (paytypeBeans != null) {
            pieChartBeanList.clear();
            float total = 0;
            for(int i = 0; i < paytypeBeans.size(); i++){
                total = total + paytypeBeans.get(i).getPay_amount_sum();
            }
            for (int i = 0; i < paytypeBeans.size(); i++) {
                String detail = paytypeBeans.get(i).getPaytype_name() + "￥" + floatToString(paytypeBeans.get(i).getPay_amount_sum());
                if(paytypeBeans.get(i).getPay_amount_sum() == 0){
                    continue;
                }
                float angle = (paytypeBeans.get(i).getPay_amount_sum() / total) * 100f;
                if (paytypeBeans.get(i).getPaytype_name().contains("微信")) {
                    pieChartBeanList.add(new PieChartBean(detail, angle, R.color.wechatColor));
                } else {
                    pieChartBeanList.add(new PieChartBean(detail, angle, R.color.alipayColor));
                }
            }
            pieChartView.setDate(pieChartBeanList);
        }
    }

    private float totalAmount = 0; //当前日期交易总和
    private float compareAmount = 0; //对比日期交易总和
    private String todayDate; //今日时间 月和日
    private String yesterdayDate; //昨日时间 月和日
    private String searchDay;

    @Override
    public void showDayBillReport(DayBillReportBean reportBean) {
        refreshLayout.finishRefresh();  //刷新完成
        searchDay = reportBean.getSearch_day();
        billDate.setText(searchDay.substring(0, 4) + "年" + searchDay.substring(4, 6) + "月" + searchDay.substring(6) + "日");
        billNumber.setText(reportBean.getSum().getPay_count() + "");
        if (reportBean.getSum().getPay_count() == 0) {
            reportChartLayout.setVisibility(View.GONE);
            emptyLayout.setVisibility(View.VISIBLE);
        } else {
            emptyLayout.setVisibility(View.GONE);
            reportChartLayout.setVisibility(View.VISIBLE);
        }
        billTotal.setText(floatToString(reportBean.getSum().getPay_amount_sum()));

        refundNumber.setText(reportBean.getRefund_sum().getRefundCount() + "");
        refundTotal.setText(floatToString(reportBean.getRefund_sum().getRefundAmountSum()));
        totalAmount = reportBean.getSum().getPay_amount_sum() + Math.abs(reportBean.getRefund_sum().getRefundAmountSum());
        totalAmountSum.setText(floatToString(totalAmount));
        setTrendBarChartData(reportBean.getTrend_sum());
        todayDate = searchDay.substring(4, 6) + "月" + searchDay.substring(6) + "日";
        highAmountTime.setText(todayDate + "收入最高的时间是");
        String compareDay = reportBean.getCompared_sum().getDay();
        yesterdayDate = compareDay.substring(4, 6) + "月" + compareDay.substring(6) + "日";
        compareAmount = reportBean.getCompared_sum().getPay_amount_sum();
        if (totalAmount >= compareAmount) {
            float differAmount = totalAmount - compareAmount;
            amountCompareDetail.setText(todayDate + "比" + yesterdayDate + "增加");
            compareArrow.setImageResource(R.mipmap.ic_increase);
            tvCompareAmount.setText( floatToString(differAmount) + "元");
            tvCompareAmount.setTextColor(Color.parseColor("#EF3F3E"));
        } else {
            float differAmount = totalAmount - compareAmount;
            amountCompareDetail.setText(todayDate + "比" + yesterdayDate + "减少");
            compareArrow.setImageResource(R.mipmap.ic_decrease);
            tvCompareAmount.setText( floatToString(Math.abs(differAmount)) + "元");
            tvCompareAmount.setTextColor(Color.parseColor("#35A04D"));
        }
        //对比报表
        setCompareBarChartData();
        setPieChartData(reportBean.getPaytype());
    }

    @OnClick(R.id.select_date_layout)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putInt("store_id", store_id);
        jumpActivityForResult(SelectDayReportActivity.class, bundle, SELECT_CANLENDA);
    }


    private String search_day = "";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_CANLENDA) {
            search_day = data.getStringExtra("date");
            mvpPresenter.getDayBillReport(store_id, search_day);
        }
    }

    public static String floatToString(float num) {
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BillRefreshEvent event) {
        store_id = event.getStoreId();
        mvpPresenter.getDayBillReport(store_id, search_day);
    }
}
