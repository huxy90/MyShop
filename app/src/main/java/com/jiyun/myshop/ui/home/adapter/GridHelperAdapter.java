package com.jiyun.myshop.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.model.bean.HomeBean;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Linsa on 2018/1/2:11:21.
 * des: 创建相关LayoutHelper的使用
 */

public class GridHelperAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    private LayoutHelper mHelper;
    private List<HomeBean.DataBean.BrandListBean> mData;
    private Context context;

    public GridHelperAdapter(List<HomeBean.DataBean.BrandListBean> mData, LayoutHelper helper,Context context) {
        this.mData = mData;
        this.mHelper=helper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.brand_item, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerViewItemHolder recyclerViewHolder = (RecyclerViewItemHolder) holder;
        Glide.with(context).load(mData.get(position).getNew_pic_url()).into(recyclerViewHolder.iv_icon);
//        recyclerViewHolder.iv_icon.setBackgroundResource(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 正常条目的item的ViewHolder
     */
    private class RecyclerViewItemHolder extends RecyclerView.ViewHolder {

        public ImageView iv_icon;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv);
        }
    }

    //整体列表的刷新
    public void updateList(List<HomeBean.DataBean.BrandListBean> list){
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }
    //在列表的尾部添加数据
    public void updateMoreList(List<HomeBean.DataBean.BrandListBean> list){
        mData.addAll(list);
        notifyDataSetChanged();
    }
}
