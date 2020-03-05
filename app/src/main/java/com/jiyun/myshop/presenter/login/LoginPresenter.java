package com.jiyun.myshop.presenter.login;

import com.jiyun.myshop.base.BasePresenter;
import com.jiyun.myshop.common.ResponseSubscriber;
import com.jiyun.myshop.interfaces.login.LoginConstract;
import com.jiyun.myshop.model.HttpManager;
import com.jiyun.myshop.model.bean.AuthBean;
import com.jiyun.myshop.utils.RxUtils;

import io.reactivex.subscribers.ResourceSubscriber;

public class LoginPresenter extends BasePresenter<LoginConstract.View> implements LoginConstract.Presenter {
    @Override
    public void login(String nickname, String password) {
        addSubcribe(HttpManager.getInstance().myServer().getAuth(nickname,password)
                    .compose(RxUtils.rxScheduler())
                    .subscribeWith(new ResponseSubscriber<AuthBean>(mView){
                        @Override
                        public void onNext(AuthBean authBean) {
                            if(authBean.getErrno() == 0){
                                mView.loginReturn(authBean);
                            }else {
                                super.onNext(authBean);
                            }

                        }
                    }));
    }
}
