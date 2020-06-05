package com.fumiao.pay.bean.main;

import java.io.Serializable;

public class ScanBean implements Serializable {


    /**
     * status : 1
     * order_id : 20190320113657751552
     * msg : 支付提示
     * query_url : https://qr.addiao.cn/pay/app/pay/controller/Aliscan/scanQuery.html
     */

    private int status;
    private String order_id;
    private String msg;
    private String query_url;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getQuery_url() {
        return query_url;
    }

    public void setQuery_url(String query_url) {
        this.query_url = query_url;
    }
}
