package com.jiyun.myshop.ui.notifications.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.model.bean.GoodInfoBo;

import java.util.List;

/**
 *
 */
public class GoodListAdapter extends BaseAdapter {
    public GoodListAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.item_search;
    }

    @Override
    protected void bindView(VH vh, Object data) {
        ImageView iv_item_search = (ImageView) vh.getViewById(R.id.iv_item_search);
        TextView tv_search_name = (TextView)vh.getViewById(R.id.tv_search_name);
        TextView tv_search_price = (TextView)vh.getViewById(R.id.tv_search_price);
        GoodInfoBo.DataBean.GoodsListBean bean = ( GoodInfoBo.DataBean.GoodsListBean) data;
        Glide.with(mContext).load(bean.getList_pic_url()).into(iv_item_search);
        tv_search_name.setText(bean.getName());
        tv_search_price.setText("¥"+bean.getRetail_price());
    }
}
