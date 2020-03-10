package com.jiyun.myshop.presenter.notification;

import com.jiyun.myshop.base.BasePresenter;
import com.jiyun.myshop.common.ResponseSubscriber;
import com.jiyun.myshop.interfaces.notification.CatalogConstract;
import com.jiyun.myshop.model.HttpManager;
import com.jiyun.myshop.model.bean.CatalogBean;
import com.jiyun.myshop.model.bean.CatalogByIdBean;
import com.jiyun.myshop.utils.RxUtils;

public class CatalogPresenter extends BasePresenter<CatalogConstract.View> implements CatalogConstract.Presenter {
    @Override
    public void getCatalog() {
        addSubcribe(HttpManager.getInstance().myServer().getCatalog()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new ResponseSubscriber<CatalogBean>(mView){
            @Override
            public void onNext(CatalogBean bean) {
                if(bean.getErrno() == 0){
                    mView.getCatalogReturn(bean);
                }else {
                    super.onNext(bean);
                }
            }
        }));
    }

    @Override
    public void getCatalogById(String id) {
        addSubcribe(HttpManager.getInstance().myServer().getCatalogById(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubscriber<CatalogByIdBean>(mView){
                    @Override
                    public void onNext(CatalogByIdBean bean) {
                        if(bean.getErrno() == 0){
                            mView.getCatalogByIdReturn(bean);
                        }else {
                            super.onNext(bean);
                        }
                    }
                }));
    }
}
