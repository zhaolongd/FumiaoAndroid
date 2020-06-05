package com.fumiao.pay.mvp.store;

import com.fumiao.pay.bean.store.MemberDetailsBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface MemberDetailView extends BaseView{
    void showMemberDetail(MemberDetailsBean data);

    void editeMemberSuccess();
}
