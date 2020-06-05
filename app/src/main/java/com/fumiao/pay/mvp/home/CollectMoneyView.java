package com.fumiao.pay.mvp.home;

import com.fumiao.pay.bean.main.ScanBean;
import com.fumiao.pay.mvp.base.BaseView;


public interface CollectMoneyView extends BaseView {
    void showPay(ScanBean data);
    void jumpScanQRCode();
}
