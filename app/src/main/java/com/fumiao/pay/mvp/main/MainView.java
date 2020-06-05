package com.fumiao.pay.mvp.main;

import com.fumiao.pay.bean.store.StoreDetailsBean;
import com.fumiao.pay.mvp.base.BaseView;


public interface MainView extends BaseView {
    void showStoreDetails(StoreDetailsBean data);
}
