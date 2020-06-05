package com.fumiao.pay.ui.activity.store;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.fumiao.core.adapter.CoreBean;
import com.fumiao.core.adapter.CoreRecycleAdapter;
import com.fumiao.core.adapter.CoreViewHolder;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.uitls.Utils;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.store.MemberListBean;
import com.fumiao.pay.mvp.store.MemberManagePresenter;
import com.fumiao.pay.mvp.store.MemberManageView;
import com.fumiao.pay.ui.dialog.MsgDialog;
import com.fumiao.pay.widget.SpacesItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberManageActivity extends MvpActivity<MemberManagePresenter> implements MemberManageView {

    @BindView(R.id.member_recy)
    RecyclerView memberRecy;
    @BindView(R.id.member_refresh_Layout)
    SmartRefreshLayout memberRefreshLayout;
    ArrayList<MemberListBean.MembersBean.DataBean> data;
    MemberListAdapter memberAdapter;

    MsgDialog msgDialog;
    int store_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_manage, false);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        store_id = SPUtil.getInstance().getInt(STORE_ID);
        msgDialog = new MsgDialog(this);
        setTitle("管理店员");
        setRight("添加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpActivity(MemberAddActivity.class);
            }
        });

        data = new ArrayList<>();
        memberAdapter = new MemberListAdapter(this, R.layout.item_member_list, data);
        memberRecy.setAdapter(memberAdapter);
        memberRecy.setLayoutManager(new LinearLayoutManager(this));
        memberRecy.addItemDecoration(new SpacesItemDecoration(Utils.dip2px(this, 15), Utils.dip2px(this, 15), 0 ,Utils.dip2px(this, 10)));
        memberAdapter.setOnItemClickListner(new CoreRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("member_id", data.get(position).getId());
                jumpActivity(MemberDetailActivity.class, bundle);
            }
        });
        memberRefreshLayout.setEnableLoadMore(false);
        memberRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mvpPresenter.getMemberList(store_id);
                memberRefreshLayout.finishRefresh();  //刷新完成
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        memberRefreshLayout.autoRefresh();//自动刷新
    }

    @Override
    protected MemberManagePresenter createPresenter() {
        return new MemberManagePresenter(this, this);
    }

    @Override
    public void showMemberList(MemberListBean memberListBean) {
        if(memberListBean != null){
            data.clear();
            if(memberListBean.getMembers().getData() != null){
                data.addAll(memberListBean.getMembers().getData());
            }
            if (data.size() == 0){
                showEmpty(ContextCompat.getDrawable(this, R.mipmap.store_list_null), "暂无店员哦");
            }else {
                hideEmpty();
            }
            memberAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void delMemberSuccess() {
        ToastUitl.show("删除成功");
        memberRefreshLayout.autoRefresh();//自动刷新
    }

    class MemberListAdapter extends CoreRecycleAdapter {
        public MemberListAdapter(Context context, int layoutId, List<? extends CoreBean> data) {
            super(context, layoutId, data);
        }

        @Override
        protected <T extends CoreBean> void convert(CoreViewHolder holder, T bean, int position) {
            super.convert(holder, bean, position);
            final MemberListBean.MembersBean.DataBean dataBean = (MemberListBean.MembersBean.DataBean) bean;
            holder.setText(R.id.tv_name, dataBean.getRealname());
            holder.setText(R.id.tv_member_position, dataBean.getPosition());
            holder.setText(R.id.tv_store, dataBean.getStore_name());
            //删除店员
            holder.setOnClickListener(R.id.btn_delete, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    msgDialog.show("是否删除该店员", "取消", "删除", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            msgDialog.dismiss();
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mvpPresenter.delMember(dataBean.getId());
                            msgDialog.dismiss();
                        }
                    });
                }
            });

            //编辑店员点击
            holder.setOnClickListener(R.id.edit_member_layout, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("member_id", dataBean.getId());
                    bundle.putBoolean("edit_member", true);
                    jumpActivity(MemberDetailActivity.class, bundle);
                }
            });
        }
    }

}
