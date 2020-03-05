package com.jiyun.myshop.interfaces.login;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.AuthBean;

/**
 * 登录业务的契约类
 */
public interface LoginConstract {
    interface View extends IBaseView{
        void loginReturn(AuthBean bean);
    }
    interface Presenter extends IBasePresenter<View>{
        void login(String nickname,String password);
    }
}
