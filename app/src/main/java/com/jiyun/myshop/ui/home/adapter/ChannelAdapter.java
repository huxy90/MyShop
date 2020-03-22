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
public class ChannelAdapter extends BaseAdapter {
    public ChannelAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_item_channel;
    }

    @Override
    protected void bindView(VH vh, Object data) {
        HomeBean.DataBean.ChannelBean bean = (HomeBean.DataBean.ChannelBean)data;
        TextView txt_name = (TextView)vh.getViewById(R.id.txt_name);
        txt_name.setText(bean.getName());
    }
}
//public class ChannelAdapter extends BaseQuickAdapter<HomeBean.DataBean.ChannelBean, BaseViewHolder> {
//    public ChannelAdapter(int layoutResId, @Nullable List<HomeBean.DataBean.ChannelBean> data) {
//        super(layoutResId, data);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, HomeBean.DataBean.ChannelBean item) {
//        helper.setText(R.id.txt_name,item.getName());
//    }
//}
