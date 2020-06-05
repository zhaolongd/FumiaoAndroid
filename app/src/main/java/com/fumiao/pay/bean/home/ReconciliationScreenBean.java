package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class ReconciliationScreenBean {

    private Store store;
    private PayType paytype;
    private PayWay payway;
    private PayStatus pay_status;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public PayType getPayType() {
        return paytype;
    }

    public void setPayType(PayType paytype) {
        this.paytype = paytype;
    }

    public PayWay getPayWay() {
        return payway;
    }

    public void setPayWay(PayWay payway) {
        this.payway = payway;
    }

    public PayStatus getPayStatus() {
        return pay_status;
    }

    public void setPayStatus(PayStatus pay_status) {
        this.pay_status = pay_status;
    }

    public static class Store {
        private String name;
        private List<StoreBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<StoreBean> getList() {
            return list;
        }

        public void setList(List<StoreBean> list) {
            this.list = list;
        }
    }

    public static class PayType {
        private String name;
        private List<PayTypeBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<PayTypeBean> getList() {
            return list;
        }

        public void setList(List<PayTypeBean> list) {
            this.list = list;
        }
    }

    public static class PayWay {
        private String name;
        private List<PayWayBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<PayWayBean> getList() {
            return list;
        }

        public void setList(List<PayWayBean> list) {
            this.list = list;
        }
    }

    public static class PayStatus{
        private String name;
        private List<PayStatusBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<PayStatusBean> getList() {
            return list;
        }

        public void setList(List<PayStatusBean> list) {
            this.list = list;
        }
    }

    public static class StoreBean extends CoreBean {
        /**
         * id : 1
         * name : 店铺名称
         */
        private boolean isSelect;
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return getName();
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }

    public static class PayTypeBean extends CoreBean {
        /**
         * id : 4
         * name : 支付宝H5
         * icon : /static/common/paytype/ali_ico.png
         */
        private boolean isSelect;
        private int id;
        private String name;
        private String icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }

    public static class PayWayBean extends CoreBean {
        /**
         * id : 1
         * name : 线下收款
         */
        private boolean isSelect;
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }

    public static class PayStatusBean extends CoreBean{
        private boolean isSelect;
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }
}
