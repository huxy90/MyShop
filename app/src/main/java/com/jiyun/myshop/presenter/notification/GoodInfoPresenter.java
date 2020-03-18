package com.jiyun.myshop.presenter.notification;

import com.jiyun.myshop.base.BasePresenter;
import com.jiyun.myshop.common.ResponseSubscriber;
import com.jiyun.myshop.interfaces.notification.GoodInfoConstract;
import com.jiyun.myshop.model.HttpManager;
import com.jiyun.myshop.model.bean.GoodInfoBean;
import com.jiyun.myshop.model.bean.GoodInfoBo;
import com.jiyun.myshop.utils.RxUtils;

public class GoodInfoPresenter extends BasePresenter<GoodInfoConstract.View> implements GoodInfoConstract.Presenter {

    @Override
    public void getGoodInfo(int id) {
        addSubcribe(HttpManager.getInstance().myServer().getGoodInfo(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new ResponseSubscriber<GoodInfoBean>(mView){
            @Override
            public void onNext(GoodInfoBean bean) {
                if(bean.getErrno() == 0){
                    mView.getGoodInfoReturn(bean);
                }else {
                    super.onNext(bean);
                }
            }
        }));
    }

    @Override
    public void getGoodInfoBo(int id) {
        addSubcribe(HttpManager.getInstance().myServer().getGoodInfoBo(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubscriber<GoodInfoBo>(mView){
                    @Override
                    public void onNext(GoodInfoBo bean) {
                        if(bean.getErrno() == 0){
                            mView.getGoodInfoBoReturn(bean);
                        }else {
                            super.onNext(bean);
                        }
                    }
                }));
    }
}
