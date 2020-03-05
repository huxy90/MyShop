package com.jiyun.myshop.ui.login;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseActivity;
import com.jiyun.myshop.interfaces.login.RegisterConstract;
import com.jiyun.myshop.model.bean.AuthBean;
import com.jiyun.myshop.presenter.login.RegisterPresenter;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterConstract.Presenter> implements RegisterConstract.View {

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.et_repwd)
    EditText et_repwd;
    @BindView(R.id.bt_reg)
    Button bt_reg;

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected RegisterConstract.Presenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.bt_reg)
    public void onViewClicked(){
        register();
    }

    private void register() {
       String name = et_name.getText().toString();
       String pwd = et_pwd.getText().toString();
       String repwd = et_repwd.getText().toString();
       if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd) && pwd.equals(repwd)){
           presenter.register(name,pwd);
       }else {

       }
    }

    @Override
    public void registerReturn(AuthBean bean) {
         if(bean.getErrno() == 0){
             Intent intent = new Intent(this,LoginActivity.class);
             intent.putExtra("nickname",bean.getData().getUserInfo().getNickname());
             setResult(110,intent);
             finish();
         }
    }
}
