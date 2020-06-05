package com.fumiao.pay.bean.me;


public class ChanneDetailsBean {


    /**
     * account : {"id":34,"business_license":"/uploads/f8/c45d9ed5d9ea98b4764d262108d294407430d8.jpg","id_card_front":"/uploads/f8/c45d9ed5d9ea98b4764d262108d294407430d8.jpg","id_card_back":"/uploads/f8/c45d9ed5d9ea98b4764d262108d294407430d8.jpg","bank_card_front":"/uploads/f8/c45d9ed5d9ea98b4764d262108d294407430d8.jpg","bank_card_back":"/uploads/f8/c45d9ed5d9ea98b4764d262108d294407430d8.jpg","store_img":"/uploads/f8/c45d9ed5d9ea98b4764d262108d294407430d8.jpg","status":1,"apply_time":1538210592,"create_time":1538206750}
     */

    private AccountBean account;

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

    public static class AccountBean {
        /**
         * id : 34
         * business_license : /uploads/f8/c45d9ed5d9ea98b4764d262108d294407430d8.jpg
         * id_card_front : /uploads/f8/c45d9ed5d9ea98b4764d262108d294407430d8.jpg
         * id_card_back : /uploads/f8/c45d9ed5d9ea98b4764d262108d294407430d8.jpg
         * bank_card_front : /uploads/f8/c45d9ed5d9ea98b4764d262108d294407430d8.jpg
         * bank_card_back : /uploads/f8/c45d9ed5d9ea98b4764d262108d294407430d8.jpg
         * store_img : /uploads/f8/c45d9ed5d9ea98b4764d262108d294407430d8.jpg
         * status : 1
         * apply_time : 1538210592
         * create_time : 1538206750
         */

        private int id;
        private String business_license;
        private String id_card_front;
        private String id_card_back;
        private String bank_card_front;
        private String bank_card_back;
        private String store_img;
        private String bank_bill;
        private int status;
        private int apply_time;
        private int create_time;
        private String reason;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBusiness_license() {
            return business_license;
        }

        public void setBusiness_license(String business_license) {
            this.business_license = business_license;
        }

        public String getId_card_front() {
            return id_card_front;
        }

        public void setId_card_front(String id_card_front) {
            this.id_card_front = id_card_front;
        }

        public String getId_card_back() {
            return id_card_back;
        }

        public void setId_card_back(String id_card_back) {
            this.id_card_back = id_card_back;
        }

        public String getBank_card_front() {
            return bank_card_front;
        }

        public void setBank_card_front(String bank_card_front) {
            this.bank_card_front = bank_card_front;
        }

        public String getBank_card_back() {
            return bank_card_back;
        }

        public void setBank_card_back(String bank_card_back) {
            this.bank_card_back = bank_card_back;
        }

        public String getStore_img() {
            return store_img;
        }

        public void setStore_img(String store_img) {
            this.store_img = store_img;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getApply_time() {
            return apply_time;
        }

        public void setApply_time(int apply_time) {
            this.apply_time = apply_time;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public String getBank_bill() {
            return bank_bill;
        }

        public void setBank_bill(String bank_bill) {
            this.bank_bill = bank_bill;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
