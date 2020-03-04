package com.jiyun.myshop.base;

import android.os.Bundle;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 抽取View层实现基类
 */
public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    protected  P presenter;//有P层引用
    Unbinder unbinder;//butterknife对象

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        presenter = createPresenter();
        unbinder = ButterKnife.bind(this);
        //关联View
        if(presenter != null){
            presenter.attachView(this);
        }
        initView();
        initData();
    }




    //抽象方法，子类需要实现
    public abstract int getLayout();//获取加载的View id
    protected abstract P createPresenter();//创建P层实例
    protected abstract void initData();//初始化数据
    protected abstract void initView();//初始化界面

    @Override
    public void showTips(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.dettachView();
        }
        if(unbinder != null){
            unbinder.unbind();
        }
    }
}
