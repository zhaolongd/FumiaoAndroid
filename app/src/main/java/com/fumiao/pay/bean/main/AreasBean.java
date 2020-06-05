package com.fumiao.pay.bean.main;

import java.util.ArrayList;

public class AreasBean {
    private ArrayList<AeraBean> areas;
    private CurrentBean current;

    public ArrayList<AeraBean> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<AeraBean> areas) {
        this.areas = areas;
    }

    public CurrentBean getCurrent() {
        return current;
    }

    public void setCurrent(CurrentBean current) {
        this.current = current;
    }

    public static class CurrentBean {
        private String current_region_id; //当前省份ID
        private String current_city_id;  //当前城市ID
        private String current_area_id;  //当前区县ID
        private String current_region_name; //当前省份
        private String current_city_name; //当前市

        public String getCurrent_region_id() {
            return current_region_id;
        }

        public void setCurrent_region_id(String current_region_id) {
            this.current_region_id = current_region_id;
        }

        public String getCurrent_city_id() {
            return current_city_id;
        }

        public void setCurrent_city_id(String current_city_id) {
            this.current_city_id = current_city_id;
        }

        public String getCurrent_area_id() {
            return current_area_id;
        }

        public void setCurrent_area_id(String current_area_id) {
            this.current_area_id = current_area_id;
        }

        public String getCurrent_region_name() {
            return current_region_name;
        }

        public void setCurrent_region_name(String current_region_name) {
            this.current_region_name = current_region_name;
        }

        public String getCurrent_city_name() {
            return current_city_name;
        }

        public void setCurrent_city_name(String current_city_name) {
            this.current_city_name = current_city_name;
        }
    }
}
