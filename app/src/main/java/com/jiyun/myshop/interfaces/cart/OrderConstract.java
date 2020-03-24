package com.jiyun.myshop.interfaces.cart;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.CartBean;
import com.jiyun.myshop.model.bean.DelCarInfo;

public interface OrderConstract {
    interface View extends IBaseView{
    }
    interface Presenter extends IBasePresenter<View>{
    }
}
