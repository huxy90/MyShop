package com.jiyun.myshop.presenter.notification;

import android.util.Log;

import com.jiyun.myshop.base.BasePresenter;
import com.jiyun.myshop.common.ResponseSubscriber;
import com.jiyun.myshop.interfaces.notification.CatalogConstract;
import com.jiyun.myshop.interfaces.notification.SearchConstract;
import com.jiyun.myshop.model.HttpManager;
import com.jiyun.myshop.model.bean.CatalogBean;
import com.jiyun.myshop.model.bean.CatalogByIdBean;
import com.jiyun.myshop.model.bean.GoodListBean;
import com.jiyun.myshop.utils.RxUtils;

public class SearchPresenter extends BasePresenter<SearchConstract.View> implements SearchConstract.Presenter {

    @Override
    public void getGoodList(String keyword, int page, int size, String sort, String order, int categoryId) {
        addSubcribe(HttpManager.getInstance().myServer().getGoodsList(keyword,page,size,sort,order,categoryId)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubscriber<GoodListBean>(mView){
                    @Override
                    public void onNext(GoodListBean bean) {
                        Log.e("TAG",bean+"-------");
                        if(bean.getErrno() == 0){
                            mView.getGoodListReturn(bean);
                        }else {
                            super.onNext(bean);
                        }
                    }
                }));
    }
}
