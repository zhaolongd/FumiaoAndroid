package com.fumiao.pay.bean.store;

import java.util.List;

/**
 * Created by chee on 2018/9/11.
 */
public class StoreDetailsBean {


    /**
     * store : {"id":9,"manager_id":23,"name":"知足","image":"/uploads/31/40b3ebc18de8513034be7c678bb94e0c602dce.png","cate_id":"9，11，6","cate_name":"排档海鲜，江浙沪菜，咖啡西餐","description":"侧卧","province":110000,"province_name":"北京市","city":110100,"city_name":"市辖区","area":110101,"area_name":"东城区","address":"我们","status":1,"update_time":1536650021,"create_time":0,"cate_tags":["排档海鲜"]}
     */

    private StoreBean store;

    private String using_cashier_channel;
    private int payment_status;
    private String reason;
    private String today_pay_amout;
    private String today_pay_count;

    public String getUsing_cashier_channel() {
        return using_cashier_channel;
    }

    public void setUsing_cashier_channel(String using_cashier_channel) {
        this.using_cashier_channel = using_cashier_channel;
    }

    public StoreBean getStore() {
        return store;
    }

    public void setStore(StoreBean store) {
        this.store = store;
    }

    public int getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(int payment_status) {
        this.payment_status = payment_status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getToday_pay_amout() {
        return today_pay_amout;
    }

    public void setToday_pay_amout(String today_pay_amout) {
        this.today_pay_amout = today_pay_amout;
    }

    public String getToday_pay_count() {
        return today_pay_count;
    }

    public void setToday_pay_count(String today_pay_count) {
        this.today_pay_count = today_pay_count;
    }

    public static class StoreBean {
        /**
         *"id": 290,
         *"serial_number": "1120191202766722",
         *"merchant_id": "366079158128290",
         *"manager_id": 351,
         *"pid": 0,
         *"name": "索辣西",
         *"image": "",
         *"cate_id": "48",
         *"cate_name": "",
         *"description": "暂无介绍",
         *"province": "140000000",
         *"province_name": "山西省",
         *"city": "140100000",
         *"city_name": "太原市",
         *"area": "140105000",
         *"area_name": "小店区",
         *"address": "哦红米",
         *"status": 1,
         *"update_time": 0,
         *"create_time": 1575281615,
         *"is_default": 1,
         *"pca": "山西省\/太原市\/小店区",
         *"cate_tags": ["其他服务"]
         */

        private int id;
        private int manager_id;
        private String name;
        private String image;
        private String cate_id;
        private String cate_name;
        private String description;
        private String province;
        private String province_name;
        private String city;
        private String city_name;
        private String area;
        private String area_name;
        private String address;
        private int status;
        private int update_time;
        private int create_time;
        private String pca;
        private List<String> cate_tags;


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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
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

        public String getPca() {
            return pca;
        }

        public void setPca(String pca) {
            this.pca = pca;
        }

        public List<String> getCate_tags() {
            return cate_tags;
        }

        public void setCate_tags(List<String> cate_tags) {
            this.cate_tags = cate_tags;
        }
    }
}
