package com.fumiao.pay.mvp.store;

import android.app.Activity;

import com.fumiao.core.okgo.JsonCallback;
import com.fumiao.core.okgo.model.BaseResponse;
import com.fumiao.core.uitls.RsaUtils;
import com.fumiao.pay.mvp.base.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.File;

public class MemberAddPresenter extends BasePresenter<MemberAddView> {

    public MemberAddPresenter(MemberAddView mvpView, Activity activity) {
        super(mvpView, activity);
    }

    public void editMember(int id, String password, int store_id, String username, String realname, String position, long entry_time) {

        HttpParams httpParams = new HttpParams();
        httpParams.put("id", id);
        httpParams.put("password", RsaUtils.encryptByPublicKey(password));
        httpParams.put("store_id", store_id);
        httpParams.put("username", username);
        httpParams.put("realname", realname);
        httpParams.put("position", position);
//        httpParams.put("entry_time", entry_time / 1000);
        OkGo.<BaseResponse>post(CLERK_ADD)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        mvpView.showSuccessMsg("修改成功！");
                    }
                });
    }

    public void addMember(String password, int store_id, String username, String realname, String position) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("password", RsaUtils.encryptByPublicKey(password));
        httpParams.put("store_id", store_id);
        httpParams.put("username", username);
        httpParams.put("realname", realname);
        httpParams.put("position", position);
//        httpParams.put("entry_time", entry_time / 1000);
        OkGo.<BaseResponse>post(CLERK_ADD)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse>(activity) {
                    @Override
                    public void onSuccessCallback(Response<BaseResponse> response) {
                        mvpView.showSuccessMsg("添加成功！");
                    }
                });
    }
}
