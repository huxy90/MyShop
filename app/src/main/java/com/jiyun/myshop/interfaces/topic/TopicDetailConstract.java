package com.jiyun.myshop.interfaces.topic;

import com.jiyun.myshop.interfaces.IBasePresenter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.model.bean.CommentBean;
import com.jiyun.myshop.model.bean.TopicBean;
import com.jiyun.myshop.model.bean.TopicDetailBean;
import com.jiyun.myshop.model.bean.TopicTJ;

/**
 * 专题详情 业务层接口
 */
public interface TopicDetailConstract {

    interface  View extends IBaseView{
        void getTopicDetailReturn(TopicDetailBean bean);
        void getCommentReturn(CommentBean bean);
        void getTopicTJReturn(TopicTJ bean);
    }
    interface Presenter extends IBasePresenter<View>{
        void getTopicDetail(int id);
        void getComment(int valueId,int typeId,int size);
        void getTopicTJ(int id);
    }

}
