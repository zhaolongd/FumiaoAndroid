package com.fumiao.pay.bean.home;

import com.fumiao.core.adapter.CoreBean;

/**
 * Created by chee on 2018/9/12.
 */
public class HomeMenuBean extends CoreBean {


    /**
     * title : 付款方式
     * img_url : http://api.faka.zuy.cn/static/upload/5b5addb00d1b5/5b5addb00d1ee.png
     * function_id : 9
     * sort : 0
     * is_show : true
     * function_links : cc.zuy.faka_android.ui.activity.menu.PayWayActivity
     * iOS_ViewType : 1
     * iOS_sotryBoard : Home
     * iOS_ViewController : PayTypeListViewController
     * android_Ver : 1.0
     * iOS_Ver : 1.0
     */

   private int id;
   private String icon;
   private String name;
   private String android_url;
   private int is_display;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAndroid_url() {
        return android_url;
    }

    public void setAndroid_url(String android_url) {
        this.android_url = android_url;
    }

    public int getIs_display() {
        return is_display;
    }

    public void setIs_display(int is_display) {
        this.is_display = is_display;
    }
}
