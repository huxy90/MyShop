package com.jiyun.myshop.base;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    protected V mView;//当前关联的view
    WeakReference<V> weakReference;//采用弱引用关联view

    @Override
    public void attachView(V view) {
        weakReference = new WeakReference(view);
        mView = weakReference.get();
    }

    @Override
    public void dettachView() {
        mView = null;
    }

    //RxJava2背压式处理内存
    CompositeDisposable compositeDisposable;

    /**
     * 添加请求数据的对象到被压式管理compositeDisposable
     * @param disposable
     */
    protected void addSubcribe(Disposable disposable){
        if(compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
}
