package com.jiyun.myshop.ui.dashboard.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.model.bean.CatalogBean;
import com.jiyun.myshop.model.bean.CommentBean;
import com.jiyun.myshop.model.bean.TopicDetailBean;

import java.util.List;

/**
 * 评论
 */
public class CommentAdapter extends BaseAdapter {

    public CommentAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.comment_item;
    }

    @Override
    protected void bindView(VH vh, Object data) {
        ImageView iv = (ImageView) vh.getViewById(R.id.iv);
        TextView tv_name = (TextView)vh.getViewById(R.id.tv_name);
        TextView tv_date = (TextView)vh.getViewById(R.id.tv_date);
        TextView tv_desc = (TextView)vh.getViewById(R.id.tv_desc);
        CommentBean.DataBeanX.DataBean bean = (CommentBean.DataBeanX.DataBean) data;
        Glide.with(mContext).load(bean.getUser_info().getAvatar()).into(iv);
        tv_name.setText(bean.getUser_info().getNickname());
        tv_date.setText(bean.getAdd_time());
        tv_desc.setText(bean.getContent());
    }
}
