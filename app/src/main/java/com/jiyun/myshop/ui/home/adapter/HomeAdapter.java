package com.jiyun.myshop.ui.home.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiyun.myshop.R;
import com.jiyun.myshop.model.bean.HomeBean;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeBean.HomeListBean, BaseViewHolder> {

    List<HomeBean.HomeListBean> list;

    public HomeAdapter(List<HomeBean.HomeListBean> data) {
        super(data);
        this.list = data;
        addItemType(HomeBean.HomeListBean.TYPE_BANNER, R.layout.layout_item_banner);
        addItemType(HomeBean.HomeListBean.TYPE_CHANNEL,R.layout.layout_item_channel);
        addItemType(HomeBean.HomeListBean.TYPE_TITLE,R.layout.layout_item_title);
        addItemType(HomeBean.HomeListBean.TYPE_BRAND,R.layout.layout_item_brand);
        addItemType(HomeBean.HomeListBean.TYPE_NEWGOOD,R.layout.layout_item_newgood);
        addItemType(HomeBean.HomeListBean.TYPE_HOTGOOD,R.layout.layout_item_hotgood);
        addItemType(HomeBean.HomeListBean.TYPE_TOPIC,R.layout.layout_topic_recy);
        addItemType(HomeBean.HomeListBean.TYPE_CATEGORY,R.layout.layout_item_category);
        addItemType(HomeBean.HomeListBean.TYPE_VIEW_LINE,R.layout.layout_view_line);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.HomeListBean item) {

        switch (item.getItemType()){
            case HomeBean.HomeListBean.TYPE_CHANNEL:
                initChannel(helper,item);
                break;
            case HomeBean.HomeListBean.TYPE_BRAND:
                initBrand(helper,(HomeBean.DataBean.BrandListBean)item.data);
                break;
            case HomeBean.HomeListBean.TYPE_TITLE:
                initTitle(helper,item);
                break;
            case HomeBean.HomeListBean.TYPE_NEWGOOD:
                initNewGood(helper,(HomeBean.DataBean.NewGoodsListBean)item.data);
                break;
            case HomeBean.HomeListBean.TYPE_HOTGOOD:
                initHotGood(helper,(HomeBean.DataBean.HotGoodsListBean)item.data);
                break;
            case HomeBean.HomeListBean.TYPE_TOPIC:
                initTopic(helper,(List<HomeBean.DataBean.TopicListBean>)item.data);
                break;
            case HomeBean.HomeListBean.TYPE_CATEGORY:
                initCategory(helper,(HomeBean.DataBean.CategoryListBean.GoodsListBean)item.data);
                break;

        }
    }
    private void initChannel(BaseViewHolder helper, HomeBean.HomeListBean item) {
        RecyclerView rlView = helper.getView(R.id.recy_channel);
        if(rlView.getAdapter() == null){
            rlView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            ChannelAdapter adapter = new ChannelAdapter(R.layout.layout_item_channel,item);
            adapter.bindToRecyclerView(rlView);
        }
    }
    private void initBrand(BaseViewHolder helper, HomeBean.DataBean.BrandListBean data) {
        Glide.with(mContext).load(data.getList_pic_url()).into((ImageView)helper.getView(R.id.img_brand));
        helper.setText(R.id.txt_name,data.getName());
        helper.setText(R.id.txt_price,"￥"+data.getFloor_price());
    }
    private void initTitle(BaseViewHolder helper, HomeBean.HomeListBean item) {
        helper.setText(R.id.txt_title,item.title);
    }
    private void initNewGood(BaseViewHolder helper, HomeBean.DataBean.NewGoodsListBean data) {
        Glide.with(mContext).load(data.getList_pic_url()).into((ImageView)helper.getView(R.id.img_newGood));
        helper.setText(R.id.txt_name,data.getName());
        helper.setText(R.id.txt_price,"￥"+data.getRetail_price());
    }
    private void initHotGood(BaseViewHolder helper, HomeBean.DataBean.HotGoodsListBean data) {
        Glide.with(mContext).load(data.getList_pic_url()).into((ImageView)helper.getView(R.id.img_hot));
        helper.setText(R.id.txt_name,data.getName());
        helper.setText(R.id.txt_des,data.getGoods_brief());
        helper.setText(R.id.txt_price,"￥"+data.getRetail_price());
    }
    private void initTopic(BaseViewHolder helper, List<HomeBean.DataBean.TopicListBean> data) {
        RecyclerView rlView = helper.getView(R.id.recy_topic);
        if(rlView.getAdapter() == null){
            rlView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            TopicAdapter adapter = new TopicAdapter(R.layout.layout_item_topic,data);
            adapter.bindToRecyclerView(rlView);
        }
    }
    private void initCategory(BaseViewHolder helper, HomeBean.DataBean.CategoryListBean.GoodsListBean data) {
        Glide.with(mContext).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.img_category));
        helper.setText(R.id.txt_name,data.getName());
        helper.setText(R.id.txt_price,"￥"+data.getRetail_price());
    }
}
