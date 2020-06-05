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
import com.fumiao.pay.bean.home.DayCalendarBean;
import com.fumiao.pay.mvp.home.SelectDayReportPresenter;
import com.fumiao.pay.mvp.home.SelectDayReportView;
import com.fumiao.pay.ui.adapter.DayCalendarAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectDayReportActivity extends MvpActivity<SelectDayReportPresenter> implements SelectDayReportView {


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
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    private int store_id;

    private ArrayList<DayCalendarBean.DaysBean> firstMonthBean = new ArrayList<>();
    private DayCalendarAdapter firstAdapter;

    private ArrayList<DayCalendarBean.DaysBean> secondMonthBean = new ArrayList<>();
    private DayCalendarAdapter secondAdapter;

    private ArrayList<DayCalendarBean.DaysBean> thirdMonthBean = new ArrayList<>();
    private DayCalendarAdapter thirdAdapter;

    private ArrayList<DayCalendarBean.DaysBean> fourthMonthBean = new ArrayList<>();
    private DayCalendarAdapter fourthAdapter;

    private ArrayList<DayCalendarBean.DaysBean> fifthMonthBean = new ArrayList<>();
    private DayCalendarAdapter fifthAdapter;

    private ArrayList<DayCalendarBean.DaysBean> sixMonthBean = new ArrayList<>();
    private DayCalendarAdapter sixthAdapter;

    private ArrayList<DayCalendarBean.DaysBean> seventhMonthBean = new ArrayList<>();

    private ArrayList<DayCalendarBean.DaysBean> tempData = new ArrayList<>();
    private DayCalendarAdapter seventhAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_day_calender, false);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setTitle("时间选择");
        firstAdapter = new DayCalendarAdapter(this, R.layout.item_report_calender, firstMonthBean);
        firstMonth.setLayoutManager(new GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false));
        firstMonth.setAdapter(firstAdapter);

        secondAdapter = new DayCalendarAdapter(this, R.layout.item_report_calender, secondMonthBean);
        secondMonth.setLayoutManager(new GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false));
        secondMonth.setAdapter(secondAdapter);

        thirdAdapter = new DayCalendarAdapter(this, R.layout.item_report_calender, thirdMonthBean);
        thirdMonth.setLayoutManager(new GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false));
        thirdMonth.setAdapter(thirdAdapter);

        fourthAdapter = new DayCalendarAdapter(this, R.layout.item_report_calender, fourthMonthBean);
        fourthMonth.setLayoutManager(new GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false));
        fourthMonth.setAdapter(fourthAdapter);

        fifthAdapter = new DayCalendarAdapter(this, R.layout.item_report_calender, fifthMonthBean);
        fifthMonth.setLayoutManager(new GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false));
        fifthMonth.setAdapter(fifthAdapter);

        sixthAdapter = new DayCalendarAdapter(this, R.layout.item_report_calender, sixMonthBean);
        sixthMonth.setLayoutManager(new GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false));
        sixthMonth.setAdapter(sixthAdapter);

        seventhAdapter = new DayCalendarAdapter(this, R.layout.item_report_calender, seventhMonthBean);
        seventhMonth.setLayoutManager(new GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false));
        seventhMonth.setAdapter(seventhAdapter);

        firstAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = firstMonthBean.get(position).getDate();
                if (date != null) {
                    Intent intent = new Intent();
                    intent.putExtra("date", date);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        secondAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = secondMonthBean.get(position).getDate();
                if (date != null) {
                    Intent intent = new Intent();
                    intent.putExtra("date", date);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        thirdAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = thirdMonthBean.get(position).getDate();
                if (date != null) {
                    Intent intent = new Intent();
                    intent.putExtra("date", date);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        fourthAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = fourthMonthBean.get(position).getDate();
                if (date != null) {
                    Intent intent = new Intent();
                    intent.putExtra("date", date);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        fifthAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = fifthMonthBean.get(position).getDate();
                if (date != null) {
                    Intent intent = new Intent();
                    intent.putExtra("date", date);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        sixthAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = sixMonthBean.get(position).getDate();
                if (date != null) {
                    Intent intent = new Intent();
                    intent.putExtra("date", date);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        seventhAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = seventhMonthBean.get(position).getDate();
                if (date != null) {
                    Intent intent = new Intent();
                    intent.putExtra("date", date);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        store_id = getIntent().getIntExtra("store_id", 0);
        mvpPresenter.getDayReportCalendar(store_id);
    }

    @Override
    protected SelectDayReportPresenter createPresenter() {
        return new SelectDayReportPresenter(this, this);
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

    private void addDayData(List<DayCalendarBean.DaysBean> originData, ArrayList<DayCalendarBean.DaysBean> data) {
        tempData.clear();
        tempData.addAll(originData);
        if (tempData != null && tempData.size() > 0) {
            String date = tempData.get(0).getDate();
            String year = date.substring(0, 4);
            String month = date.substring(4, 6);
            String day = date.substring(6);
            int num = CalculateWeekDay(year, month, day);
            for (int k = 0; k < num; k++) {
                data.add(new DayCalendarBean.DaysBean());
            }
            data.addAll(tempData);
        }
    }


    @Override
    public void showDayCalendar(DayCalendarBean dayCalendarBean) {
        int count = 0;
        for (int i = 0; i < dayCalendarBean.getCalendar().size(); i++) {
            for (int j = 0; j < dayCalendarBean.getCalendar().get(i).getMonths().size(); j++) {
                if (i == j) {
                    isShowYear = true;
                } else {
                    isShowYear = false;
                }
                if (count == 0) {
                    addTitle(firtTitle, dayCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), dayCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    addDayData(dayCalendarBean.getCalendar().get(i).getMonths().get(j).getDays(), firstMonthBean);
                } else if (count == 1) {
                    addTitle(secondTitle, dayCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), dayCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    addDayData(dayCalendarBean.getCalendar().get(i).getMonths().get(j).getDays(), secondMonthBean);
                } else if (count == 2) {
                    addTitle(thirdTitle, dayCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), dayCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    addDayData(dayCalendarBean.getCalendar().get(i).getMonths().get(j).getDays(), thirdMonthBean);
                } else if (count == 3) {
                    addTitle(fourthTitle, dayCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), dayCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    addDayData(dayCalendarBean.getCalendar().get(i).getMonths().get(j).getDays(), fourthMonthBean);
                } else if (count == 4) {
                    addTitle(fifthTitle, dayCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), dayCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    addDayData(dayCalendarBean.getCalendar().get(i).getMonths().get(j).getDays(), fifthMonthBean);
                } else if (count == 5) {
                    addTitle(sixthTitle, dayCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), dayCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    addDayData(dayCalendarBean.getCalendar().get(i).getMonths().get(j).getDays(), sixMonthBean);
                } else if (count == 6) {
                    addTitle(seventhTitle, dayCalendarBean.getCalendar().get(i).getMonths().get(j).getMonth(), dayCalendarBean.getCalendar().get(i).getYear(), isShowYear);
                    addDayData(dayCalendarBean.getCalendar().get(i).getMonths().get(j).getDays(), seventhMonthBean);
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

    public int CalculateWeekDay(String year, String month, String day) {
        int y = Integer.parseInt(year);
        int m = Integer.parseInt(month);
        int d = Integer.parseInt(day);
        if (m < 1 || m > 12) {
            System.out.println("你输入的月份不再范围内，请重新输入！");
        }
        if (m == 1 || m == 2) {
            m += 12;
            y--;
        }
        int iWeek = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
        return iWeek;
    }
}
