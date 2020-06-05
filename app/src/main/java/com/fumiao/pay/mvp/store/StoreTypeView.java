package com.fumiao.pay.mvp.store;


import com.fumiao.pay.bean.store.CateListBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface StoreTypeView extends BaseView {
    void showCateList(CateListBean data);
}
