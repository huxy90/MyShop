package com.jiyun.myshop.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.base.BaseFragment;
import com.jiyun.myshop.interfaces.home.HomeConstract;
import com.jiyun.myshop.model.bean.HomeBean;
import com.jiyun.myshop.presenter.home.HomePresenter;
import com.jiyun.myshop.ui.dashboard.TopicDetailActivity;
import com.jiyun.myshop.ui.home.adapter.BrandAdapter;
import com.jiyun.myshop.ui.home.adapter.ChannelAdapter;
import com.jiyun.myshop.ui.home.adapter.HomeAdapter;
import com.jiyun.myshop.ui.home.adapter.HotAdapter;
import com.jiyun.myshop.ui.home.adapter.NewGoodsAdapter;
import com.jiyun.myshop.ui.home.adapter.TopicAdapter;
import com.jiyun.myshop.ui.notifications.GoodInfoActivity;
import com.jiyun.myshop.ui.notifications.SearchActivity;
import com.jiyun.myshop.utils.MaxRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {

    LinearLayout ll_search;//搜索
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
        ll_search = getView().findViewById(R.id.ll_search);
        banner = (Banner)getView().findViewById(R.id.banner);
        rl_channel = (MaxRecyclerView)getView().findViewById(R.id.rl_channel);
        rl_brand = (MaxRecyclerView)getView().findViewById(R.id.rl_brand);
        rl_newGooods = (MaxRecyclerView)getView().findViewById(R.id.rl_newGooods);
        rl_hotGooods = (MaxRecyclerView)getView().findViewById(R.id.rl_hotGooods);
        rl_topic = (MaxRecyclerView)getView().findViewById(R.id.rl_topic);
        rl_category = (MaxRecyclerView)getView().findViewById(R.id.rl_category);
        //解决卡顿问题
        initRyView();
        //点击搜索框跳转到搜索页面
        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

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

    private void initRyView() {
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



