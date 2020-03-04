package com.jiyun.myshop.model;

import com.jiyun.myshop.common.Constant;
import com.jiyun.myshop.model.apis.MyServer;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装网络请求的类型
 * Retrofit+RxJava
 */
public class HttpManager {

    private static volatile HttpManager instance;

    //提供单例模式方法
    public static HttpManager getInstance(){
        if(instance == null){
            synchronized (HttpManager.class){
                if (instance == null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    /**
     * 获取Retrofit对象
     * @param baseUrl
     * @return
     */
    public Retrofit getrRetrofit(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient())
                .build();
        return retrofit;
    }

    /**
     * 获取网络请求的OkHttpClient对象
     * @return
     */
    public OkHttpClient okHttpClient(){
        Cache cache = new Cache(new File(Constant.PATH_CACHE), 10 * 1024 * 1024);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        return client;
    }

    private MyServer myServer;
    /**
     * 获取MyServer网络接口类
     * @return
     */
    public MyServer myServer(){
        if(myServer == null){
            synchronized (MyServer.class){
                if(myServer == null){
                    myServer = getrRetrofit(Constant.BASE_SHOP_URL).create(MyServer.class);
                }
            }
        }
        return myServer;
    }
}
