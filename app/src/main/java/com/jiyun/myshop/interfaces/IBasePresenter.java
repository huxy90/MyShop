package com.jiyun.myshop.interfaces;

/**
 * P层基类接口
 * 和View层关联 */
public interface IBasePresenter<T extends IBaseView> {
    void attachView(T view);//关联View层和P层
    void dettachView();//解绑View层的关联
}
