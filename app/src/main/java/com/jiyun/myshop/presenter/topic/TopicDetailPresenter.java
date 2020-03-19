package com.jiyun.myshop.presenter.topic;

import android.util.Log;

import com.jiyun.myshop.base.BasePresenter;
import com.jiyun.myshop.common.ResponseSubscriber;
import com.jiyun.myshop.interfaces.topic.TopicConstract;
import com.jiyun.myshop.interfaces.topic.TopicDetailConstract;
import com.jiyun.myshop.model.HttpManager;
import com.jiyun.myshop.model.bean.CommentBean;
import com.jiyun.myshop.model.bean.TopicBean;
import com.jiyun.myshop.model.bean.TopicDetailBean;
import com.jiyun.myshop.model.bean.TopicTJ;
import com.jiyun.myshop.utils.RxUtils;

public class TopicDetailPresenter extends BasePresenter<TopicDetailConstract.View> implements TopicDetailConstract.Presenter{

    @Override
    public void getTopicDetail(int id) {
        addSubcribe(HttpManager.getInstance().myServer().getTopicDetail(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubscriber<TopicDetailBean>(mView){
                    @Override
                    public void onNext(TopicDetailBean bean) {
                        mView.getTopicDetailReturn(bean);
                    }
                })
        );
    }

    @Override
    public void getComment(int valueId, int typeId, int size) {
        addSubcribe(HttpManager.getInstance().myServer().getTopicComment(valueId,typeId,size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubscriber<CommentBean>(mView){
                    @Override
                    public void onNext(CommentBean bean) {
                        mView.getCommentReturn(bean);
                    }
                })
        );
    }

    @Override
    public void getTopicTJ(int id) {
        addSubcribe(HttpManager.getInstance().myServer().getTopicTJ(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubscriber<TopicTJ>(mView){
                    @Override
                    public void onNext(TopicTJ bean) {
                        Log.e("TAG",bean.toString());
                        mView.getTopicTJReturn(bean);
                    }
                })
        );
    }
}
