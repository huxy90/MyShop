package com.jiyun.myshop.interfaces.login;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.AuthBean;

public interface RegisterConstract {
    interface View extends IBaseView{
        void registerReturn(AuthBean bean);
    }
    interface Presenter extends IBasePresenter<View>{
        void register(String nickname,String password);
    }
}
