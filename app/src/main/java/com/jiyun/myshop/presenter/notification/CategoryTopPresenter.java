package com.jiyun.myshop.presenter.notification;

import com.jiyun.myshop.base.BasePresenter;
import com.jiyun.myshop.common.ResponseSubscriber;
import com.jiyun.myshop.interfaces.notification.CategoryTopConstract;
import com.jiyun.myshop.model.HttpManager;
import com.jiyun.myshop.model.bean.CategoryBottom;
import com.jiyun.myshop.model.bean.CategoryTop;
import com.jiyun.myshop.utils.RxUtils;

public class CategoryTopPresenter extends BasePresenter<CategoryTopConstract.View> implements CategoryTopConstract.Presenter {
    @Override
    public void getCategoryTop(String id) {

        addSubcribe(HttpManager.getInstance().myServer().getCategoryTop(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new ResponseSubscriber<CategoryTop>(mView){
            @Override
            public void onNext(CategoryTop bean) {
                if(bean.getErrno() == 0){
                    mView.getCategoryTopReturn(bean);
                }else{
                    super.onNext(bean);
                }
            }
        }));
    }
    @Override
    public void getCategoryBottom(String id, int page, int size) {
        addSubcribe(HttpManager.getInstance().myServer().getCategoryBottom(id,page,size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubscriber<CategoryBottom>(mView){
                    @Override
                    public void onNext(CategoryBottom bean) {
                        if(bean.getErrno() == 0){
                            mView.getCategoryBoReturn(bean);
                        }else{
                            super.onNext(bean);
                        }
                    }
                }));
    }
}
