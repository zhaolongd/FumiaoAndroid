package com.fumiao.pay.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.home.MonthCalendarBean;
import com.fumiao.pay.mvp.home.SelectMonthReportPresenter;
import com.fumiao.pay.mvp.home.SelectMonthReportView;
import com.fumiao.pay.ui.adapter.MonthCalendarAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectMonthReportActivity extends MvpActivity<SelectMonthReportPresenter> implements SelectMonthReportView {

    @BindView(R.id.firt_title)
    LinearLayout firtTitle;
    @BindView(R.id.first_year)
    RecyclerView firstYear;
    @BindView(R.id.second_title)
    LinearLayout secondTitle;
    @BindView(R.id.second_year)
    RecyclerView secondYear;
    private int store_id;

    private ArrayList<MonthCalendarBean.MonthsBean> firstYearBean = new ArrayList<>();
    private MonthCalendarAdapter firstAdapter;

    private ArrayList<MonthCalendarBean.MonthsBean> secondYearBean = new ArrayList<>();
    private MonthCalendarAdapter secondAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_month_calendar, false);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        setTitle("时间选择");
        firstAdapter = new MonthCalendarAdapter(this, R.layout.item_report_calender, firstYearBean);
        firstYear.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        firstYear.setAdapter(firstAdapter);

        secondAdapter = new MonthCalendarAdapter(this, R.layout.item_report_calender, secondYearBean);
        secondYear.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        secondYear.setAdapter(secondAdapter);

        firstAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String date = firstYearBean.get(position).getYear_month();
                if(date != null){
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
                String date = secondYearBean.get(position).getYear_month();
                if(date != null){
                    Intent intent = new Intent();
                    intent.putExtra("date", date);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        store_id = getIntent().getIntExtra("store_id", 0);
        mvpPresenter.getMonthReportCalendar(store_id);
    }

    @Override
    protected SelectMonthReportPresenter createPresenter() {
        return new SelectMonthReportPresenter(this, this);
    }

    private void addTitle(LinearLayout layout , String year){
        final View view = LayoutInflater.from(this).inflate(R.layout.item_calendar_title, null);
        view.findViewById(R.id.month).setVisibility(View.GONE);
        final TextView tvYear  = view.findViewById(R.id.year);
        tvYear.setText(year);
        layout.addView(view);
    }

    @Override
    public void showMonthCalendar(MonthCalendarBean monthCalendarBean) {
        for (int i = 0; i < monthCalendarBean.getCalendar().size(); i++) {
           if(i == 0){
               addTitle(firtTitle, monthCalendarBean.getCalendar().get(i).getYear());
               firstYearBean.addAll(monthCalendarBean.getCalendar().get(i).getMonths());
               firstAdapter.notifyDataSetChanged();
           }else {
               addTitle(secondTitle, monthCalendarBean.getCalendar().get(i).getYear());
               secondYearBean.addAll(monthCalendarBean.getCalendar().get(i).getMonths());
               secondAdapter.notifyDataSetChanged();
           }
        }
    }

}
