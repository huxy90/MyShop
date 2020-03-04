package com.jiyun.myshop.interfaces.topic;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.TopicBean;

/**
 * 专题 业务层接口
 */
public interface TopicConstract {

    interface  View extends IBaseView{
        void getTopicDataReturn(TopicBean bean);
    }
    interface Presenter extends IBasePresenter<View>{
        void getTopicData(int page,int size);
    }

}
