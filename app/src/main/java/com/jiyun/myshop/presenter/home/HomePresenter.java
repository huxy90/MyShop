package com.jiyun.myshop.presenter.home;

import android.util.Log;

import com.jiyun.myshop.base.BasePresenter;
import com.jiyun.myshop.common.ResponseSubscriber;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.interfaces.home.HomeConstract;
import com.jiyun.myshop.model.HttpManager;
import com.jiyun.myshop.model.bean.HomeBean;
import com.jiyun.myshop.utils.RxUtils;


public class HomePresenter extends BasePresenter<HomeConstract.View> implements HomeConstract.Presenter{

    @Override
    public void getHomeData() {
        addSubcribe(HttpManager.getInstance().myServer().getHome()
                .compose(RxUtils.<HomeBean>rxScheduler())
                .subscribeWith(new ResponseSubscriber<HomeBean>(mView){
                    @Override
                    public void onNext(HomeBean bean) {
                        if(bean.getErrno() == 0){
                            mView.getHomeDataReturn(bean);
                        }else {
                            super.onNext(bean);
                        }
                    }
                }));
    }


}
