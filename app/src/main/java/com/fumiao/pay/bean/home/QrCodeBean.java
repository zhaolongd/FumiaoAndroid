package com.fumiao.pay.bean.home;

import java.util.List;

/**
 * Created by chee on 2018/9/15.
 */
public class QrCodeBean {


    /**
     * support_paytypes : []
     * counter_no : 2018091516101337361
     * qrcode_url : https://onecodepay.zhiyu.cc/app/cashier/cashierQrcode?counter=2018091516101337361&money=0
     * pay_info: a7wQoz12wLfL5gshjBKs8MbdUVYLHj8Ja4%2Fa8sej0K53%2FG%2FTnk8Z1HfqQUDGs1x%2B
     */

    private String counter_no;
    private String qrcode_url;
    private List<?> support_paytypes;
    private String pay_info;
    private int pay_switch;

    public String getCounter_no() {
        return counter_no;
    }

    public void setCounter_no(String counter_no) {
        this.counter_no = counter_no;
    }

    public String getQrcode_url() {
        return qrcode_url;
    }

    public void setQrcode_url(String qrcode_url) {
        this.qrcode_url = qrcode_url;
    }

    public List<?> getSupport_paytypes() {
        return support_paytypes;
    }

    public void setSupport_paytypes(List<?> support_paytypes) {
        this.support_paytypes = support_paytypes;
    }

    public String getPay_info() {
        return pay_info;
    }

    public void setPay_info(String pay_info) {
        this.pay_info = pay_info;
    }

    public int getPay_switch() {
        return pay_switch;
    }

    public void setPay_switch(int pay_switch) {
        this.pay_switch = pay_switch;
    }

}
