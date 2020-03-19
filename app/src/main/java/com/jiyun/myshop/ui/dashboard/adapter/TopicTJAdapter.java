package com.jiyun.myshop.ui.dashboard.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.model.bean.CommentBean;
import com.jiyun.myshop.model.bean.TopicTJ;

import java.util.List;

/**
 * 推荐专题
 */
public class TopicTJAdapter extends BaseAdapter {

    public TopicTJAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.topictj_item;
    }

    @Override
    protected void bindView(VH vh, Object data) {
        ImageView iv = (ImageView) vh.getViewById(R.id.iv);
        TextView tv_title = (TextView)vh.getViewById(R.id.tv_title);
        TopicTJ.DataBean bean = (TopicTJ.DataBean ) data;
        Glide.with(mContext).load(bean.getScene_pic_url()).into(iv);
        tv_title.setText(bean.getTitle());
    }
}
