package com.jiyun.myshop.interfaces.notification;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.CatalogBean;
import com.jiyun.myshop.model.bean.CatalogByIdBean;

/**
 * 分类  契约类
 */
public interface CatalogConstract {
    interface View extends IBaseView {
        void getCatalogReturn(CatalogBean bean);
        void getCatalogByIdReturn(CatalogByIdBean bean);
    }
    interface Presenter extends IBasePresenter<View>{
        void getCatalog();
        void getCatalogById(String id);
    }
}
