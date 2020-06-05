package com.fumiao.pay.mvp.home;

import com.fumiao.pay.bean.home.ReconciliationsBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface ReconciliationsView extends BaseView{

    void showReconciliationsData(ReconciliationsBean bean);

}
