package com.jiyun.myshop.presenter.topic;

import com.jiyun.myshop.base.BasePresenter;
import com.jiyun.myshop.common.ResponseSubscriber;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.interfaces.topic.TopicConstract;
import com.jiyun.myshop.model.HttpManager;
import com.jiyun.myshop.model.bean.TopicBean;
import com.jiyun.myshop.utils.RxUtils;

public class TopicPresenter extends BasePresenter<TopicConstract.View> implements TopicConstract.Presenter{


    @Override
    public void getTopicData(int page, int size) {
        addSubcribe(HttpManager.getInstance().myServer().getTopic(page,size)
               .compose(RxUtils.rxScheduler())
               .subscribeWith(new ResponseSubscriber<TopicBean>(mView){
                   @Override
                   public void onNext(TopicBean bean) {
                       mView.getTopicDataReturn(bean);
                   }
               })
        );
    }
}
