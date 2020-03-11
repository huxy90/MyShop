package com.jiyun.myshop.interfaces.home;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.BrandBean;

/**
 * 品牌制造商
 */
public interface BrandConstract {
    interface View extends IBaseView{
        void getBrandReturn(BrandBean bean);
    }
    interface Presenter extends IBasePresenter<View>{
        void getBrandData(String brandId,String page,String size);
    }
}
