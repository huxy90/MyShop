package com.jiyun.myshop.ui.notifications;

import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseActivity;
import com.jiyun.myshop.interfaces.notification.CategoryTopConstract;
import com.jiyun.myshop.model.bean.CategoryTop;
import com.jiyun.myshop.presenter.notification.CategoryTopPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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

    @Override
    public int getLayout() {
        return R.layout.activity_category;
    }

    @Override
    protected CategoryTopConstract.Presenter createPresenter() {
        return new CategoryTopPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void getCategoryTopReturn(CategoryTop bean) {

    }
}
