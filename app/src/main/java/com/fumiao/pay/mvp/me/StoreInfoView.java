package com.fumiao.pay.mvp.me;

import com.fumiao.pay.bean.store.StoreDetailsBean;
import com.fumiao.pay.mvp.base.BaseView;

/**
 * Created by chee on 2018/9/18.
 */
public interface StoreInfoView extends BaseView {
    void showStoreDetails(StoreDetailsBean data);
}
