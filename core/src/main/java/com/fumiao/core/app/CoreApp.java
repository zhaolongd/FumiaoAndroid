package com.fumiao.core.app;

import android.app.Application;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.fumiao.core.uitls.AppUtils;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import okhttp3.OkHttpClient;

/**
 * Created by chee on 2018/8/21.
 */

public class CoreApp extends Application {

    private static CoreApp single;
    public static boolean isDebug = true;//是否为调试模式
    public static final long MILLISECONDS = 15000;

    @Override
    public void onCreate() {
        super.onCreate();
        single = this;
        Logger.addLogAdapter(new AndroidLogAdapter());
        initOkGo();
    }

    private void initOkGo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
//log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
//log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);

        //全局的读取超时时间
        builder.readTimeout(MILLISECONDS, TimeUnit.MILLISECONDS);
//全局的写入超时时间
        builder.writeTimeout(MILLISECONDS, TimeUnit.MILLISECONDS);
//全局的连接超时时间
        builder.connectTimeout(MILLISECONDS, TimeUnit.MILLISECONDS);
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));//使用SharedPreferences保持cookie，如果cookie不过期，则一直有效


        String versionName = AppUtils.getVersionName(this);
        HttpHeaders baseParams = new HttpHeaders();
        baseParams.put("Version-Control", "Android/" + versionName);
//        baseParams.put("platform", "android");
        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .addCommonHeaders(baseParams);
    }

    public static CoreApp getSingle() {
        return single;
    }

}
