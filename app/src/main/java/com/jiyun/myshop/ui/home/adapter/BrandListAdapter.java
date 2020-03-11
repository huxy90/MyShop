package com.jiyun.myshop.ui.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.model.bean.BrandBean;
import com.jiyun.myshop.model.bean.CatalogBean;

import java.util.List;

public class BrandListAdapter extends BaseAdapter {

    public BrandListAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.brandlist_item;
    }

    @Override
    protected void bindView(VH vh, Object data) {
        ImageView iv = (ImageView) vh.getViewById(R.id.iv);
        TextView tv_name = (TextView)vh.getViewById(R.id.tv_name);
        TextView tv_price = (TextView)vh.getViewById(R.id.tv_price);
        BrandBean.DataBeanX.DataBean bean = (BrandBean.DataBeanX.DataBean) data;
        Glide.with(mContext).load(bean.getList_pic_url()).into(iv);
        tv_name.setText(bean.getName());
        tv_price.setText("¥"+bean.getRetail_price());
    }
}
