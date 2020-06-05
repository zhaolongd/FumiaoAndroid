package com.fumiao.pay.bean.store;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class MemberListBean {


    /**
     * members : {"total":1,"per_page":20,"current_page":"1","last_page":1,"data":[{"id":25,"type":2,"manager_id":22,"store_id":5,"phone":"","status":1,"pay_switch":1,"username":"147258369","realname":"zeng","head_image":"/uploads/11/9eec55c49dd6e8dee511048f0cd47e7c1efbf8.jpg","position":"zzzz","entry_time":995212800,"login_time":0,"password":"966747b1d835d79470385ea35cf6651a","encrypt_pwd":"AFEjkDvuPKag88gbnAja8g==","update_time":1536646265,"create_time":1536633441,"login_ip":0,"store_name":"test1","todayAmount":0}]}
     */

    private MembersBean members;

    public MembersBean getMembers() {
        return members;
    }

    public void setMembers(MembersBean members) {
        this.members = members;
    }

    public static class MembersBean {
        /**
         * total : 1
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"id":25,"type":2,"manager_id":22,"store_id":5,"phone":"","status":1,"pay_switch":1,"username":"147258369","realname":"zeng","head_image":"/uploads/11/9eec55c49dd6e8dee511048f0cd47e7c1efbf8.jpg","position":"zzzz","entry_time":995212800,"login_time":0,"password":"966747b1d835d79470385ea35cf6651a","encrypt_pwd":"AFEjkDvuPKag88gbnAja8g==","update_time":1536646265,"create_time":1536633441,"login_ip":0,"store_name":"test1","todayAmount":0}]
         */

        private int total;
        private int per_page;
        private String current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public String getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(String current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean extends CoreBean {
            /**
             * id : 25
             * type : 2
             * manager_id : 22
             * store_id : 5
             * phone :
             * status : 1
             * pay_switch : 1
             * username : 147258369
             * realname : zeng
             * head_image : /uploads/11/9eec55c49dd6e8dee511048f0cd47e7c1efbf8.jpg
             * position : zzzz
             * entry_time : 995212800
             * login_time : 0
             * password : 966747b1d835d79470385ea35cf6651a
             * encrypt_pwd : AFEjkDvuPKag88gbnAja8g==
             * update_time : 1536646265
             * create_time : 1536633441
             * login_ip : 0
             * store_name : test1
             * todayAmount : 0
             */

            private int id;
            private int type;
            private int manager_id;
            private int store_id;
            private String phone;
            private int status;
            private int pay_switch;
            private String username;
            private String realname;
            private String head_image;
            private String position;
            private int entry_time;
            private int login_time;
            private String password;
            private String encrypt_pwd;
            private int update_time;
            private int create_time;
            private String login_ip;
            private String store_name;
            private double todayAmount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getManager_id() {
                return manager_id;
            }

            public void setManager_id(int manager_id) {
                this.manager_id = manager_id;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getPay_switch() {
                return pay_switch;
            }

            public void setPay_switch(int pay_switch) {
                this.pay_switch = pay_switch;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getHead_image() {
                return head_image;
            }

            public void setHead_image(String head_image) {
                this.head_image = head_image;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public int getEntry_time() {
                return entry_time;
            }

            public void setEntry_time(int entry_time) {
                this.entry_time = entry_time;
            }

            public int getLogin_time() {
                return login_time;
            }

            public void setLogin_time(int login_time) {
                this.login_time = login_time;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getEncrypt_pwd() {
                return encrypt_pwd;
            }

            public void setEncrypt_pwd(String encrypt_pwd) {
                this.encrypt_pwd = encrypt_pwd;
            }

            public int getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(int update_time) {
                this.update_time = update_time;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public String getLogin_ip() {
                return login_ip;
            }

            public void setLogin_ip(String login_ip) {
                this.login_ip = login_ip;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public double getTodayAmount() {
                return todayAmount;
            }

            public void setTodayAmount(double todayAmount) {
                this.todayAmount = todayAmount;
            }
        }
    }
}
