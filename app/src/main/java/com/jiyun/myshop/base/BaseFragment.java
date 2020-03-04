package com.jiyun.myshop.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {

    protected P presenter;//P层的实例引用
    protected Context context;
    protected Activity activity;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        activity = getActivity();
        //避免约束布局时显示不完整
        View view = LayoutInflater.from(context).inflate(getLayout(),container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(view);
        presenter = createPresenter();
        if(presenter != null){
            presenter.attachView(this);
        }
        initView();
        initData();
    }


    //子类需实现方法
    protected abstract int getLayout() ;
    protected abstract P createPresenter();//创建p层引用
    protected abstract void initView();//初始化界面
    protected abstract void initData();//初始化数据

    @Override
    public void showTips(String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.dettachView();
        }
        if(unbinder != null){
            unbinder.unbind();
        }
    }
}
