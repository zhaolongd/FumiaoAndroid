package com.fumiao.pay.mvp.base;

import android.app.Activity;
import android.widget.Toast;

import com.fumiao.pay.config.HttpConfig;
import com.fumiao.pay.config.KeyConfig;

/**
 * Created by chee on 2018/6/4.
 */
public class BasePresenter<V> implements HttpConfig, KeyConfig {
    public V mvpView;
    public Activity activity;

    public BasePresenter(V mvpView, Activity activity) {
        this.mvpView = mvpView;
        this.activity = activity;
    }

    public void attachView(V mvpView, Activity activity) {
        this.mvpView = mvpView;
        this.activity = activity;
    }

    public void detachView() {
        this.mvpView = null;
    }


    public interface Callback {
        void ok();
    }

    public void toast(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

}
