package com.jiyun.myshop.presenter.home;

import com.jiyun.myshop.base.BasePresenter;
import com.jiyun.myshop.common.ResponseSubscriber;
import com.jiyun.myshop.interfaces.home.BrandConstract;
import com.jiyun.myshop.model.HttpManager;
import com.jiyun.myshop.model.bean.BrandBean;
import com.jiyun.myshop.utils.RxUtils;

public class BrandPresenter extends BasePresenter<BrandConstract.View> implements BrandConstract.Presenter {

    @Override
    public void getBrandData(String brandId, String page, String size) {
        addSubcribe(HttpManager.getInstance().myServer().getBrand(brandId,page,size)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new ResponseSubscriber<BrandBean>(mView){
            @Override
            public void onNext(BrandBean bean) {
                if(bean.getErrno() == 0){
                    mView.getBrandReturn(bean);
                }else {
                    super.onNext(bean);
                }
            }
        }));
    }
}
