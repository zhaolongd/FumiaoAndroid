package com.fumiao.pay.bean.me;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

/**
 * Created by chee on 2018/9/14.
 */
public class HelpCenterBean {


    /**
     * articles : {"total":1,"per_page":15,"current_page":"1","last_page":1,"data":[{"id":4,"show_create_time":1535644800,"title":"帮助文档","brief":"123123","is_top":0}]}
     */

    private ArticlesBean articles;

    public ArticlesBean getArticles() {
        return articles;
    }

    public void setArticles(ArticlesBean articles) {
        this.articles = articles;
    }

    public static class ArticlesBean {
        /**
         * total : 1
         * per_page : 15
         * current_page : 1
         * last_page : 1
         * data : [{"id":4,"show_create_time":1535644800,"title":"帮助文档","brief":"123123","is_top":0}]
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
             * id : 4
             * show_create_time : 1535644800
             * title : 帮助文档
             * brief : 123123
             * is_top : 0
             */

            private int id;
            private int show_create_time;
            private String title;
            private String brief;
            private int is_top;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getShow_create_time() {
                return show_create_time;
            }

            public void setShow_create_time(int show_create_time) {
                this.show_create_time = show_create_time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public int getIs_top() {
                return is_top;
            }

            public void setIs_top(int is_top) {
                this.is_top = is_top;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
