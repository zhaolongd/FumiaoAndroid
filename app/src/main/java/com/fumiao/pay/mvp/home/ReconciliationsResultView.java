package com.fumiao.pay.mvp.home;

import com.fumiao.pay.bean.home.ReconciliationsResultBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface ReconciliationsResultView  extends BaseView{

    void showReconciliationsResultList(ReconciliationsResultBean data);
}
