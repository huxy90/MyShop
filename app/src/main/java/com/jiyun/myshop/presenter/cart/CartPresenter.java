package com.jiyun.myshop.presenter.cart;

import com.jiyun.myshop.base.BasePresenter;
import com.jiyun.myshop.common.ResponseSubscriber;
import com.jiyun.myshop.interfaces.cart.CartConstract;
import com.jiyun.myshop.model.HttpManager;
import com.jiyun.myshop.model.bean.CartBean;
import com.jiyun.myshop.model.bean.DelCarInfo;
import com.jiyun.myshop.utils.RxUtils;

public class CartPresenter extends BasePresenter<CartConstract.View> implements CartConstract.Presenter {
    @Override
    public void getCart() {
        addSubcribe(HttpManager.getInstance().myServer().getCartInfo()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new ResponseSubscriber<CartBean>(mView){
            @Override
            public void onNext(CartBean bean) {
                if(bean.getErrno() == 0){
                    mView.getCartReturn(bean);
                }else {
                    super.onNext(bean);
                }
            }
        }));
    }

    @Override
    public void delCart(int productIds) {
        addSubcribe(HttpManager.getInstance().myServer().delCart(productIds)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubscriber<DelCarInfo>(mView){
                    @Override
                    public void onNext(DelCarInfo bean) {
                        if(bean.getErrno() == 0){
                            mView.delCartReturn(bean);
                        }else {
                            super.onNext(bean);
                        }
                    }
                }));
    }
}
