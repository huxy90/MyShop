package com.jiyun.myshop.ui.home.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiyun.myshop.R;
import com.jiyun.myshop.model.bean.HomeBean;

import java.util.List;

import androidx.annotation.Nullable;

public class ChannelAdapter extends BaseQuickAdapter<HomeBean.DataBean.ChannelBean, BaseViewHolder> {
    public ChannelAdapter(int layoutResId, @Nullable List<HomeBean.DataBean.ChannelBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataBean.ChannelBean item) {
        helper.setText(R.id.txt_name,item.getName());
    }
}
