package com.fumiao.pay.bean.me;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

public class CashierDeviceBean {
    List<DeviceBean> device_list;

    public List<DeviceBean> getDevice_list() {
        return device_list;
    }

    public void setDevice_list(List<DeviceBean> device_list) {
        this.device_list = device_list;
    }

    public static class DeviceBean extends CoreBean{
        /**
         * "id": 14,
         * "store_id": 248,
         * "device_type": 2,
         * "device_sn": "FM2002767472259",
         * "serial_number": "FM2002767472259",
         * "status": 1,
         * "add_time": 1571797848
         */
        private String id;
        private String store_id;
        private int device_type; //1二维码绑定盒子，2单独二维码，3单独盒子，4电子码
        private String device_sn;
        private String serial_number;
        private int status; //1已激活正常服务 2已关闭',
        private String add_time;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public int getDevice_type() {
            return device_type;
        }

        public void setDevice_type(int device_type) {
            this.device_type = device_type;
        }

        public String getDevice_sn() {
            return device_sn;
        }

        public void setDevice_sn(String device_sn) {
            this.device_sn = device_sn;
        }

        public String getSerial_number() {
            return serial_number;
        }

        public void setSerial_number(String serial_number) {
            this.serial_number = serial_number;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
