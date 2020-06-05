package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

/**
 * pay_amount 退款金额
 * pay_applydate 退款时间
 * pay_status 退款状态 3退款成功 31退款中 32退款失败
 */
public class RefundOrderBean extends CoreBean{
    private String pay_orderid;
    private String pay_amount;
    private int pay_applydate;
    private int pay_status;

    public String getPay_orderid() {
        return pay_orderid;
    }

    public void setPay_orderid(String pay_orderid) {
        this.pay_orderid = pay_orderid;
    }

    public String getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(String pay_amount) {
        this.pay_amount = pay_amount;
    }

    public int getPay_applydate() {
        return pay_applydate;
    }

    public void setPay_applydate(int pay_applydate) {
        this.pay_applydate = pay_applydate;
    }

    public int getPay_status() {
        return pay_status;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }
}
