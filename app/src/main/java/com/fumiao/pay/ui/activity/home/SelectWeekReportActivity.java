package com.fumiao.pay.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.home.WeekCalendarBean;
import com.fumiao.pay.mvp.home.SelectWeekReportPresenter;
import com.fumiao.pay.mvp.home.SelectWeekReportView;
import com.fumiao.pay.ui.adapter.WeekCalendarAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectWeekReportActivity extends MvpActivity<SelectWeekReportPresenter> implements SelectWeekReportView {

    @BindView(R.id.first_month)
    RecyclerView firstMonth;
    @BindView(R.id.second_month)
    RecyclerView secondMonth;
    @BindView(R.id.third_month)
    RecyclerView thirdMonth;
    @BindView(R.id.fourth_month)
    RecyclerView fourthMonth;
    @BindView(R.id.fifth_month)
    RecyclerView fifthMonth;
    @BindView(R.id.sixth_month)
    RecyclerView sixthMonth;
    @BindView(R.id.seventh_month)
    RecyclerView seventhMonth;
    @BindView(R.id.firt_title)
    LinearLayout firtTitle;
    @BindView(R.id.second_title)
    LinearLayout secondTitle;
    @BindView(R.id.third_title)
    LinearLayout thirdTitle;
    @BindView(R.id.fourth_title)
    LinearLayout fourthTitle;
    @BindView(R.id.fifth_title)
    LinearLayout fifthTitle;
    @BindView(R.id.sixth_title)
    LinearLayout sixthTitle;
    @BindView(R.id.seventh_title)
    LinearLayout seventhTitle;
    @BindView(R.id.week_layout)
    LinearLayout weekLayout;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    private int store_id;

    private ArrayList<WeekCalendarBean.WeeksBean> firstMonthBean = new ArrayList<>();
    private WeekCalendarAdapter firstAdapter;

    private ArrayList<WeekCalendarBean.WeeksBean> secondMonthBean = new ArrayList<>();
    private WeekCalendarAdapter secondAdapter;

    private ArrayList<WeekCalendarBean.WeeksBean> thirdMonthBean = new ArrayList<>();
    private WeekCalendarAdapter thirdAdapter;

    private ArrayList<WeekCalendarBean.WeeksBean> fourthMonthBean = new ArrayList<>();
    private WeekCalendarAdapter fourthAdapter;

    private ArrayList<WeekCalendarBean.WeeksBean> fifthMonthBean = new ArrayList<>();
    private WeekCalendarAdapter fifthAdapter;

    private ArrayList<WeekCalendarBean.WeeksBean> sixMonthBean = new ArrayList<>();
    private WeekCalendarAdapter sixthAdapter;

    private ArrayList<WeekCalendarBean.WeeksBean> seventhMonthBean = new ArrayList<>();

    private ArrayList<WeekCalendarBean.WeeksBean> tempData = new ArrayList<>();
    private WeekCalendarAdapter seventhAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_day_calender, false);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setTitle("时间选择");
        weekLayout.setVisibility(View.GONE);
        firstAdapter = new WeekCalendarAdapter(this, R.layout.item_report_calender, firstMonthBean);
        firstMonth.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        firstMonth.setAdapter(firstAdapter);

        secondAdapter = new WeekCalendarAdapter(this, R.layout.item_report_calender, secondMonthBean);
        secondMonth.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        secondMonth.setAdapter(secondAdapter);

        thirdAdapter = new WeekCalendarAdapter(this, R.layout.item_report_calender, thirdMonthBean);
        thirdMonth.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        thirdMonth.setAdapter(thirdAdapter);

        fourthAdapter = new WeekCalendarAdapter(this, R.layout.item_report_calender, fourthMonthBean);
        fourthMonth.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        fourthMonth.setAdapter(fourthAdapter);

        fifthAdapter = new WeekCalendarAdapter(this, R.layout.item_report_calender, fifthMonthBean);
        fifthMonth.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        fifthMonth.setAdapter(fifthAdapter);

        sixthAdapter = new WeekCalendarAdapter(this, R.layout.item_report_calender, sixMonthBean);
        sixthMonth.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        sixthMonth.setAdapter(sixthAdapter);

        seventhAdapter = new WeekCalendarAdapter(this, R.layout.item_report_calender, seventhMonthBean);
        seventhMonth.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        seventhMonth.setAdapter(seventhAdapter);

        firstAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = firstMonthBean.get(position).getYear()+ firstMonthBean.get(position).getMonth();
                String week = firstMonthBean.get(position).getWeek_number();
                if (date != null && week != null) {
                    Intent intent = new Intent();
                    intent.putExtra("search_month", date);
                    intent.putExtra("search_week", week);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        secondAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = secondMonthBean.get(position).getYear()+ secondMonthBean.get(position).getMonth();
                String week = secondMonthBean.get(position).getWeek_number();
                if (date != null && week != null) {
                    Intent intent = new Intent();
                    intent.putExtra("search_month", date);
                    intent.putExtra("search_week", week);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        thirdAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = thirdMonthBean.get(position).getYear()+thirdMonthBean.get(position).getMonth();
                String week = thirdMonthBean.get(position).getWeek_number();
                if (date != null && week != null) {
                    Intent intent = new Intent();
                    intent.putExtra("search_month", date);
                    intent.putExtra("search_week", week);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        fourthAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = fourthMonthBean.get(position).getYear()+ fourthMonthBean.get(position).getMonth();
                String week = fourthMonthBean.get(position).getWeek_number();
                if (date != null && week != null) {
                    Intent intent = new Intent();
                    intent.putExtra("search_month", date);
                    intent.putExtra("search_week", week);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        fifthAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = fifthMonthBean.get(position).getYear()+ fifthMonthBean.get(position).getMonth();
                String week = fifthMonthBean.get(position).getWeek_number();
                if (date != null && week != null) {
                    Intent intent = new Intent();
                    intent.putExtra("search_month", date);
                    intent.putExtra("search_week", week);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        sixthAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = sixMonthBean.get(position).getYear()+ sixMonthBean.get(position).getMonth();
                String week = sixMonthBean.get(position).getWeek_number();
                if (date != null && week != null) {
                    Intent intent = new Intent();
                    intent.putExtra("search_month", date);
                    intent.putExtra("search_week", week);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        seventhAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = seventhMonthBean.get(position).getYear()+ seventhMonthBean.get(position).getMonth();
                String week = seventhMonthBean.get(position).getWeek_number();
                if (date != null && week != null) {
                    Intent intent = new Intent();
                    intent.putExtra("search_month", date);
                    intent.putExtra("search_week", week);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        store_id = getIntent().getIntExtra("store_id", 0);
        mvpPresenter.getWeekReportCalendar(store_id);
    }

    @Override
    protected SelectWeekReportPresenter createPresenter() {
        return new SelectWeekReportPresenter(this, this);
    }

    private void addTitle(LinearLayout layout, String month, String year, Boolean isShowYear) {
        final View view = LayoutInflater.from(this).inflate(R.layout.item_calendar_title, null);
        final TextView tvMonth = view.findViewById(R.id.month);
        final TextView tvYear = view.findViewById(R.id.year);
        final LinearLayout yearLayout = view.findViewById(R.id.year_layout);
        if (isShowYear) {
            yearLayout.setVisibility(View.VISIBLE);
        } else {
            yearLayout.setVisibility(View.GONE);
        }
        tvMonth.setText(month + "月");
        tvYear.setText(year);
        layout.addView(view);
    }

    boolean isShowYear = false;


    @Override
    public void showWeekCalendar(WeekCalendarBean weekCalendarBean) {
        int count = 0;
        for (int i = 0; i < weekCalendarBean.getCalendar().size(); i++) {
            for (int j = 0; j < weekCalendarBean.getCalendar().get(i).getMonths().size(); j++) {
                if (i == j) {
                    isShowYear = true;
                } else {
                    isShowYear = false;
                }
                if (count == 0) {
                    addTitle(firtTitle, weekCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), weekCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    firstMonthBean.addAll(weekCalendarBean.getCalendar().get(i).getMonths().get(j).getWeeks());
                } else if (count == 1) {
                    addTitle(secondTitle, weekCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), weekCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    secondMonthBean.addAll(weekCalendarBean.getCalendar().get(i).getMonths().get(j).getWeeks());

                } else if (count == 2) {
                    addTitle(thirdTitle, weekCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), weekCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    thirdMonthBean.addAll(weekCalendarBean.getCalendar().get(i).getMonths().get(j).getWeeks());
                } else if (count == 3) {
                    addTitle(fourthTitle, weekCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), weekCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    fourthMonthBean.addAll(weekCalendarBean.getCalendar().get(i).getMonths().get(j).getWeeks());
                } else if (count == 4) {
                    addTitle(fifthTitle, weekCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), weekCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    fifthMonthBean.addAll(weekCalendarBean.getCalendar().get(i).getMonths().get(j).getWeeks());
                } else if (count == 5) {
                    addTitle(sixthTitle, weekCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), weekCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    sixMonthBean.addAll(weekCalendarBean.getCalendar().get(i).getMonths().get(j).getWeeks());
                } else if (count == 6) {
                    addTitle(seventhTitle, weekCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), weekCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    seventhMonthBean.addAll(weekCalendarBean.getCalendar().get(i).getMonths().get(j).getWeeks());
                }
                count++;
            }
        }
        firstAdapter.notifyDataSetChanged();
        secondAdapter.notifyDataSetChanged();
        thirdAdapter.notifyDataSetChanged();
        fourthAdapter.notifyDataSetChanged();
        fifthAdapter.notifyDataSetChanged();
        sixthAdapter.notifyDataSetChanged();
        seventhAdapter.notifyDataSetChanged();
        handler.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

    }

    Handler handler = new Handler();
}
