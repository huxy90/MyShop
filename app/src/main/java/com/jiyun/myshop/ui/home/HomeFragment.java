package com.jiyun.myshop.ui.home;

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
        homeAdapter = new HomeAdapter(list);
        homeAdapter.bindToRecyclerView(rl_View);
        //监听计算当前条目占用的列表
        homeAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                int type = list.get(i).getItemType();
                switch (type){
                    case HomeBean.HomeListBean.TYPE_BANNER:
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


 /*
  package com.jiyun.myshop.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.base.BaseFragment;
import com.jiyun.myshop.interfaces.home.HomeConstract;
import com.jiyun.myshop.model.bean.HomeBean;
import com.jiyun.myshop.presenter.home.HomePresenter;
import com.jiyun.myshop.ui.home.adapter.BrandAdapter;
import com.jiyun.myshop.ui.home.adapter.HotAdapter;
import com.jiyun.myshop.ui.home.adapter.NewGoodsAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class HomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {

    Banner banner;//轮播图
    RecyclerView rl_channel;//
    RecyclerView rl_brand;//品牌制造商
    RecyclerView rl_newGooods;//新品
    RecyclerView rl_hotGooods;//人气推荐
    private BrandAdapter bAdapter;
    private NewGoodsAdapter nAdapter;
    private HotAdapter hAdapter;

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
        rl_channel = (RecyclerView)getView().findViewById(R.id.rl_channel);
        rl_brand = (RecyclerView)getView().findViewById(R.id.rl_brand);
        rl_newGooods = (RecyclerView)getView().findViewById(R.id.rl_newGooods);
        rl_hotGooods = (RecyclerView)getView().findViewById(R.id.rl_hotGooods);

        //品牌制造
        List<HomeBean.DataBean.BrandListBean> bList = new ArrayList<>();
        bAdapter = new BrandAdapter(bList,context);
        rl_brand.setLayoutManager(new GridLayoutManager(context,2));
        rl_brand.setAdapter(bAdapter);
        //新品首发
        List<HomeBean.DataBean.BrandListBean> nList = new ArrayList<>();
        nAdapter = new NewGoodsAdapter(nList,context);
        rl_newGooods.setLayoutManager(new GridLayoutManager(context,2));
        rl_newGooods.setAdapter(nAdapter);
        //人气
        List<HomeBean.DataBean.HotGoodsListBean> hList = new ArrayList<>();
        hAdapter = new HotAdapter(hList,context);
        rl_hotGooods.setAdapter(hAdapter);

        bAdapter.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter.VH vh, int position) {
                Intent intent = new Intent(context,BrandActivity.class);
                intent.putExtra("bean",bList.get(position));
                startActivity(intent);
                //Toast.makeText(context,position+"--",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    public void getHomeDataReturn(HomeBean result) {
        //轮播图
        banner.setImages(result.getData().getBanner())
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        HomeBean.DataBean.BannerBean bean =  (HomeBean.DataBean.BannerBean)path;
                        Log.e("TAG","banner:-----------"+path);
                        Glide.with(context).load(bean.getImage_url()).into(imageView);
                    }
                }).start();
        //刷新品牌制作商列表
        bAdapter.updateMoreList(result.getData().getBrandList());
        //刷新新品首发列表
        nAdapter.updateList(result.getData().getNewGoodsList());
        hAdapter.updateList(result.getData().getHotGoodsList());
    }
}



 */
