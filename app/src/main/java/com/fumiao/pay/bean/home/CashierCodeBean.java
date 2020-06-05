package com.fumiao.pay.bean.home;
import com.fumiao.core.adapter.CoreBean;

public class CashierCodeBean extends CoreBean{
    private int is_activation; // 1：未激活 2：已激活
    private String code_url;

    public int getIs_activation() {
        return is_activation;
    }

    public void setIs_activation(int is_activation) {
        this.is_activation = is_activation;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }
}
