package com.jiyun.myshop.presenter.notification;

import android.util.Log;

import com.jiyun.myshop.base.BasePresenter;
import com.jiyun.myshop.common.ResponseSubscriber;
import com.jiyun.myshop.interfaces.notification.CategoryBoConstract;
import com.jiyun.myshop.model.HttpManager;
import com.jiyun.myshop.model.bean.CatalogByIdBean;
import com.jiyun.myshop.model.bean.CategoryBottom;
import com.jiyun.myshop.utils.RxUtils;

public class CategoryBoPresenter extends BasePresenter<CategoryBoConstract.View> implements CategoryBoConstract.Presenter {

    @Override
    public void getCategoryBottom(int id, int page, int size) {
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
