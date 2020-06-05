package com.fumiao.pay.mvp.store;

import com.fumiao.pay.bean.store.MemberListBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface MemberManageView extends BaseView{
    void showMemberList(MemberListBean data);
    void delMemberSuccess();
}
