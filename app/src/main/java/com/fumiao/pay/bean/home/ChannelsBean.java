package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

public class ChannelsBean extends CoreBean{
    private String paytype;
    private String rate;

    public ChannelsBean(String paytype, String rate) {
        this.paytype = paytype;
        this.rate = rate;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
