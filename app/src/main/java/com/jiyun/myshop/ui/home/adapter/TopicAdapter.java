package com.jiyun.myshop.ui.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.model.bean.HomeBean;

import java.util.List;

import androidx.annotation.Nullable;
public class TopicAdapter extends BaseAdapter {

    public TopicAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_item_topic;
    }

    @Override
    protected void bindView(VH vh, Object data) {
        ImageView img_topic = (ImageView) vh.getViewById(R.id.img_topic);
        TextView txt_name = (TextView) vh.getViewById(R.id.txt_name);
        TextView txt_price = (TextView) vh.getViewById(R.id.txt_price);
        TextView txt_des = (TextView) vh.getViewById(R.id.txt_des);
        HomeBean.DataBean.TopicListBean bean = (HomeBean.DataBean.TopicListBean)data;
        txt_name.setText(bean.getTitle());
        txt_price.setText("¥"+bean.getPrice_info());
        txt_des.setText(bean.getSubtitle());
        Glide.with(mContext).load(bean.getItem_pic_url()).into(img_topic);
    }
}

//public class TopicAdapter extends BaseQuickAdapter<HomeBean.DataBean.TopicListBean, BaseViewHolder> {
//    public TopicAdapter(int layoutResId, @Nullable List<HomeBean.DataBean.TopicListBean> data) {
//        super(layoutResId, data);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, HomeBean.DataBean.TopicListBean item) {
//        Glide.with(mContext).load(item.getItem_pic_url()).into((ImageView) helper.getView(R.id.img_topic));
//        helper.setText(R.id.txt_name,item.getTitle());
//        helper.setText(R.id.txt_price,"￥"+item.getPrice_info());
//        helper.setText(R.id.txt_des,item.getSubtitle());
//    }
//}
