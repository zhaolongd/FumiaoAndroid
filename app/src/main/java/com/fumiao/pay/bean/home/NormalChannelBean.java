package com.fumiao.pay.bean.home;

import java.util.List;

/**
 * Created by chee on 2018/9/18.
 */
public class NormalChannelBean {


    private List<AccountsBean> accounts;

    public List<AccountsBean> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountsBean> accounts) {
        this.accounts = accounts;
    }

    public static class AccountsBean {
        /**
         * id : 215
         * show_title : 牛支付
         * show_desc : 爱搜多空间23
         * rate : 0.0000
         * is_default : 1
         * status : 1
         */

        private int id;
        private String show_title;
        private String show_desc;
        private String rate;
        private int is_default;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShow_title() {
            return show_title;
        }

        public void setShow_title(String show_title) {
            this.show_title = show_title;
        }

        public String getShow_desc() {
            return show_desc;
        }

        public void setShow_desc(String show_desc) {
            this.show_desc = show_desc;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
