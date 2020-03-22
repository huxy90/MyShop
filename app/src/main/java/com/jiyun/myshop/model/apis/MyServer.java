package com.jiyun.myshop.model.apis;

import com.jiyun.myshop.model.bean.AddCartBean;
import com.jiyun.myshop.model.bean.AuthBean;
import com.jiyun.myshop.model.bean.BrandBean;
import com.jiyun.myshop.model.bean.CartBean;
import com.jiyun.myshop.model.bean.CatalogBean;
import com.jiyun.myshop.model.bean.CatalogByIdBean;
import com.jiyun.myshop.model.bean.CategoryBottom;
import com.jiyun.myshop.model.bean.CommentBean;
import com.jiyun.myshop.model.bean.GoodInfoBean;
import com.jiyun.myshop.model.bean.GoodInfoBo;
import com.jiyun.myshop.model.bean.HomeBean;
import com.jiyun.myshop.model.bean.TopicBean;
import com.jiyun.myshop.model.bean.TopicDetailBean;
import com.jiyun.myshop.model.bean.TopicTJ;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyServer {
    @GET("index")
    Flowable<HomeBean> getHome();
    //制造商详情页商品列表
    @GET("goods/list")
    Flowable<BrandBean> getBrand(@Query("brandId") String brandId, @Query("page") String page, @Query("size") String size);

    //专题列表
    @GET("topic/list")
    Flowable<TopicBean> getTopic(@Query("page") int page,@Query("size") int size);
    //专题详情
    @GET("topic/detail")
    Flowable<TopicDetailBean> getTopicDetail(@Query("id") int id);
    //专题详情页评论数据
    @GET("comment/list")
    Flowable<CommentBean> getTopicComment(@Query("valueId") int valueId,@Query("typeId") int typeId,@Query("size") int size);
    //专题推荐
    @GET("topic/related")
    Flowable<TopicTJ> getTopicTJ(@Query("id") int id);

    //登录
    @POST("auth/login")
    @FormUrlEncoded
    Flowable<AuthBean> getAuth(@Field("nickname") String nickname,@Field("password") String password);

    //注册
    @POST("auth/register")
    @FormUrlEncoded
    Flowable<AuthBean> register(@Field("nickname") String nickname,@Field("password") String password);

    //分类
    @GET("catalog/index")
    Flowable<CatalogBean> getCatalog();

    //分类右边对应的数据
    @GET("catalog/current")
    Flowable<CatalogByIdBean> getCatalogById(@Query("id") String id);

    //商品详情列表数据
    @GET("goods/list")
    Flowable<CategoryBottom> getCategoryBottom(@Query("categoryId") int categoryId, @Query("page") int page, @Query("size") int size);

    //商品购买详情页
    @GET("goods/detail")
    Flowable<GoodInfoBean> getGoodInfo(@Query("id") int id);

    //商品购买详情页底部商品列表数据
    @GET("goods/related")
    Flowable<GoodInfoBo> getGoodInfoBo(@Query("id") int id);

    //加入到购物车
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<AddCartBean> addCart(@Field("goodsId") int goodsId, @Field("number") int number, @Field("productId") int productId);

    //获取购物车数据
    @GET("cart/index")
    Flowable<CartBean> getCartInfo();

}
