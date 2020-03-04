package com.jiyun.myshop.interfaces.home;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.HomeBean;

/**
 * 业务层接口
 */
public interface HomeConstract {
    interface View extends IBaseView{
        void getHomeDataReturn(HomeBean result);
    }
    interface Presenter extends IBasePresenter<View>{
        void getHomeData();
    }
}
