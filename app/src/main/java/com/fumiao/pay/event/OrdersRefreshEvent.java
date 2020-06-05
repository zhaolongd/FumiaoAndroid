package com.fumiao.pay.event;

public class OrdersRefreshEvent {
    private int mMsg;
    public OrdersRefreshEvent(int msg) {
        mMsg = msg;
    }
    public int getMsg(){
        return mMsg;
    }
}
