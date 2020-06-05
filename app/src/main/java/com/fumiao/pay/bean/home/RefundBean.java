package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

public class RefundBean  extends CoreBean {
    String status;
    String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
