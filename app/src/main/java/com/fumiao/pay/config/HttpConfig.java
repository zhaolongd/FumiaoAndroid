package com.fumiao.pay.config;

import com.fumiao.pay.BuildConfig;


public interface HttpConfig {

    String BASE_URL = BuildConfig.URL;
    String INIT_URL = BASE_URL + "/Member_Login/init";//初始化
    String IMG_CODE = BASE_URL + "/Member_Login/captcha";//图片验证码(注册短信)
    String GET_PHONE_CODE = BASE_URL + "/Member_Login/sendPhoneCode";//发送短信验证码
    String REGISTER_LOGIN = BASE_URL + "/Member_Login/dologin";//店长登录注册、店员登录网关
    String CATE_LIST = BASE_URL + "/store/cateList";//门店分类列表
    String STORE_ADD = BASE_URL + "/store/save";//门店新增、保存
    String AREA = BASE_URL + "/system/area";//地址
    String STORE_LIST = BASE_URL + "/store/getList";//门店列表接口
    String STORE_DETAILS = BASE_URL + "/store/getStore";//门店详情
    String MEMBER_LIST = BASE_URL + "/member/memberList";//门店店员列表
    String CLERK_ADD = BASE_URL + "/member/memberSave";//保存门店店员详情(店员、店长)
    String CLERK_EDIT = BASE_URL + "/Member/save_clerk"; //修改店员信息
    String MEMBER_PROFILE = BASE_URL + "/member/memberProfile";//门店成员详情
    String DEL_MEMBER = BASE_URL + "/member/delete";//删除成员
    String LOGOUT = BASE_URL + "/Member_Login/logout";//店长、店员退出登录
    String CHANGE_PASSWORD = BASE_URL + "/Member_Login/editPwd";//忘记密码
    String CHANGE_MANAGER_PASSWORD = BASE_URL + "/Member_Login/editManagerPwd";//安全密码
    String CHANGE_PHONE = BASE_URL + "/Member_Login/change_phone";//更换手机号
    String PAYMENT_LIST = BASE_URL + "/payment/getList";//账单列表
    String ORDER_DETAILS = BASE_URL + "/payment/paymentDetail";//账单详情
    String ORDER_SREEN = BASE_URL + "/payment/getFilterCondition";//账单筛选条件
    String HELP_LIST = BASE_URL + "/article/getList";//文章列表
    String SCAN_CODE = BASE_URL + "/cashier/scan";//扫码收银
    String STORE_PAY = BASE_URL + "/member/paySwitch";//店长支付开关
    String CHECK_UPDATE = BASE_URL + "/system/checkUpdate";//检查更新
    String ORDER_REFUND = BASE_URL + "/payment/pay_refund";//交易退款
    String ORDER_CANCEL = BASE_URL + "/payment/setVoid";//交易取消
    String REGISTRATION_PROTOCOL = "https://fmapp.51fumiao.com/wap/Article/registerProtocol";//用户注册协议
    String SET_DEFAULT_STORE = BASE_URL +"/Store/set_default_store";
    String CASHIER_DETAIL = BASE_URL + "/cashier/cashierDetail";//收银台二维码详情接口
    String BIND_CASHIER_QRCODE = BASE_URL+"/QrCode/activation_code"; //激活收款码、设备列表
    String STATISTIC_REPORT = BASE_URL+ "/payment/statistic_report"; //分析报表
    String REPORT_CALENDAR = BASE_URL+ "/payment/get_report_calendar"; //分析报表日历
    String BILL_STATISTICS = BASE_URL+ "/payment/billing_statistics"; //对账统计页面
    String REFUND_ORDER_DETAILS = BASE_URL + "/payment/get_refund_order";//退款账单详情
    String HOME_LIST = BASE_URL + "/home/get_list"; //首页数据
    String HOME_TODAY_PAYMENT = BASE_URL + "/payment/get_always";//首页获取当日时时收款数据
    String INIT_JPUSH_REGISTRANTION_ID = BASE_URL + "/Member/init_jpush_registration_id"; //极光推送上传registration id
    String CHAIER_QRCODE_LIST = BASE_URL + "/QrCode/device_list"; //收款码、设备列表
    String PAYMENT_ARRIVED_RECORDS = BASE_URL + "/payment/get_recently_arrived_records";
    String MY_SERVICE = "https://h5.m.taobao.com/alicare/meebot.html?appKey=ui7Y3VvEfa&type=dingding_channel"; //我的客服
    String INCOMING_RESULT = BASE_URL + "/QrCode/incoming_result"; //进件结果查询
    String PAYMENT_CODE = BASE_URL + "/QrCode/payment_code";
    String ALL_STORE_LIST = BASE_URL + "/Store/get_all_stores"; //老板账号下所有门店
}
