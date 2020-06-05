package com.fumiao.pay.config;


import com.fumiao.pay.BuildConfig;

public interface KeyConfig {

    String SPLASH_PAGE="com.fumiao.pay.ui.activity.SplashActivity";
    String MAIN_PAGE="com.fumiao.pay.activity.MainActivity";
    int LOGIN_SUCCESS = 2001;//登录成功
    int LOGIN_EXIT = 2002; //退出登录
    int REFUND_SUCCESS = 1; //退款成功
    int CANCEL_ORDER_SUCCESS = 2; //取消订单
    String LOGIN_ID = "LOGIN_ID";
    String STORE_ID = "STORE_ID";
    String IS_BOSS = "IS_BOSS";
    String ORIGIN_KEY = BuildConfig.ORIGIN_KEY;
    //登录后存储的动态key,之后请求接口sign需要用到该key
    String KEY= "KEY";
    String REASON= "reason";
    String STORE_MANAGER_ID = "MANAGER_ID";//店长id
    String STORE_NAME = "NAME";//门店名称
    String STORE_IMAGE = "IMAGE";//门店封面
    String STORE_CATE_ID = "CATE_ID";
    String STORE_CATE_NAME = "CATE_NAME";
    String STORE_DESCRIPTION = "DESCRIPTION";
    String STORE_PROVINCE = "PROVINCE";
    String STORE_PROVINCE_NAME = "PROVINCE_NAME";
    String STORE_CITY = "CITY";
    String STORE_CITY_NAME = "CITY_NAME";
    String STORE_AREA = "AREA";
    String STORE_AREA_NAME = "AREA_NAME";
    String STORE_ADDRESS = "ADDRESS";
    String STORE_STATUS = "STATUS";//门店状态：1正常，2冻结，3删除
    String STORE_CATE_TAGS = "CATE_TAGS";
    //    0：未提交
//1：审核中
//2：审核通过
//3：审核不通过
    String PAYMENT_STATUS = "payment_status";

    String IS_MENBER = "IS_MENBER";//是否为店员

    String USER_PHONE = "user_phone";//登录手机号码

    String REMEMBER_SHOPKEEPER="rememberShopkeeper";
    String REMEMBER_CLERK="rememberClerk";
    String SHOPKEEPER_PHONE="SHOPKEEPER_phone";
    String  CLERK_PHONE ="CLERK_PHONE";
    String SHOPKEEPER_PASSWORD="SHOPKEEPER_PASSWORD";
    String CLERK_PSSSWORD ="CLERK_PSSSWORD";

    String STORE_PAY_SWITCH = "store_pay_switch";

    String VOICE_SWITCH ="VOICE_SWITCH";//语音开关
    String SWITCH_PUSH = "switchPush";//系统推送开关
    String SWITCH_NOTICE = "SWITCH_NOTICE"; //通知栏显示信息详情

}
