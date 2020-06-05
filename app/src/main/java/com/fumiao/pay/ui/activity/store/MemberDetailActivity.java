package com.fumiao.pay.ui.activity.store;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import com.fumiao.core.uitls.EmojiFilter;
import com.fumiao.core.uitls.ToastUitl;
import com.fumiao.core.widget.KeyEditText;
import com.fumiao.pay.R;
import com.fumiao.pay.app.MvpActivity;
import com.fumiao.pay.bean.store.MemberDetailsBean;
import com.fumiao.pay.mvp.store.MemberDetailPresenter;
import com.fumiao.pay.mvp.store.MemberDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MemberDetailActivity extends MvpActivity<MemberDetailPresenter> implements MemberDetailView {

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

    boolean isEditeMember = false;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_add);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        loginUser.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        userName.getEditText().setFilters(new InputFilter[]{new EmojiFilter()});
        userJob.getEditText().setFilters(new InputFilter[]{new EmojiFilter()});
        id = getIntent().getIntExtra("member_id", 0);
        isEditeMember = getIntent().getBooleanExtra("edit_member",false);
        mvpPresenter.getMemberDetail(id);
        if(isEditeMember){
            setTitle("修改店员信息");
            btnComfirm.setText("确认修改");
            btnComfirm.setVisibility(View.VISIBLE);
        }else{
            setTitle("店员信息");
            userName.getEditText().setEnabled(false);
            userJob.getEditText().setEnabled(false);
            loginUser.getEditText().setEnabled(false);
            loginPassword.getEditText().setEnabled(false);
            btnComfirm.setVisibility(View.GONE);
            setRight("编辑", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setTitle("修改店员信息");
                    btnComfirm.setText("确认修改");
                    btnComfirm.setVisibility(View.VISIBLE);
                    userName.getEditText().setEnabled(true);
                    userJob.getEditText().setEnabled(true);
                    loginUser.getEditText().setEnabled(true);
                    loginPassword.getEditText().setEnabled(true);
                }
            });
        }
    }

    @Override
    protected MemberDetailPresenter createPresenter() {
        return new MemberDetailPresenter(this, this);
    }

    @Override
    public void showMemberDetail(MemberDetailsBean data) {
        userName.setText(data.getMember().getRealname());
        userJob.setText(data.getMember().getPosition());
        loginUser.setText(data.getMember().getPhone());
        loginPassword.setText(data.getMember().getOrigin_pwd());
    }

    @Override
    public void editeMemberSuccess() {
        ToastUitl.show("修改成功");
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
        mvpPresenter.editMember(id, loginPassword.getText(),loginUser.getText(), userName.getText(), userJob.getText());
    }
}
