package com.fumiao.pay.event;

/**
 * Created by zhaolong.
 * Description: 切换门店后报表数据需更新
 * Date: 2020/1/17 0017 14:20
 */
public class BillRefreshEvent {
    private int storeId = 0;
    public BillRefreshEvent(int id) {
        storeId = id;
    }
    public int getStoreId(){
        return storeId;
    }
}
