package com.jiyun.myshop.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.base.BaseFragment;
import com.jiyun.myshop.interfaces.notification.CatalogConstract;
import com.jiyun.myshop.model.bean.CatalogBean;
import com.jiyun.myshop.model.bean.CatalogByIdBean;
import com.jiyun.myshop.presenter.notification.CatalogPresenter;
import com.jiyun.myshop.ui.notifications.adapter.CatalogAdapter;
import com.jiyun.myshop.ui.notifications.adapter.CatalogByIdAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类
 */
public class NotificationsFragment extends BaseFragment<CatalogConstract.Presenter> implements CatalogConstract.View, VerticalTabLayout.OnTabSelectedListener {

    VerticalTabLayout mTab;
    ImageView iv;
    TextView tv_frontname;
    TextView tv_title;
    RecyclerView rlView;
    MyTabAdapter adapter;
    CatalogAdapter rlAdapter;
    CatalogByIdAdapter rlByIdAdapter = null;
    List<CatalogByIdBean.DataBean.CurrentCategoryBean.SubCategoryListBean> rByIdlist = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.fragment_notifications;
    }

    @Override
    protected CatalogConstract.Presenter createPresenter() {
        return new CatalogPresenter();
    }

    @Override
    protected void initView() {
        mTab = getView().findViewById(R.id.tab);
        iv = getView().findViewById(R.id.iv);
        tv_frontname = getView().findViewById(R.id.tv_frontname);
        tv_title = getView().findViewById(R.id.tv_title);
        rlView = getView().findViewById(R.id.rl_View);

        List<CatalogBean.DataBean.CurrentCategoryBean.SubCategoryListBean> rlist = new ArrayList<>();
        rlAdapter = new CatalogAdapter(rlist,context);
        rlView.setLayoutManager(new GridLayoutManager(context,3));
        rlView.setAdapter(rlAdapter);
        rlByIdAdapter = new CatalogByIdAdapter(rByIdlist,context);
        mTab.addOnTabSelectedListener(this);


        //item的点击事件
        rlAdapter.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter.VH vh, int position) {
                Intent intent = new Intent(context, CategoryActivity.class);
                intent.putExtra("id",rlist.get(position).getId());
                startActivity(intent);
            }
        });

        rlByIdAdapter.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter.VH vh, int position) {
                Intent intent = new Intent(context, CategoryActivity.class);
                intent.putExtra("id",rByIdlist.get(position).getId());
                startActivity(intent);
            }
        });

    }

    List<CatalogBean.DataBean.CategoryListBean> list = new ArrayList<>();

    @Override
    protected void initData() {
        presenter.getCatalog();
    }

    @Override
    public void getCatalogReturn(CatalogBean bean) {
        list.clear();
        list.addAll(bean.getData().getCategoryList());
        adapter = new MyTabAdapter();
        mTab.setTabAdapter(adapter);
        //默认显示第一个条目的图片
        Glide.with(context).load(list.get(0).getWap_banner_url()).into(iv);
        tv_frontname.setText(list.get(0).getFront_name());
        tv_title.setText(list.get(0).getName()+"分类");
        rlAdapter.updateList(bean.getData().getCurrentCategory().getSubCategoryList());
    }

    @Override
    public void getCatalogByIdReturn(CatalogByIdBean bean) {
        Glide.with(context).load(bean.getData().getCurrentCategory().getWap_banner_url()).into(iv);
        tv_frontname.setText(bean.getData().getCurrentCategory().getFront_name());
        tv_title.setText(bean.getData().getCurrentCategory().getName()+"分类");
        if(rlByIdAdapter == null){
//            rByIdlist.clear();
//            rByIdlist.addAll(bean.getData().getCurrentCategory().getSubCategoryList());
//            rlByIdAdapter = new CatalogByIdAdapter(rByIdlist,context);
            rlView.setAdapter(rlByIdAdapter);
        }else {
            rlByIdAdapter.updateList(bean.getData().getCurrentCategory().getSubCategoryList());
        }
    }

    @Override
    public void onTabSelected(TabView tab, int position) {
        presenter.getCatalogById(list.get(position).getId()+"");
    }

    @Override
    public void onTabReselected(TabView tab, int position) {

    }

    class MyTabAdapter implements TabAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public ITabView.TabBadge getBadge(int position) {
            return null;
        }

        @Override
        public ITabView.TabIcon getIcon(int position) {
            return null;
        }

        @Override
        public ITabView.TabTitle getTitle(int position) {
            ITabView.TabTitle tilte = new ITabView.TabTitle.Builder()
                    .setContent(list.get(position).getName())
                    .build();
            return tilte;
        }

        @Override
        public int getBackground(int position) {
            return 0;
        }
    }
}