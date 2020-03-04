package com.jiyun.myshop.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseFragment;
import com.jiyun.myshop.interfaces.home.HomeConstract;
import com.jiyun.myshop.model.bean.HomeBean;
import com.jiyun.myshop.presenter.home.HomePresenter;

public class HomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {


    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeConstract.Presenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    public void getHomeDataReturn(HomeBean result) {
        Log.i("TAG","aaaaaaaaaaaaaaaafragment: "+result);
    }
}