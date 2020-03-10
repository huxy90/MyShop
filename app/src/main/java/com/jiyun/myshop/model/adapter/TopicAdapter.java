package com.jiyun.myshop.model.adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.model.bean.TopicBean;
import java.util.List;

public class TopicAdapter extends BaseAdapter {


    public TopicAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.item;
    }

    @Override
    protected void bindView(VH vh, Object data) {
        TopicBean.DataBeanX.DataBean bean = (TopicBean.DataBeanX.DataBean)data;
        ImageView iv = (ImageView)vh.getViewById(R.id.iv);
        TextView title = (TextView)vh.getViewById(R.id.title);
        TextView subtitle = (TextView)vh.getViewById(R.id.subtitle);
        TextView price_info = (TextView)vh.getViewById(R.id.price_info);
        title.setText(bean.getTitle());
        subtitle.setText(bean.getSubtitle());
        price_info.setText(bean.getPrice_info()+"元起");
        Glide.with(mContext).load(bean.getScene_pic_url()).into(iv);
    }
}
/*
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
*/