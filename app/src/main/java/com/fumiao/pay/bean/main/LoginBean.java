package com.fumiao.pay.bean.main;


public class LoginBean {

    /**
     * info : {"id":4,"type":1,"manager_id":0,"store_id":0,"phone":"13088833049","username":"manager20181229135553601017","status":1,"pay_switch":1,"push_switch":1,"push_detail":1,"realname":"","head_image":"","position":"","entry_time":0,"update_time":1546062953,"create_time":1546062953,"login_time":1546565921,"login_ip":3658713793,"session_random":"ed43d2bd95f97d4d903ac457f349d0dc"}
     * store : {"id":4,"manager_id":4,"name":"门店201812294","image":"/uploads/53/9b59a9d7ec9757902e36e3e2aeb2943d656aec.jpg","cate_id":"78,80,82","cate_name":"酒吧,茶馆棋牌,会所桑拿","description":"是的，新店","province":210000,"province_name":"辽宁省","city":210100,"city_name":"沈阳市","area":210101,"area_name":"市辖区","address":"新店有优惠","status":1,"update_time":1546079748,"create_time":1546062953,"terminal_id":"","unionpay_token":"","unionpay_token_expire":0,"unionpay_password":""}
     */

    private InfoBean info;
    private StoreBean store = new StoreBean();

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public StoreBean getStore() {
        return store;
    }

    public void setStore(StoreBean store) {
        this.store = store;
    }

    public static class InfoBean {
        /**
         * id : 4
         * type : 1
         * manager_id : 0
         * store_id : 0
         * phone : 13088833049
         * username : manager20181229135553601017
         * status : 1
         * pay_switch : 1
         * push_switch : 1
         * push_detail : 1
         * realname :
         * head_image :
         * position :
         * entry_time : 0
         * update_time : 1546062953
         * create_time : 1546062953
         * login_time : 1546565921
         * login_ip : 3658713793
         * session_random : ed43d2bd95f97d4d903ac457f349d0dc
         */

        private int id;
        private int type; //1:店长 2：店员
        private int manager_id;
        private int store_id;
        private int is_boss; //1：老板账号 0：非老板账号
        private String phone;
        private String username;
        private int status;
        private int pay_switch;
        private int push_switch;
        private int push_detail;
        private int voice_remind_switch;
        private String realname;
        private String head_image;
        private String position;
        private int entry_time;
        private int update_time;
        private int create_time;
        private int login_time;
        private long login_ip;
        private String session_random;
        private String chk_key;

        public String getChk_key(){
            return chk_key;
        }

        public void setChk_key(String chk_key){
            this.chk_key = chk_key;
        }

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

        public int getIs_boss() {
            return is_boss;
        }

        public void setIs_boss(int is_boss) {
            this.is_boss = is_boss;
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

        public int getPush_switch() {
            return push_switch;
        }

        public void setPush_switch(int push_switch) {
            this.push_switch = push_switch;
        }

        public int getPush_detail() {
            return push_detail;
        }

        public void setPush_detail(int push_detail) {
            this.push_detail = push_detail;
        }

        public int getVoice_remind_switch() {
            return voice_remind_switch;
        }

        public void setVoice_remind_switch(int voice_remind_switch) {
            this.voice_remind_switch = voice_remind_switch;
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

        public long getLogin_ip() {
            return login_ip;
        }

        public void setLogin_ip(long login_ip) {
            this.login_ip = login_ip;
        }

        public String getSession_random() {
            return session_random;
        }

        public void setSession_random(String session_random) {
            this.session_random = session_random;
        }
    }

    public static class StoreBean {
        /**
         * id : 4
         * manager_id : 4
         * name : 门店201812294
         * image : /uploads/53/9b59a9d7ec9757902e36e3e2aeb2943d656aec.jpg
         * cate_id : 78,80,82
         * cate_name : 酒吧,茶馆棋牌,会所桑拿
         * description : 是的，新店
         * province : 210000
         * province_name : 辽宁省
         * city : 210100
         * city_name : 沈阳市
         * area : 210101
         * area_name : 市辖区
         * address : 新店有优惠
         * status : 1
         * update_time : 1546079748
         * create_time : 1546062953
         * terminal_id :
         * unionpay_token :
         * unionpay_token_expire : 0
         * unionpay_password :
         */

        private int id = 0;
        private int manager_id;
        private String name;
        private String image;
        private String cate_id;
        private String cate_name;
        private String description;
        private int province;
        private String province_name;
        private int city;
        private String city_name;
        private int area;
        private String area_name;
        private String address;
        private int status;
        private int update_time;
        private int create_time;
        private String terminal_id;
        private String unionpay_token;
        private int unionpay_token_expire;
        private String unionpay_password;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getManager_id() {
            return manager_id;
        }

        public void setManager_id(int manager_id) {
            this.manager_id = manager_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public String getTerminal_id() {
            return terminal_id;
        }

        public void setTerminal_id(String terminal_id) {
            this.terminal_id = terminal_id;
        }

        public String getUnionpay_token() {
            return unionpay_token;
        }

        public void setUnionpay_token(String unionpay_token) {
            this.unionpay_token = unionpay_token;
        }

        public int getUnionpay_token_expire() {
            return unionpay_token_expire;
        }

        public void setUnionpay_token_expire(int unionpay_token_expire) {
            this.unionpay_token_expire = unionpay_token_expire;
        }

        public String getUnionpay_password() {
            return unionpay_password;
        }

        public void setUnionpay_password(String unionpay_password) {
            this.unionpay_password = unionpay_password;
        }
    }
}
