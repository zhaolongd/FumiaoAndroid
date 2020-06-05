package com.fumiao.pay.mvp.store;

import com.fumiao.pay.bean.store.StoreListBean;
import com.fumiao.pay.mvp.base.BaseView;

public interface StoreManageVeiw extends BaseView{
    void showStoreList(StoreListBean data);
    void setDefaultStoreSuccess(int storeId);
}
