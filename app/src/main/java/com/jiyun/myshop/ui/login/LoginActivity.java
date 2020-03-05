package com.jiyun.myshop.ui.login;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.OnClick;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseActivity;
import com.jiyun.myshop.interfaces.login.LoginConstract;
import com.jiyun.myshop.model.bean.AuthBean;
import com.jiyun.myshop.presenter.login.LoginPresenter;
import com.jiyun.myshop.utils.SpUtils;

public class LoginActivity extends BaseActivity<LoginConstract.Presenter> implements LoginConstract.View {

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.bt_login)
    Button bt_login;
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.tv_wjpwd)
    TextView tv_wjpwd;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginConstract.Presenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.bt_login,R.id.tv_register,R.id.tv_wjpwd})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.bt_login:
                login();
                break;
            case R.id.tv_register:
                register();
                break;
            case R.id.tv_wjpwd:

                break;
        }

    }

    //注册
    private void register() {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == 110){
            String nickname = data.getStringExtra("nickname");
            et_name.setText(nickname);
        }
    }

    //执行登录
    private void login() {
        String nickname = et_name.getText().toString();
        String pw = et_pwd.getText().toString();
        if(!TextUtils.isEmpty(nickname) && !TextUtils.isEmpty(pw)){
            presenter.login(nickname,pw);
        }
    }

    @Override
    public void loginReturn(AuthBean bean) {
        String token = bean.getData().getToken();
        SpUtils.getInstance().setValue("token",token);
        finish();
    }
}
