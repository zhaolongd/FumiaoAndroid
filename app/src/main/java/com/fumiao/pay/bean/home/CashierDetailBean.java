package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class CashierDetailBean extends CoreBean{

    /**
     * qrcode : 需要先base64 解码，再需AES解密
     */
    private String qrcode; //二维码
    private int is_open_cashier_account; //是否开通收银渠道 -1:未开通,0:审核中,1:正常，2:审核失败
    private int is_store; //门店是否有效：0无效，1有效
    private int is_activation; //是否绑码：0未绑码，1已绑码
    private int is_url; //返回收款码状态:0失败，1成功
    private int pay_switch;

    private List<String> paytypes;
    private String rate;
    private String store_name;
    private String serial_number;
    private String address;


    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public int getPaySwitch() {
        return pay_switch;
    }

    public void setPaySwitch(int pay_switch) {
        this.pay_switch = pay_switch;
    }

    public int getIsOpenCashierAccount() {
        return is_open_cashier_account;
    }

    public void setIsOpenCashierAccount(int is_open_cashier_account) {
        this.is_open_cashier_account = is_open_cashier_account;
    }

    public int getIsStore() {
        return is_store;
    }

    public void setIsStore(int is_store) {
        this.is_store = is_store;
    }

    public int getIsActivation() {
        return is_activation;
    }

    public void setIsActivation(int is_activation) {
        this.is_activation = is_activation;
    }

    public int getIsUrl() {
        return is_url;
    }

    public void setIsUrl(int is_url) {
        this.is_url = is_url;
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

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
