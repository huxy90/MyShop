package com.jiyun.myshop.ui.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.model.bean.HomeBean;

import java.util.List;

public class BrandAdapter extends BaseAdapter {
    public BrandAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.brand_item;
    }

    @Override
    protected void bindView(BaseAdapter.VH vh, Object data) {
        HomeBean.DataBean.BrandListBean bean = (HomeBean.DataBean.BrandListBean)data;
        ImageView iv = (ImageView) vh.getViewById(R.id.iv);
        TextView tv_name = (TextView)vh.getViewById(R.id.tv_name);
        TextView tv_price = (TextView)vh.getViewById(R.id.tv_price);
        tv_name.setText(bean.getName());
        tv_price.setText(bean.getFloor_price()+"元起");
        Glide.with(mContext).load(bean.getNew_pic_url()).into(iv);
    }
}
