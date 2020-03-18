package com.jiyun.myshop.interfaces.notification;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.CategoryBottom;
//分类详细信息列表
public interface CategoryBoConstract {
    interface View extends IBaseView{
        void getCategoryBoReturn(CategoryBottom bean);
    }
    interface Presenter extends IBasePresenter<View>{
        void getCategoryBottom(int id,int page,int size);
    }
}
