package com.fumiao.pay.event;

public class HomeTopEvent {
    private int mMsg;
    public HomeTopEvent(int msg) {
        mMsg = msg;
    }
    public int getMsg(){
        return mMsg;
    }
}
