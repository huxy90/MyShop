package com.jiyun.myshop.interfaces.home;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.HomeBean;

import java.util.List;

/**
 * 业务层接口
 */
public interface HomeConstract {
    interface View extends IBaseView{
        void getHomeDataReturn(List<HomeBean.HomeListBean> result);
    }
    interface Presenter extends IBasePresenter<View>{
        void getHomeData();
    }
}
