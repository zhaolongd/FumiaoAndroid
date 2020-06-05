package com.fumiao.core.okgo;

import android.app.Activity;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.fumiao.core.app.CoreActivity;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.L;
import com.fumiao.core.uitls.MD5Utils;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUitl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.fumiao.core.okgo.JsonConvert.SUCCESS;


/**
 * Created by chee on 2018/6/4.
 */
public abstract class JsonCallback<T> extends AbsCallback<T> {

    private Activity activity;
    private boolean showLoad;
    private Type type;
    private Class<T> clazz;
    private String key = SPUtil.getInstance().getString( "KEY");

    public JsonCallback(Activity activity) {
        this.activity = (CoreActivity) activity;
        this.showLoad = true;
    }

    public JsonCallback(Activity activity, boolean showLoad) {
        this.activity = (CoreActivity) activity;
        this.showLoad = showLoad;
    }

    public JsonCallback(Activity activity, boolean showLoad, String key) {
        this.activity = (CoreActivity) activity;
        this.showLoad = showLoad;
        this.key = key;
    }

    @Override
    public void onSuccess(Response<T> response) {
        if (((BaseResponse) response.body()).code == SUCCESS) {
            onSuccessCallback(response);
        } else {
            ToastUitl.show(((BaseResponse) response.body()).msg);
        }
    }

    public abstract void onSuccessCallback(Response<T> response);

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        L.e("OkGo", response.getException().getMessage());
        Throwable exception = response.getException();
        if ("登陆失效".equals(response.getException().getMessage())) {
            ((CoreActivity) activity).toLogin();
        } else if(exception instanceof UnknownHostException ||exception instanceof ConnectException){
            ToastUitl.show("与服务器连接失败，请稍后重试！");
        }else {
           ToastUitl.show(response.getException().getMessage());
        }
    }

    @Override
    public void onStart(Request request) {
        request.getParams().put("request_time", System.currentTimeMillis()/ 1000);
        List<String> signs = new ArrayList<>();
        for (ConcurrentHashMap.Entry<String, List<String>> entry : request.getParams().urlParamsMap.entrySet()) {
            signs.add(entry.getKey()+"="+entry.getValue());
        }
        for (ConcurrentHashMap.Entry<String, List<HttpParams.FileWrapper>> entry : request.getParams().fileParamsMap.entrySet()) {
            signs.add(entry.getKey()+"="+entry.getValue());
        }
       final List<String> params = new ArrayList<>();
        for (int i = 0; i < signs.size(); i++) {
            //参数为空不参与加密
            String sign = signs.get(i);
            if(sign.equals("")){
                continue;
            }
            int start = sign.indexOf("[");
            int end = sign.lastIndexOf("]");
            String param = (sign.split("=")[0]) + "=" + sign.substring(start + 1, end);
            params.add(param);
        }
        if(params.size()>0){
            //排序
            Collections.sort(params);
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < params.size(); i++) {
                //文件不参与加密
                if (params.get(i).contains("File")) {
                    continue;
                }
                if (!"".equals(buffer.toString())) {
                    buffer.append("&");
                }
                buffer.append(params.get(i));
            }
            buffer.append("&key=" + key);

            try {
                request.getParams().put("sign", MD5Utils.getMD5(buffer.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (activity instanceof CoreActivity && showLoad) {
            ((CoreActivity) activity).showLoad();
        }
    }

    @Override
    public void onCacheSuccess(Response response) {
    }

    @Override
    public void onFinish() {
        if (activity instanceof CoreActivity && showLoad) {
            ((CoreActivity) activity).hintLoad();
        }
    }

    @Override
    public void uploadProgress(Progress progress) {
    }

    @Override
    public void downloadProgress(Progress progress) {
    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                JsonConvert<T> convert = new JsonConvert<>(clazz);
                return convert.convertResponse(response);
            }
        }
        JsonConvert<T> convert = new JsonConvert<>(type);
        return convert.convertResponse(response);
    }

}
