package com.jiyun.myshop.ui.home;

import android.content.Context;
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


 /*package com.jiyun.myshop.ui.home;

import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.base.BaseFragment;
import com.jiyun.myshop.interfaces.home.HomeConstract;
import com.jiyun.myshop.model.bean.HomeBean;
import com.jiyun.myshop.presenter.home.HomePresenter;
import com.jiyun.myshop.ui.home.adapter.BrandAdapter;
import com.jiyun.myshop.ui.home.adapter.GridHelperAdapter;
import com.jiyun.myshop.ui.home.adapter.HotAdapter;
import com.jiyun.myshop.ui.home.adapter.NewGoodsAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {

    RecyclerView rl_view;
    private BrandAdapter bAdapter;
    private NewGoodsAdapter nAdapter;
    private HotAdapter hAdapter;
    private GridHelperAdapter gAdapter;

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
        rl_view = (RecyclerView) getView().findViewById(R.id.rl_View);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        rl_view.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        //设置布局管理器
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(context);
        rl_view.setLayoutManager(layoutManager);
        DelegateAdapter adapters = new DelegateAdapter(layoutManager, true);
        GridLayoutHelper gridHelper = new GridLayoutHelper(2);
        gridHelper.setMarginTop(30);
        //设置垂直方向条目的间隔
        gridHelper.setVGap(2);
        //设置水平方向条目的间隔
        gridHelper.setHGap(2);
        //自动填充满布局
        gridHelper.setAutoExpand(true);
        List<HomeBean.DataBean.BrandListBean> bList = new ArrayList<>();
        gAdapter = new GridHelperAdapter(bList, gridHelper,context);
        adapters.addAdapter(gAdapter);
        rl_view.setAdapter(adapters);

//        bAdapter.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseAdapter.VH vh, int position) {
//                Toast.makeText(context, position + "--", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    public void getHomeDataReturn(HomeBean result) {
        gAdapter.updateList(result.getData().getBrandList());
        //刷新品牌制作商列表
//        bAdapter.updateMoreList(result.getData().getBrandList());
//        //刷新新品首发列表
//        nAdapter.updateList(result.getData().getNewGoodsList());
//        hAdapter.updateList(result.getData().getHotGoodsList());
    }
}*/