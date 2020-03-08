package com.jiyun.myshop.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
                Toast.makeText(context,position+"--",Toast.LENGTH_LONG).show();
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