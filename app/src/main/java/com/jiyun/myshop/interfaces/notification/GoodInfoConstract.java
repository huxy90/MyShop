package com.jiyun.myshop.interfaces.notification;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.AddCartBean;
import com.jiyun.myshop.model.bean.GoodInfoBean;
import com.jiyun.myshop.model.bean.GoodInfoBo;


public interface GoodInfoConstract {
    interface View extends IBaseView{
        void getGoodInfoReturn(GoodInfoBean bean);
        void getGoodInfoBoReturn(GoodInfoBo bo);
        void addCartReturn(AddCartBean bean);
    }
    interface Presenter extends IBasePresenter<View>{
        void getGoodInfo(int id);
        void getGoodInfoBo(int id);
        void addCart(int goodsId, int number, int productId);
    }
}
