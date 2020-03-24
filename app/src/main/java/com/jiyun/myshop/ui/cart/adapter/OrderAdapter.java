package com.jiyun.myshop.ui.cart.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.model.bean.CartBean;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends BaseAdapter<CartBean.DataBean.CartListBean> {

    public OrderAdapter(List<CartBean.DataBean.CartListBean> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_item_order;
    }

    @Override
    protected void bindView(VH vh, CartBean.DataBean.CartListBean data) {
        ImageView iv = (ImageView) vh.getViewById(R.id.iv);
        TextView tv_name = (TextView) vh.getViewById(R.id.tv_name);
        TextView tv_num = (TextView) vh.getViewById(R.id.tv_num);
        TextView tv_price = (TextView) vh.getViewById(R.id.tv_price);
        Glide.with(mContext).load(data.getList_pic_url()).into(iv);
        tv_name.setText(data.getGoods_name());
        tv_price.setText("¥" + data.getRetail_price());
        tv_num.setText("X" + data.getNumber());
    }

}
