package com.jiyun.myshop.model.apis;

import com.jiyun.myshop.model.bean.HomeBean;
import com.jiyun.myshop.model.bean.TopicBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyServer {
    @GET("index")
    Flowable<HomeBean> getHome();

    //专题
    @GET("topic/list")
    Flowable<TopicBean> getTopic(@Query("page") int page,@Query("size") int size);

}
