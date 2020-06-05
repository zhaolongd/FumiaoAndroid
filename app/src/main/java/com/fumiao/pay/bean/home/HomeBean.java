package com.fumiao.pay.bean.home;

import java.util.List;

public class HomeBean {
    List<HomeMenuBean> application;
    List<ArticleBean> article_list;
    List<BannerBean> banner_list;
    String bgi;

    public List<HomeMenuBean> getApplication() {
        return application;
    }

    public void setApplication(List<HomeMenuBean> application) {
        this.application = application;
    }

    public List<ArticleBean> getArticle_list() {
        return article_list;
    }

    public void setArticle_list(List<ArticleBean> article_list) {
        this.article_list = article_list;
    }

    public List<BannerBean> getBanner_list() {
        return banner_list;
    }

    public void setBanner_list(List<BannerBean> banner_list) {
        this.banner_list = banner_list;
    }

    public String getBgi() {
        return bgi;
    }

    public void setBgi(String bgi) {
        this.bgi = bgi;
    }
}
