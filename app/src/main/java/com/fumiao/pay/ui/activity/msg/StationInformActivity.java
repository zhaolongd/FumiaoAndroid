package com.fumiao.pay.ui.activity.msg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.me.HelpCenterBean;
import com.fumiao.pay.mvp.msg.MsgPresenter;
import com.fumiao.pay.mvp.msg.MsgView;
import com.fumiao.pay.ui.adapter.InstationMsgAdapter;
import com.fumiao.pay.widget.WebActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.fumiao.pay.widget.WebActivity.WEB_TITLE;
import static com.fumiao.pay.widget.WebActivity.WEB_URL;


/**
 * Author: XieBoss
 * Date: 2019/8/16 0016 17:37
 *
 * @Description:
 */
public class StationInformActivity extends MvpActivity<MsgPresenter> implements MsgView {

    InstationMsgAdapter instationMsgAdapter;
    ArrayList<HelpCenterBean.ArticlesBean.DataBean> articlesBeans = new ArrayList<>();
    @BindView(R.id.station_recycler)
    RecyclerView recyStation;

    int pagesize = 20, page = 1;
    @BindView(R.id.refresh_Layout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_inform, false);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected MsgPresenter createPresenter() {
        return new MsgPresenter(this, this);
    }

    public void init() {
        setTitle("站内通知");
        instationMsgAdapter = new InstationMsgAdapter(this, R.layout.item_instation, articlesBeans);
        recyStation.setAdapter(instationMsgAdapter);
        recyStation.setLayoutManager(new LinearLayoutManager(this));
        instationMsgAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                if (articlesBeans.size() != 0) {
                    Bundle bundle = new Bundle();
//                bundle.putInt("id", helpCenterBeans.get(position).getId());
                    bundle.putString(WEB_TITLE, articlesBeans.get(position).getTitle());
                    bundle.putString(WEB_URL, articlesBeans.get(position).getUrl());
                    jumpActivity(WebActivity.class, bundle);
                }
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                refreshLayout.setEnableLoadMore(true);
                mvpPresenter.getArticleList(pagesize, page);
//                refreshLayout.finishRefresh(true);  //刷新完成
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mvpPresenter.getArticleList(pagesize, page);
//                refreshLayout.finishLoadMore(true);//加载完成
            }
        });
        mvpPresenter.getArticleList(pagesize, page);

    }

    @Override
    public void showInstationList(HelpCenterBean data) {
        refreshLayout.finishRefresh(true);  //刷新完成
        if(data != null ){
            if (page == 1) {
                articlesBeans.clear();
            }
            if(data.getArticles().getData() != null){
                if (data.getArticles().getData().size() < pagesize) {
                    refreshLayout.setEnableLoadMore(false);
                }
                articlesBeans.addAll(data.getArticles().getData());
            }
            if (articlesBeans.size() == 0){
                showEmpty(ContextCompat.getDrawable(this, R.mipmap.station_inform_null), "暂无消息哦");
            }else {
                hideEmpty();
            }
            instationMsgAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void loadArticleDataFail() {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
