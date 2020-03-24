package com.jiyun.myshop.ui.cart.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.model.bean.CartBean;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends BaseAdapter<CartBean.DataBean.CartListBean> {
    List<CartBean.DataBean.CartListBean> list = new ArrayList<>();
    List<CartBean.DataBean.CartListBean> getAll = new ArrayList<>();//记录选中的条目
    private String state = "编辑";

    public CartAdapter(List<CartBean.DataBean.CartListBean> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public int getLayout() {
        return R.layout.layout_item_cart;
    }

    @Override
    protected void bindView(VH vh, CartBean.DataBean.CartListBean data) {
        ImageView car_item_iv = (ImageView) vh.getViewById(R.id.car_item_iv);
        TextView car_tv_name = (TextView) vh.getViewById(R.id.car_tv_name);
        TextView car_tv_price = (TextView) vh.getViewById(R.id.car_tv_price);
        TextView car_item_count = (TextView) vh.getViewById(R.id.car_item_count);
        Glide.with(mContext).load(data.getList_pic_url()).into(car_item_iv);
        car_tv_name.setText(data.getGoods_name());
        car_tv_price.setText("¥"+data.getRetail_price());
        car_item_count.setText("X"+data.getNumber());

        LinearLayout ll_control_add = (LinearLayout)vh.getViewById(R.id.ll_control_add);//-  xx  +
        TextView car_item_chose = (TextView)vh.getViewById(R.id.car_item_chose);//已选择
        CheckBox car_item_cb = (CheckBox)vh.getViewById(R.id.car_item_cb);
        TextView car_item_add_count = (TextView)vh.getViewById(R.id.car_item_add_count);//+
        TextView car_item_show_count = (TextView)vh.getViewById(R.id.car_item_show_count);//数量
        TextView car_item_reduce_count = (TextView)vh.getViewById(R.id.car_item_reduce_count);//-


        if("编辑".equals(state)){
             ll_control_add.setVisibility(View.INVISIBLE);
             car_item_chose.setVisibility(View.INVISIBLE);
             car_tv_name.setVisibility(View.VISIBLE);
             car_item_count.setVisibility(View.VISIBLE);
        }else {
             ll_control_add.setVisibility(View.VISIBLE);
             car_item_chose.setVisibility(View.VISIBLE);
             car_tv_name.setVisibility(View.INVISIBLE);
             car_item_count.setVisibility(View.INVISIBLE);
        }
        //getAll选择的集合
        if(getAll.contains(data)){
            car_item_cb.setChecked(true);
        }else {
            car_item_cb.setChecked(false);
        }

        //+   数量加1
        car_item_add_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(car_item_show_count.getText().toString());
                i++;
                car_item_show_count.setText(i+"");
                data.setNumber(i);
            }
        });
        //-
        car_item_reduce_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(car_item_show_count.getText().toString());
                i--;
                if(i >= 1){
                    car_item_show_count.setText(i+"");
                    data.setNumber(i);
                }
            }
        });
        //选中
        car_item_cb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(car_item_cb.isChecked()){
                    getAll.add(data);
                }else {
                    getAll.remove(data);
                }
                if(onCbClick!=null){
                    onCbClick.cbClick();
                }
            }
        });
    }

    //改变条目状态值    编辑  完成
    public void setItemVisibility(String state){
        this.state = state;
        notifyDataSetChanged();
    }

    //全选  全不选
    public void  getAll(boolean checked){
        getAll.clear();
        if(checked){
            getAll.addAll(mDatas);
        }
        notifyDataSetChanged();
    }
    //返回被选中的条目集合
    public List<CartBean.DataBean.CartListBean> getAll(){
        return getAll;
    }

    public OnCbClick onCbClick;
    public void setOnCbClick(OnCbClick onCbClick){
        this.onCbClick = onCbClick;
    }
    public interface OnCbClick{
          void cbClick();
    }
}
