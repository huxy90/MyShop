package com.jiyun.myshop.ui.cart.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.model.bean.CartBean;

import java.util.List;

public class CartAdapter extends BaseAdapter<CartBean.DataBean.CartListBean> {
    public CartAdapter(List<CartBean.DataBean.CartListBean> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_item_cart;
    }

    @Override
    protected void bindView(VH vh, CartBean.DataBean.CartListBean data) {
        ImageView car_item_iv = (ImageView) vh.getViewById(R.id.car_item_iv);
        TextView car_tv_name = (TextView) vh.getViewById(R.id.car_tv_name);
        TextView car_tv_price = (TextView) vh.getViewById(R.id.car_tv_price);
        TextView car_item_count = (TextView) vh.getViewById(R.id.car_item_count);
        Glide.with(mContext).load(data.getList_pic_url()).into(car_item_iv);
        car_tv_name.setText(data.getGoods_name());
        car_tv_price.setText("¥"+data.getRetail_price());
        car_item_count.setText("X"+data.getNumber());
    }
}
