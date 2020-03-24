package com.jiyun.myshop.interfaces.notification;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.CatalogBean;
import com.jiyun.myshop.model.bean.CatalogByIdBean;
import com.jiyun.myshop.model.bean.GoodListBean;

import retrofit2.http.Query;

/**
 * 搜索  契约类
 */
public interface SearchConstract {
    interface View extends IBaseView {
        void getGoodListReturn(GoodListBean bean);
    }
    interface Presenter extends IBasePresenter<View>{
        void getGoodList(String keyword, int page,int size,String sort,String order,int categoryId);
    }
}
