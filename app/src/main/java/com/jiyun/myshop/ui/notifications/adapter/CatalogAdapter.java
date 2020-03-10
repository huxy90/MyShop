package com.jiyun.myshop.ui.notifications.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.model.bean.CatalogBean;

import java.util.List;

public class CatalogAdapter extends BaseAdapter {

    public CatalogAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.catalog_item;
    }

    @Override
    protected void bindView(VH vh, Object data) {
        ImageView iv = (ImageView) vh.getViewById(R.id.iv);
        TextView tv_name = (TextView)vh.getViewById(R.id.tv_name);
        CatalogBean.DataBean.CurrentCategoryBean.SubCategoryListBean bean = (CatalogBean.DataBean.CurrentCategoryBean.SubCategoryListBean) data;
        Glide.with(mContext).load(bean.getWap_banner_url()).into(iv);
        tv_name.setText(bean.getName());
    }
}
