package com.jiyun.myshop.ui.cart;

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
import com.jiyun.myshop.presenter.cart.CartPresenter;
import com.jiyun.myshop.ui.cart.adapter.CartAdapter;
import com.jiyun.myshop.ui.notifications.NotificationsViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartFragment extends BaseFragment<CartConstract.Presenter> implements CartConstract.View {

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
        rl_View = getView().findViewById(R.id.rl_View);
        List<CartBean.DataBean.CartListBean> list = new ArrayList<>();
        adapter = new CartAdapter(list,context);
        rl_View.setLayoutManager(new LinearLayoutManager(context));
        rl_View.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.getCart();
    }

    @Override
    public void getCartReturn(CartBean bean) {
        adapter.updateMoreList(bean.getData().getCartList());
    }
}