package com.fumiao.pay.mvp.store;

import com.fumiao.pay.bean.store.StoreDetailsBean;
import com.fumiao.pay.mvp.base.BaseView;


public interface StoreSettingView extends BaseView {
    void editSuccess();
    void showStoreDetails(StoreDetailsBean data);
}
