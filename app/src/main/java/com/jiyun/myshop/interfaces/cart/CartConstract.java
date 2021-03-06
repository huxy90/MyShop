package com.jiyun.myshop.interfaces.cart;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.CartBean;
import com.jiyun.myshop.model.bean.DelCarInfo;

public interface CartConstract {
    interface View extends IBaseView{
        void getCartReturn(CartBean bean);
        void delCartReturn(DelCarInfo bean);
    }
    interface Presenter extends IBasePresenter<View>{
        void getCart();
        void delCart(int productIds);
    }
}
