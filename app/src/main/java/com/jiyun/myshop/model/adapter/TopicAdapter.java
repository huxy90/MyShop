package com.jiyun.myshop.model.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.model.bean.TopicBean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class TopicAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<TopicBean.DataBeanX.DataBean> list = new ArrayList<>();

    public TopicAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,null);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;
        Log.i("TAG","title ----------"+list.get(position).getTitle());
        vh.title.setText(list.get(position).getTitle());
        vh.subtitle.setText(list.get(position).getSubtitle());
        vh.price_info.setText(list.get(position).getPrice_info()+"元起");
        Glide.with(context).load(list.get(position).getScene_pic_url()).into(vh.iv);
    }

    @Override
    public int getItemCount() {
        Log.i("TAG","getItemCount---------------");
        return list.size();
    }

    public void addData(List<TopicBean.DataBeanX.DataBean> result){
        Log.i("TAG","addData---------------");
        list.clear();
        list.addAll(result);
        notifyDataSetChanged();
    }

    class VH extends RecyclerView.ViewHolder{
        //@BindView(R.id.iv)
        ImageView iv;
       // @BindView(R.id.title)
        TextView title;
       // @BindView(R.id.subtitle)
        TextView subtitle;
        //@BindView(R.id.price_info)
        TextView price_info;

        public VH(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            price_info = itemView.findViewById(R.id.price_info);
        }
    }
}
