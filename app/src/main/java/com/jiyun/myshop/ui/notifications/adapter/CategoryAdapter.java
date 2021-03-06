package com.jiyun.myshop.ui.notifications.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.model.bean.CatalogBean;
import com.jiyun.myshop.model.bean.CategoryBottom;

import java.util.List;

/**
 * 分类详情
 */
public class CategoryAdapter extends BaseAdapter {
    public CategoryAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_item_category;
    }

    @Override
    protected void bindView(VH vh, Object data) {
        ImageView img_category = (ImageView) vh.getViewById(R.id.img_category);
        TextView txt_name = (TextView)vh.getViewById(R.id.txt_name);
        TextView txt_price = (TextView)vh.getViewById(R.id.txt_price);
        CategoryBottom.DataBeanX.DataBean bean = (CategoryBottom.DataBeanX.DataBean) data;
        Glide.with(mContext).load(bean.getList_pic_url()).into(img_category);
        txt_name.setText(bean.getName());
        txt_price.setText("¥"+bean.getRetail_price());
    }
}
