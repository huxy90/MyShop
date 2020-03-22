/*package com.jiyun.myshop.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.base.BaseFragment;
import com.jiyun.myshop.interfaces.home.HomeConstract;
import com.jiyun.myshop.model.bean.HomeBean;
import com.jiyun.myshop.presenter.home.HomePresenter;
import com.jiyun.myshop.ui.home.adapter.BrandAdapter;
import com.jiyun.myshop.ui.home.adapter.HomeAdapter;
import com.jiyun.myshop.ui.home.adapter.HotAdapter;
import com.jiyun.myshop.ui.home.adapter.NewGoodsAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class HomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {

    RecyclerView rl_View;
    List<HomeBean.HomeListBean> list;
    HomeAdapter homeAdapter;
//    private BrandAdapter bAdapter;
//    private NewGoodsAdapter nAdapter;
//    private HotAdapter hAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeConstract.Presenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        rl_View = (RecyclerView)getView().findViewById(R.id.rl_View);

        list = new ArrayList<>();
        rl_View.setLayoutManager(new GridLayoutManager(context,2));
        homeAdapter = new HomeAdapter(list,context);
        homeAdapter.bindToRecyclerView(rl_View);
        //监听计算当前条目占用的列表
        homeAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                int type = list.get(i).getItemType();
                switch (type){
                    //case HomeBean.HomeListBean.TYPE_BANNER:
                    case HomeBean.HomeListBean.TYPE_TITLE:
                    case HomeBean.HomeListBean.TYPE_CHANNEL:
                    case HomeBean.HomeListBean.TYPE_HOTGOOD:
                    case HomeBean.HomeListBean.TYPE_TOPIC:
                    case HomeBean.HomeListBean.TYPE_VIEW_LINE:
                        return 2;
                    case HomeBean.HomeListBean.TYPE_BRAND:
                    case HomeBean.HomeListBean.TYPE_NEWGOOD:
                    case HomeBean.HomeListBean.TYPE_CATEGORY:
                        return 1;
                }
                return 0;
            }
        });
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewType = adapter.getItemViewType(position);
                Toast.makeText(context,itemViewType+"---"+position,Toast.LENGTH_LONG).show();

                //banner    p: 0
                //channel:  p：1
                //brand:    p: 3--6  type: 2,3,3,3
                //newgood:  p: 8-11  8 --- 8 + newgood.size-1
                //hotgood:14-16  10 + newgood.size  -- 10 + newgood.size + hotgood.size-1
                //居家：  22-28  15 + newgood.size + hotgood.size -- 15 + newgood.size + hotgood.size + 居家.size - 1
                //

            }
        });

    }

    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    public void getHomeDataReturn(List<HomeBean.HomeListBean> result) {
        addHeader(result.remove(0));
        list.addAll(result);
        homeAdapter.notifyDataSetChanged();
    }

    private void addHeader(HomeBean.HomeListBean head){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_banner,null);
        Banner banner = view.findViewById(R.id.banner);
        List<String> imgs = new ArrayList<>();
        for (HomeBean.DataBean.BannerBean item : (List<HomeBean.DataBean.BannerBean>) head.data) {
            imgs.add(item.getImage_url());
        }
        banner.tag = "true";
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).dontAnimate().into(imageView);
            }
        });
        banner.setImages(imgs);
        banner.start();
        homeAdapter.addHeaderView(view);
    }
}
*/


  package com.jiyun.myshop.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.base.BaseFragment;
import com.jiyun.myshop.interfaces.home.HomeConstract;
import com.jiyun.myshop.model.bean.CatalogByIdBean;
import com.jiyun.myshop.model.bean.HomeBean;
import com.jiyun.myshop.presenter.home.HomePresenter;
import com.jiyun.myshop.ui.dashboard.TopicDetailActivity;
import com.jiyun.myshop.ui.home.adapter.BrandAdapter;
import com.jiyun.myshop.ui.home.adapter.ChannelAdapter;
import com.jiyun.myshop.ui.home.adapter.HomeAdapter;
import com.jiyun.myshop.ui.home.adapter.HotAdapter;
import com.jiyun.myshop.ui.home.adapter.NewGoodsAdapter;
import com.jiyun.myshop.ui.home.adapter.TopicAdapter;
import com.jiyun.myshop.ui.notifications.CategoryActivity;
import com.jiyun.myshop.ui.notifications.GoodInfoActivity;
import com.jiyun.myshop.utils.MaxRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class HomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {

    Banner banner;//轮播图
    MaxRecyclerView rl_channel;//
    MaxRecyclerView rl_brand;//品牌制造商
    MaxRecyclerView rl_newGooods;//新品
    MaxRecyclerView rl_hotGooods;//人气推荐
    MaxRecyclerView rl_topic;//专题
    MaxRecyclerView rl_category;//分类
    private ChannelAdapter cAdapter;
    private BrandAdapter bAdapter;
    private NewGoodsAdapter nAdapter;
    private HotAdapter hAdapter;
    private TopicAdapter tAdapter;
    private HomeAdapter cateAdapter;//分类
    List<HomeBean.HomeListBean> cateList;
    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeConstract.Presenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        banner = (Banner)getView().findViewById(R.id.banner);
        rl_channel = (MaxRecyclerView)getView().findViewById(R.id.rl_channel);
        rl_brand = (MaxRecyclerView)getView().findViewById(R.id.rl_brand);
        rl_newGooods = (MaxRecyclerView)getView().findViewById(R.id.rl_newGooods);
        rl_hotGooods = (MaxRecyclerView)getView().findViewById(R.id.rl_hotGooods);
        rl_topic = (MaxRecyclerView)getView().findViewById(R.id.rl_topic);
        rl_category = (MaxRecyclerView)getView().findViewById(R.id.rl_category);
        //解决卡顿问题
        rl_channel.setHasFixedSize(true);
        rl_channel.setNestedScrollingEnabled(false);
        rl_brand.setHasFixedSize(true);
        rl_brand.setNestedScrollingEnabled(false);
        rl_newGooods.setHasFixedSize(true);
        rl_newGooods.setNestedScrollingEnabled(false);
        rl_hotGooods.setHasFixedSize(true);
        rl_hotGooods.setNestedScrollingEnabled(false);
        rl_topic.setHasFixedSize(true);
        rl_topic.setNestedScrollingEnabled(false);
        rl_category.setHasFixedSize(true);
        rl_category.setNestedScrollingEnabled(false);

        //channel
        List<HomeBean.DataBean.ChannelBean> cList = new ArrayList<>();
        cAdapter = new ChannelAdapter(cList,context);
        rl_channel.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        rl_channel.setAdapter(cAdapter);
        //品牌制造
        List<HomeBean.DataBean.BrandListBean> bList = new ArrayList<>();
        bAdapter = new BrandAdapter(bList,context);
        rl_brand.setLayoutManager(new GridLayoutManager(context,2));
        rl_brand.setAdapter(bAdapter);
        //新品首发
        List<HomeBean.DataBean.NewGoodsListBean> nList = new ArrayList<>();
        nAdapter = new NewGoodsAdapter(nList,context);
        rl_newGooods.setLayoutManager(new GridLayoutManager(context,2));
        rl_newGooods.setAdapter(nAdapter);
        //人气
        List<HomeBean.DataBean.HotGoodsListBean> hList = new ArrayList<>();
        hAdapter = new HotAdapter(hList,context);
        rl_hotGooods.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        rl_hotGooods.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        rl_hotGooods.setAdapter(hAdapter);
        //专题
        List<HomeBean.DataBean.TopicListBean> tList = new ArrayList<>();
        tAdapter = new TopicAdapter(tList,context);
        rl_topic.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        rl_topic.setAdapter(tAdapter);
        //分类
        cateList = new ArrayList<>();
        cateAdapter = new HomeAdapter(cateList,context);
        rl_category.setLayoutManager(new GridLayoutManager(context,2));
        cateAdapter.bindToRecyclerView(rl_category);

        //监听计算当前条目占用的列表
        cateAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                int type = cateList.get(i).getItemType();
                switch (type){
                    case HomeBean.HomeListBean.TYPE_TITLE:
                    case HomeBean.HomeListBean.TYPE_VIEW_LINE:
                        return 2;
                    case HomeBean.HomeListBean.TYPE_CATEGORY:
                        return 1;
                }
                return 0;
            }
        });
        cateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeBean.HomeListBean bean = cateList.get(position);
                if(bean.currentType == HomeBean.HomeListBean.TYPE_CATEGORY){
                    //跳转到商品购买页
                    HomeBean.DataBean.CategoryListBean.GoodsListBean gBean = (HomeBean.DataBean.CategoryListBean.GoodsListBean)bean.data;
                    Intent intent = new Intent(context, GoodInfoActivity.class);
                    intent.putExtra("id",gBean.getId());
                    startActivity(intent);
                }
            }
        });
        //channel
        cAdapter.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter.VH vh, int position) {
//                Intent intent = new Intent(context, CategoryActivity.class);
//                intent.putExtra("cid",cList.get(position).getId());
//                intent.putExtra("position",position);
//                startActivity(intent);
            }
        });
        //品牌商
        bAdapter.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter.VH vh, int position) {
                Intent intent = new Intent(context,BrandActivity.class);
                intent.putExtra("bean",bList.get(position));
                startActivity(intent);
            }
        });
        //新品
        nAdapter.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter.VH vh, int position) {
                Intent intent = new Intent(context,GoodInfoActivity.class);
                intent.putExtra("id",nList.get(position).getId());
                startActivity(intent);
            }
        });
        //人气推荐
        hAdapter.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter.VH vh, int position) {
                Intent intent = new Intent(context,GoodInfoActivity.class);
                intent.putExtra("id",hList.get(position).getId());
                startActivity(intent);
            }
        });
        //专题
        tAdapter.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter.VH vh, int position) {
                Intent intent = new Intent(context, TopicDetailActivity.class);
                intent.putExtra("id",tList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    public void getHomeDataReturn(List<HomeBean.HomeListBean> result) {
        HomeBean bean = (HomeBean) result.get(0).dataBean;
        //轮播图
        banner.setImages(bean.getData().getBanner())
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        HomeBean.DataBean.BannerBean bean =  (HomeBean.DataBean.BannerBean)path;
                        Glide.with(context).load(bean.getImage_url()).into(imageView);
                    }
                }).start();
        //Channel
        cAdapter.updateMoreList(bean.getData().getChannel());
        //刷新品牌制作商列表
        bAdapter.updateMoreList(bean.getData().getBrandList());
        //刷新新品首发列表
        nAdapter.updateMoreList(bean.getData().getNewGoodsList());
        //人气推荐
        hAdapter.updateMoreList(bean.getData().getHotGoodsList());
        //专题
        tAdapter.updateMoreList(bean.getData().getTopicList());
        //分类
        cateList.addAll(result);
        cateAdapter.notifyDataSetChanged();
    }
}



