package com.fumiao.pay.event;

public class VoiceEvent {
    private String mMsg;
    public VoiceEvent(String msg) {
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
