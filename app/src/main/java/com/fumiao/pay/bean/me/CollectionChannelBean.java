package com.fumiao.pay.bean.me;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class CollectionChannelBean {
    private int status;
    private List<String> paytypes;
    private String rate;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getPaytypes() {
        return paytypes;
    }

    public void setPaytypes(List<String> paytypes) {
        this.paytypes = paytypes;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public static class ChannelsBean extends CoreBean {
        String paytype;
        String rate;

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
}
