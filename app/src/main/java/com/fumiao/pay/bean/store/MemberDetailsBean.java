package com.fumiao.pay.bean.store;

import java.io.Serializable;

public class MemberDetailsBean implements Serializable {


    /**
     * member : {"id":1,"type":2,"manager_id":4,"store_id":1,"phone":"","username":"member1","password":"4305fd9f0818cdfc019f36b7f7dd7b86","encrypt_pwd":"169S2qjG7PCaMr8/Tvu51Q==","status":1,"pay_switch":0,"realname":"realname","head_image":"0c\\71480df849c385861e50927fefc108af759e53.png","position":"position","entry_time":1534409161,"update_time":1535545339,"create_time":1,"login_time":1534320121,"login_ip":2130706433,"origin_pwd":"123456","todayAmount":0,"monthAmount":0.1}
     */

    private MemberBean member;

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public static class MemberBean  implements Serializable {
        /**
         * id : 1
         * type : 2
         * manager_id : 4
         * store_id : 1
         * phone :
         * username : member1
         * password : 4305fd9f0818cdfc019f36b7f7dd7b86
         * encrypt_pwd : 169S2qjG7PCaMr8/Tvu51Q==
         * status : 1
         * pay_switch : 0
         * realname : realname
         * head_image : 0c\71480df849c385861e50927fefc108af759e53.png
         * position : position
         * entry_time : 1534409161
         * update_time : 1535545339
         * create_time : 1
         * login_time : 1534320121
         * login_ip : 2130706433
         * origin_pwd : 123456
         * todayAmount : 0
         * monthAmount : 0.1
         */

        private int id;
        private int type;
        private int manager_id;
        private int store_id;
        private String phone;
        private String username;
        private String password;
        private String encrypt_pwd;
        private int status;
        private int pay_switch;
        private String realname;
        private String head_image;
        private String position;
        private long entry_time;
        private int update_time;
        private int create_time;
        private int login_time;
        private String login_ip;
        private String origin_pwd;
        private double todayAmount;
        private double monthAmount;

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
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

        public long getEntry_time() {
            return entry_time;
        }

        public void setEntry_time(long entry_time) {
            this.entry_time = entry_time;
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

        public int getLogin_time() {
            return login_time;
        }

        public void setLogin_time(int login_time) {
            this.login_time = login_time;
        }

        public String getLogin_ip() {
            return login_ip;
        }

        public void setLogin_ip(String login_ip) {
            this.login_ip = login_ip;
        }

        public String getOrigin_pwd() {
            return origin_pwd;
        }

        public void setOrigin_pwd(String origin_pwd) {
            this.origin_pwd = origin_pwd;
        }

        public double getTodayAmount() {
            return todayAmount;
        }

        public void setTodayAmount(double todayAmount) {
            this.todayAmount = todayAmount;
        }

        public double getMonthAmount() {
            return monthAmount;
        }

        public void setMonthAmount(double monthAmount) {
            this.monthAmount = monthAmount;
        }
    }

}
