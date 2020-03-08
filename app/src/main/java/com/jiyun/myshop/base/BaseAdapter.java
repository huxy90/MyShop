package com.jiyun.myshop.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {

    private List<T> mDatas;
    protected Context mContext;
    private OnItemClickListener clickListener;

    public BaseAdapter(List<T> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    //创建item 的view并绑定到ViewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(getLayout(),null);
        VH vh = new VH(view);
        //item的单击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickListener.onItemClick(vh,vh.getLayoutPosition());
            }
        });
        return vh;
    }

    //获取item条目的抽象方法
    public abstract int getLayout();
    protected abstract void bindView(VH vh, T data);

    //绑定数据到item
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
           VH vh = (VH)holder;
           T data = mDatas.get(position);
           bindView(vh,data);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class VH extends RecyclerView.ViewHolder{

        SparseArray array;

        public VH(@NonNull View itemView) {
            super(itemView);
            array = new SparseArray();
        }

        public View getViewById(int id){
            View view = (View)array.get(id);
            if(view == null){
                view = itemView.findViewById(id);
            }
            return view;
        }
    }

    /**
     *接口回调的定义
     */
    public interface OnItemClickListener{
        void onItemClick(VH vh,int position);
    }

    /**
     * 添加接口回调
     * @param clickListener
     */
    public void addOnItemClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    //整体列表的刷新
    public void updateList(List<T> list){
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }
    //在列表的尾部添加数据
    public void updateMoreList(List<T> list){
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

}
