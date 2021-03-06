package com.jiyun.myshop.presenter.home;

import android.util.Log;

import com.jiyun.myshop.base.BasePresenter;
import com.jiyun.myshop.common.ResponseSubscriber;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.interfaces.home.HomeConstract;
import com.jiyun.myshop.model.HttpManager;
import com.jiyun.myshop.model.bean.HomeBean;
import com.jiyun.myshop.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;


public class HomePresenter extends BasePresenter<HomeConstract.View> implements HomeConstract.Presenter{

    @Override
    public void getHomeData() {
        addSubcribe(HttpManager.getInstance().myServer().getHome()
                .compose(RxUtils.<HomeBean>rxScheduler())
                .map(new Function<HomeBean, List<HomeBean.HomeListBean>>() {
                    @Override
                    public List<HomeBean.HomeListBean> apply(HomeBean bean) throws Exception {
                        //把服务器返回的数据转换为列表数据
                        List<HomeBean.HomeListBean> list = new ArrayList<>();
                        //banner
//                        HomeBean.HomeListBean banner = new HomeBean.HomeListBean();
//                        banner.currentType = HomeBean.HomeListBean.TYPE_BANNER;
//                        banner.data =  bean.getData().getBanner();
//                        banner.dataBean = bean;//首页channel、品牌等展示用数据
//                        list.add(banner);
//                        //channel
//                        HomeBean.HomeListBean channel = new HomeBean.HomeListBean();
//                        channel.currentType = HomeBean.HomeListBean.TYPE_CHANNEL;
//                        channel.data = bean.getData().getChannel();
//                        list.add(channel);
//                        //分割线
//                        HomeBean.HomeListBean lineBrand = new HomeBean.HomeListBean();
//                        lineBrand.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
//                        list.add(lineBrand);
//                        //title
//                        HomeBean.HomeListBean titleBrand = new HomeBean.HomeListBean();
//                        titleBrand.currentType = HomeBean.HomeListBean.TYPE_TITLE;
//                        titleBrand.title = "品牌商直供";
//                        list.add(titleBrand);
//                        //brand
//                        for(HomeBean.DataBean.BrandListBean item : bean.getData().getBrandList()){
//                            HomeBean.HomeListBean brand = new HomeBean.HomeListBean();
//                            brand.currentType = HomeBean.HomeListBean.TYPE_BRAND;
//                            brand.data = item;
//                            list.add(brand);
//                        }
//                        //newgood title
//                        HomeBean.HomeListBean newGoodTitle = new HomeBean.HomeListBean();
//                        newGoodTitle.currentType = HomeBean.HomeListBean.TYPE_TITLE;
//                        newGoodTitle.title = "周一周四 · 新品首发";
//                        list.add(newGoodTitle);
//                        //newgood
//                        for(HomeBean.DataBean.NewGoodsListBean item : bean.getData().getNewGoodsList()){
//                            HomeBean.HomeListBean newGood = new HomeBean.HomeListBean();
//                            newGood.currentType = HomeBean.HomeListBean.TYPE_NEWGOOD;
//                            newGood.data = item;
//                            list.add(newGood);
//                        }
//                        //分割线
//                        HomeBean.HomeListBean lineHot = new HomeBean.HomeListBean();
//                        lineHot.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
//                        list.add(lineHot);
//                        //hottitle
//                        HomeBean.HomeListBean hotTitle = new HomeBean.HomeListBean();
//                        hotTitle.currentType = HomeBean.HomeListBean.TYPE_TITLE;
//                        hotTitle.title = "人气推荐";
//                        list.add(hotTitle);
//                        //hot
//                        for(HomeBean.DataBean.HotGoodsListBean item : bean.getData().getHotGoodsList()){
//                            HomeBean.HomeListBean hotGood = new HomeBean.HomeListBean();
//                            hotGood.currentType = HomeBean.HomeListBean.TYPE_HOTGOOD;
//                            hotGood.data = item;
//                            list.add(hotGood);
//                        }
//                        //分割线
//                        HomeBean.HomeListBean lineTopic = new HomeBean.HomeListBean();
//                        lineTopic.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
//                        list.add(lineTopic);
//                        //topTitle
//                        HomeBean.HomeListBean topicTitle = new HomeBean.HomeListBean();
//                        topicTitle.currentType = HomeBean.HomeListBean.TYPE_TITLE;
//                        topicTitle.title = "专题精选";
//                        list.add(topicTitle);
//                        //topic
//                        HomeBean.HomeListBean topic = new HomeBean.HomeListBean();
//                        topic.currentType = HomeBean.HomeListBean.TYPE_TOPIC;
//                        topic.data = bean.getData().getTopicList();
//                        list.add(topic);
//                        //分割线
//                        HomeBean.HomeListBean lineCategory = new HomeBean.HomeListBean();
//                        lineCategory.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
//                        list.add(lineCategory);
                        //category
                        for(HomeBean.DataBean.CategoryListBean item : bean.getData().getCategoryList()){
                            HomeBean.HomeListBean category = new HomeBean.HomeListBean();
                            category.currentType = HomeBean.HomeListBean.TYPE_TITLE;
                            category.title = item.getName();
                            category.dataBean = bean;//首页channel、品牌等展示用数据
                            list.add(category);
                            for(HomeBean.DataBean.CategoryListBean.GoodsListBean data : item.getGoodsList()){
                                HomeBean.HomeListBean good = new HomeBean.HomeListBean();
                                good.currentType = HomeBean.HomeListBean.TYPE_CATEGORY;
                                good.data = data;
                                list.add(good);
                            }
                            //分割线
                            HomeBean.HomeListBean line = new HomeBean.HomeListBean();
                            line.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
                            list.add(line);
                        }
                        return list;
                    }
                })
                .subscribeWith(new ResponseSubscriber<List<HomeBean.HomeListBean>>(mView){
                    @Override
                    public void onNext(List<HomeBean.HomeListBean> result) {
                        mView.getHomeDataReturn(result);
                    }
                }));
    }


}
