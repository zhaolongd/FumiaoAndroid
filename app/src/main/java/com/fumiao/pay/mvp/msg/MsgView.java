package com.fumiao.pay.mvp.msg;

import com.fumiao.pay.bean.me.HelpCenterBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface MsgView extends BaseView {
    void showInstationList(HelpCenterBean data);
    void loadArticleDataFail();
}
