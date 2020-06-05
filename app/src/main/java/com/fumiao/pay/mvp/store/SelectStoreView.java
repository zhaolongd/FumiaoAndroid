package com.fumiao.pay.mvp.store;

import com.fumiao.pay.bean.store.StoreListBean;
import com.fumiao.pay.mvp.base.BaseView;

/**
 * Created by zhaolong.
 * Description:
 * Date: 2020/1/16 0016 11:01
 */
public interface SelectStoreView extends BaseView {
    void showStoreList(StoreListBean data);
}
