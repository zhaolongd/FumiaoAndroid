package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;
import com.fumiao.pay.tools.IMarqueeItem;

import java.util.List;

public class PaymentRecordListBean {

    private List<PaymentRecordBean> arrived_list;

    public List<PaymentRecordBean> getArrived_list() {
        return arrived_list;
    }

    public void setArrived_list(List<PaymentRecordBean> arrived_list) {
        this.arrived_list = arrived_list;
    }

    public static class PaymentRecordBean extends CoreBean implements IMarqueeItem {

        private String pay_orderid;

        private String arrived_str;

        public String getPay_orderid() {
            return pay_orderid;
        }

        public void setPay_orderid(String pay_orderid) {
            this.pay_orderid = pay_orderid;
        }

        public String getArrived_str() {
            return arrived_str;
        }

        public void setArrived_str(String arrived_str) {
            this.arrived_str = arrived_str;
        }

        @Override
        public CharSequence marqueeMessage() {
            return arrived_str;
        }
    }
}
