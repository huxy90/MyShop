package com.jiyun.myshop.interfaces.notification;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.CategoryBottom;
import com.jiyun.myshop.model.bean.CategoryTop;
//分类顶部导航
public interface CategoryTopConstract {
    interface View extends IBaseView{
        void getCategoryTopReturn(CategoryTop bean);
        void getCategoryBoReturn(CategoryBottom bean);
    }
    interface Presenter extends IBasePresenter<View>{
        void getCategoryTop(String id);
        void getCategoryBottom(String id,int page,int size);
    }
}
