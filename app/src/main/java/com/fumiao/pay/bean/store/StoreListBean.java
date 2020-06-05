package com.fumiao.pay.bean.store;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class StoreListBean {
    private List<StoresBean> stores;

    public List<StoresBean> getStores() {
        return stores;
    }

    public void setStores(List<StoresBean> stores) {
        this.stores = stores;
    }

    public static class StoresBean extends CoreBean {
        /**
         * id : 8
         * manager_id : 23
         * name : 总店
         * image : /uploads/ea/fc64d39b50b118e56bd4882435e8d0a26b56c9.png
         * cate_id : 8，7，4
         * cate_name : 烤鱼火锅，烧烤自助，小吃快餐
         * description : 愤怒
         * province : 110000
         * province_name : 北京市
         * city : 110100
         * city_name : 市辖区
         * area : 110101
         * area_name : 东城区
         * address : 上海
         * status : 1
         * update_time : 1536647473
         * create_time : 0
         */

        private boolean clickable;
        private int id;
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
        private int is_default; //默认门店 1：默认

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

        public boolean isClickable() {
            return clickable;
        }

        public void setClickable(boolean clickable) {
            this.clickable = clickable;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        @Override
        public String toString() {
            return "StoresBean{" +
                    "clickable=" + clickable +
                    ", id=" + id +
                    ", manager_id=" + manager_id +
                    ", name='" + name + '\'' +
                    ", image='" + image + '\'' +
                    ", cate_id='" + cate_id + '\'' +
                    ", cate_name='" + cate_name + '\'' +
                    ", description='" + description + '\'' +
                    ", province=" + province +
                    ", province_name='" + province_name + '\'' +
                    ", city=" + city +
                    ", city_name='" + city_name + '\'' +
                    ", area=" + area +
                    ", area_name='" + area_name + '\'' +
                    ", address='" + address + '\'' +
                    ", status=" + status +
                    ", update_time=" + update_time +
                    ", create_time=" + create_time +
                    '}';
        }

    }
}
