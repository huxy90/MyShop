package com.jiyun.myshop.ui.notifications;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseActivity;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.interfaces.notification.CategoryBoConstract;
import com.jiyun.myshop.model.bean.CatalogByIdBean;
import com.jiyun.myshop.model.bean.CategoryBottom;
import com.jiyun.myshop.presenter.notification.CategoryBoPresenter;
import com.jiyun.myshop.ui.notifications.adapter.CategoryAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * 分类 详情
 */
public class CategoryActivity extends BaseActivity<CategoryBoConstract.Presenter> implements CategoryBoConstract.View {

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
    private int mCategoryId;
    int mPosition;
    //List<CategoryBottom.DataBeanX.FilterCategoryBean> mList;
    List<CatalogByIdBean.DataBean.CurrentCategoryBean.SubCategoryListBean> mList;
    CategoryAdapter adapter;
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            //tablayout显示
//            for (int i = 0; i < mList.size();i++){
//                CategoryBottom.DataBeanX.FilterCategoryBean fBean = mList.get(i);
//                tabLayout.addTab(tabLayout.newTab().setText(fBean.getName()));
//            }
//            setHeadData();
//            //选中我们点击的tab，并滑动到屏幕内
//            tabLayout.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    tabLayout.getTabAt(mPosition).select();
//                }
//            },100);
//        }
//    };

    @Override
    public int getLayout() {
        return R.layout.activity_category;
    }

    @Override
    protected CategoryBoConstract.Presenter createPresenter() {
        return new CategoryBoPresenter();
    }

    @Override
    protected void initData() {
       presenter.getCategoryBottom(mCategoryId,page,size);
    }

    @Override
    protected void initView() {
        //获取上个页面传递的数据
        mList = (List<CatalogByIdBean.DataBean.CurrentCategoryBean.SubCategoryListBean>) getIntent().getSerializableExtra("data");
        mPosition = getIntent().getIntExtra("position",0);//上个页面item的position
        //mCategoryId = getIntent().getIntExtra("cid",0);
        mCategoryId = mList.get(mPosition).getId();//分类id
        //tablayout
        for (int i = 0; i < mList.size();i++){
            CatalogByIdBean.DataBean.CurrentCategoryBean.SubCategoryListBean bean = mList.get(i);
            tabLayout.addTab(tabLayout.newTab().setText(bean.getName()));
        }
        tabLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tabLayout.getTabAt(mPosition).select();
                }
            },100);

        //tablayout的切换监听
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPosition = tab.getPosition();
                mCategoryId = mList.get(mPosition).getId();
                page = 1;
                adapter.mDatas.clear();
                adapter.notifyDataSetChanged();
                //请求数据
                presenter.getCategoryBottom(mCategoryId,page,size);

                setHeadData();//设置标题
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //2.recyclerview
        ArrayList<CategoryBottom.DataBeanX.DataBean> list = new ArrayList<>();
        rl_View.setLayoutManager(new GridLayoutManager(this,2));
//        rl_View.addItemDecoration(new GridDivider(this,5,R.color.linebg));
        adapter = new CategoryAdapter(list,this);
        rl_View.setAdapter(adapter);

        setHeadData();//设置标题数据

        srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getCategoryBottom(mCategoryId,page,size);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                adapter.mDatas.clear();
                page = 1;
                presenter.getCategoryBottom(mCategoryId,page,size);
            }
        });

        //item的监听
        adapter.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter.VH vh, int position) {
                Intent intent = new Intent(CategoryActivity.this,GoodInfoActivity.class);
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getCategoryBoReturn(CategoryBottom bean) {
        adapter.updateMoreList(bean.getData().getData());
        hideHeader();
        //tablayout显示
//        bean.getData().getFilterCategory().remove(0);
//        mList = bean.getData().getFilterCategory();
//        handler.sendEmptyMessage(0);

    }

    private void hideHeader() {
        srl.finishRefresh();
        srl.finishLoadMore();
    }

    private void setHeadData(){
        CatalogByIdBean.DataBean.CurrentCategoryBean.SubCategoryListBean bean = mList.get(mPosition);
        tv_name.setText(bean.getName());
        tv_desc.setText(bean.getFront_desc());
    }
}
