package com.jiyun.myshop.interfaces.cart;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.CartBean;

public interface CartConstract {
    interface View extends IBaseView{
        void getCartReturn(CartBean bean);
    }
    interface Presenter extends IBasePresenter<View>{
        void getCart();
    }
}
