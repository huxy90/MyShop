package com.jiyun.myshop.presenter.login;

import com.jiyun.myshop.base.BasePresenter;
import com.jiyun.myshop.common.ResponseSubscriber;
import com.jiyun.myshop.interfaces.login.RegisterConstract;
import com.jiyun.myshop.model.HttpManager;
import com.jiyun.myshop.model.bean.AuthBean;
import com.jiyun.myshop.utils.RxUtils;

public class RegisterPresenter extends BasePresenter<RegisterConstract.View> implements RegisterConstract.Presenter {
    @Override
    public void register(String nickname, String password) {
        addSubcribe(HttpManager.getInstance().myServer().register(nickname,password)
               .compose(RxUtils.rxScheduler())
        .subscribeWith(new ResponseSubscriber<AuthBean>(mView){
            @Override
            public void onNext(AuthBean bean) {
                if(bean.getErrno() == 0){
                    mView.registerReturn(bean);
                }else {
                    super.onNext(bean);
                }
            }
        }));
    }
}
