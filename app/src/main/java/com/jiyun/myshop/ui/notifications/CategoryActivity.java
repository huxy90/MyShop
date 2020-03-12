package com.jiyun.myshop.ui.notifications;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseActivity;
import com.jiyun.myshop.interfaces.notification.CategoryTopConstract;
import com.jiyun.myshop.model.bean.CategoryBottom;
import com.jiyun.myshop.model.bean.CategoryTop;
import com.jiyun.myshop.presenter.notification.CategoryTopPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * 分类 详情
 */
public class CategoryActivity extends BaseActivity<CategoryTopConstract.Presenter> implements CategoryTopConstract.View {

    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    @BindView(R.id.rl_View)
    RecyclerView rl_View;
    int page = 1;
    int size = 10;
    int total = 0;

    @Override
    public int getLayout() {
        return R.layout.activity_category;
    }

    @Override
    protected CategoryTopConstract.Presenter createPresenter() {
        return new CategoryTopPresenter();
    }

    String id;
    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        presenter.getCategoryTop(id);
        presenter.getCategoryBottom(id,page,size);
    }

    @Override
    protected void initView() {

        hideHeader();
        srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(page < total){
                    page++;
                    presenter.getCategoryBottom(id,page,size);
                }else {
                    Toast.makeText(CategoryActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                page = 1;
                presenter.getCategoryBottom(id,page,size);
            }
        });
    }

    private void hideHeader() {
        srl.finishRefresh();
        srl.finishLoadMore();
    }

    @Override
    public void getCategoryTopReturn(CategoryTop bean) {

    }

    @Override
    public void getCategoryBoReturn(CategoryBottom bean) {

    }
}
