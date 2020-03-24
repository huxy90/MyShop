package com.jiyun.myshop.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseFragment;
import com.jiyun.myshop.interfaces.cart.CartConstract;
import com.jiyun.myshop.model.bean.CartBean;
import com.jiyun.myshop.model.bean.DelCarInfo;
import com.jiyun.myshop.presenter.cart.CartPresenter;
import com.jiyun.myshop.ui.cart.adapter.CartAdapter;
import com.jiyun.myshop.ui.notifications.NotificationsViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartFragment extends BaseFragment<CartConstract.Presenter> implements CartConstract.View, View.OnClickListener {

    TextView car_tv_count_price;//总价
    CheckBox car_cb_all;//全部
    Button car_btn_car_press;//下单
    TextView car_tv_edit_state;//编辑

    RecyclerView rl_View;
    CartAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_cart;
    }

    @Override
    protected CartConstract.Presenter createPresenter() {
        return new CartPresenter();
    }

    @Override
    protected void initView() {
        car_tv_count_price = (TextView)getView().findViewById(R.id.car_tv_count_price);
        car_cb_all = (CheckBox)getView().findViewById(R.id.car_cb_all);
        car_btn_car_press = (Button)getView().findViewById(R.id.car_btn_car_press);
        car_tv_edit_state = (TextView)getView().findViewById(R.id.car_tv_edit_state);

        rl_View = getView().findViewById(R.id.rl_View);
        List<CartBean.DataBean.CartListBean> list = new ArrayList<>();
        adapter = new CartAdapter(list,context);
        rl_View.setLayoutManager(new LinearLayoutManager(context));
        rl_View.setAdapter(adapter);
        adapter.setOnCbClick(new CartAdapter.OnCbClick() {
            @Override
            public void cbClick() {
                setAllPrice();
            }
        });

        car_btn_car_press.setOnClickListener(this);
        car_cb_all.setOnClickListener(this);
        car_tv_edit_state.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        presenter.getCart();
    }

    @Override
    public void getCartReturn(CartBean bean) {
        adapter.updateMoreList(bean.getData().getCartList());
    }

    @Override
    public void delCartReturn(DelCarInfo bean) {
        adapter.mDatas.remove(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.car_btn_car_press://下单
                String str = car_btn_car_press.getText().toString();
                List<CartBean.DataBean.CartListBean> list = adapter.getAll();//获取选择的集合
                if("下单".equals(str)){
                    // 把getAll 集合数据传递到  确定页面显示
                    Intent intent = new Intent(getContext(), OrderActivity.class);
                    intent.putExtra("getAll", (Serializable) list);
                    startActivity(intent);
                }else{//删除
                    for(CartBean.DataBean.CartListBean bean : adapter.mDatas){
                        presenter.delCart(bean.getProduct_id());
                    }
                }
                break;
            case R.id.car_cb_all://全选
                boolean checked = car_cb_all.isChecked();
                adapter.getAll(checked);
                setAllPrice();
                break;
            case R.id.car_tv_edit_state:
                String s = car_tv_edit_state.getText().toString();
                if("编辑".equals(s)){
                     car_btn_car_press.setText("删除所有");
                     car_tv_edit_state.setText("完成");
                }else{//完成
                    car_btn_car_press.setText("下单");
                    car_tv_edit_state.setText("编辑");
                }
                adapter.setItemVisibility(car_tv_edit_state.getText().toString());
                break;
        }
    }

    private void setAllPrice() {
        //计算一下总价   选中item存储的集合
        List<CartBean.DataBean.CartListBean> getAll = adapter.getAll();
        int sum = 0;
        int count = 0;
        for (int i = 0; i < getAll.size(); i++) {
            sum+=getAll.get(i).getRetail_price()*getAll.get(i).getNumber();
            count +=getAll.get(i).getNumber();
        }
        car_cb_all.setText("全部("+count+")");
        car_tv_count_price.setText("￥"+sum);
    }
}