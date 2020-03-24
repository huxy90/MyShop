package com.jiyun.myshop.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类
 */
public class NotificationsFragment extends BaseFragment<CatalogConstract.Presenter> implements CatalogConstract.View, VerticalTabLayout.OnTabSelectedListener {

    LinearLayout ll_search;//搜索
    VerticalTabLayout mTab;
    ImageView iv;
    TextView tv_frontname;
    TextView tv_title;
    RecyclerView rlView;
    MyTabAdapter adapter;
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
        ll_search = getView().findViewById(R.id.ll_search);
        mTab = getView().findViewById(R.id.tab);
        iv = getView().findViewById(R.id.iv);
        tv_frontname = getView().findViewById(R.id.tv_frontname);
        tv_title = getView().findViewById(R.id.tv_title);
        rlView = getView().findViewById(R.id.rl_View);
        rlView.setLayoutManager(new GridLayoutManager(context,3));
        rlByIdAdapter = new CatalogByIdAdapter(rByIdlist,context);
        rlView.setAdapter(rlByIdAdapter);
        mTab.addOnTabSelectedListener(this);

        rlByIdAdapter.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter.VH vh, int position) {
                Intent intent = new Intent(context, CategoryActivity.class);
               // CatalogByIdBean.DataBean.CurrentCategoryBean.SubCategoryListBean bean = (CatalogByIdBean.DataBean.CurrentCategoryBean.SubCategoryListBean) rlByIdAdapter.mDatas;
                //intent.putExtra("cid",rByIdlist.get(0).getId());
                intent.putExtra("data", (Serializable) rlByIdAdapter.mDatas);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        //点击搜索框跳转到搜索页面
        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
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

        presenter.getCatalogById(list.get(0).getId()+"");
    }

    @Override
    public void getCatalogByIdReturn(CatalogByIdBean bean) {
        Glide.with(context).load(bean.getData().getCurrentCategory().getWap_banner_url()).into(iv);
        tv_frontname.setText(bean.getData().getCurrentCategory().getFront_name());
        tv_title.setText(bean.getData().getCurrentCategory().getName()+"分类");

        rlByIdAdapter.updateList(bean.getData().getCurrentCategory().getSubCategoryList());
    }

    @Override
    public void onTabSelected(TabView tab, int position) {
        int id = list.get(position).getId();
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