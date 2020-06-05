package com.fumiao.pay.ui.activity.store;

import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import com.fumiao.core.uitls.EmojiFilter;
import com.fumiao.core.uitls.SPUtil;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.widget.KeyEditText;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.mvp.store.MemberAddPresenter;
import com.fumiao.pay.mvp.store.MemberAddView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MemberAddActivity extends MvpActivity<MemberAddPresenter> implements MemberAddView {

    @BindView(R.id.user_name)
    KeyEditText userName;
    @BindView(R.id.user_job)
    KeyEditText userJob;
    @BindView(R.id.login_user)
    KeyEditText loginUser;
    @BindView(R.id.login_password)
    KeyEditText loginPassword;
    @BindView(R.id.btn_comfirm)
    Button btnComfirm;
    int store_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_add);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setTitle("添加店员");
        store_id = SPUtil.getInstance().getInt(STORE_ID);
        loginUser.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        userName.getEditText().setFilters(new InputFilter[]{new EmojiFilter()});
        userJob.getEditText().setFilters(new InputFilter[]{new EmojiFilter()});
    }

    @Override
    protected MemberAddPresenter createPresenter() {
        return new MemberAddPresenter(this, this);
    }

    @Override
    public void showSuccessMsg(String s) {
        ToastUitl.show("添加成功");
        finish();
    }

    @OnClick(R.id.btn_comfirm)
    public void onViewClicked() {

        if(userName.getText().isEmpty()){
            ToastUitl.show("请输入店员名字");
            return;
        }
        if(userJob.getText().isEmpty()){
            ToastUitl.show("请输入担任职位");
            return;
        }
        if(loginUser.getText().isEmpty()){
            ToastUitl.show("账号请输入店员手机号");
            return;
        }
        if(loginPassword.getText().isEmpty()){
            ToastUitl.show("请输入登录密码");
            return;
        }
        mvpPresenter.addMember(loginPassword.getText(), store_id, loginUser.getText(), userName.getText(), userJob.getText());
    }
}
