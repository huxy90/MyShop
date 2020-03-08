package com.jiyun.myshop.ui.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.model.bean.HomeBean;

import java.util.List;

public class HotAdapter extends BaseAdapter {
    public HotAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.hot_item;
    }

    @Override
    protected void bindView(VH vh, Object data) {
        HomeBean.DataBean.HotGoodsListBean bean = (HomeBean.DataBean.HotGoodsListBean)data;
        ImageView iv = (ImageView) vh.getViewById(R.id.iv);
        TextView tv_name = (TextView)vh.getViewById(R.id.tv_name);
        TextView tv_brief = (TextView)vh.getViewById(R.id.tv_brief);
        TextView tv_price = (TextView)vh.getViewById(R.id.tv_price);
        tv_name.setText(bean.getName());
        tv_brief.setText(bean.getGoods_brief());
        tv_price.setText("¥"+bean.getRetail_price());
        Glide.with(mContext).load(bean.getList_pic_url()).into(iv);
    }
}
