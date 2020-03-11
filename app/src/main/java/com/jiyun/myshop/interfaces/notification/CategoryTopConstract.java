package com.jiyun.myshop.interfaces.notification;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.CategoryTop;
//分类顶部导航
public interface CategoryTopConstract {
    interface View extends IBaseView{
        void getCategoryTopReturn(CategoryTop bean);
    }
    interface Presenter extends IBasePresenter<View>{
        void getCategoryTop(String id);
    }
}
