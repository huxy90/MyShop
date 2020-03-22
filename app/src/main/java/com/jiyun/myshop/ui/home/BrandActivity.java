package com.jiyun.myshop.ui.home;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseActivity;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.interfaces.home.BrandConstract;
import com.jiyun.myshop.model.bean.BrandBean;
import com.jiyun.myshop.model.bean.HomeBean;
import com.jiyun.myshop.presenter.home.BrandPresenter;
import com.jiyun.myshop.ui.home.adapter.BrandListAdapter;
import com.jiyun.myshop.ui.notifications.GoodInfoActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class BrandActivity extends BaseActivity<BrandConstract.Presenter> implements BrandConstract.View {

    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    @BindView(R.id.rl_View)
    RecyclerView rl_View;
    BrandListAdapter adapter;
    int page = 1;
    int size = 10;
    int totalPage = 0;
    @Override
    public int getLayout() {
        return R.layout.activity_brand;
    }

    @Override
    protected BrandConstract.Presenter createPresenter() {
        return new BrandPresenter();
    }
    HomeBean.DataBean.BrandListBean bean;
    @Override
    protected void initData() {
        bean = (HomeBean.DataBean.BrandListBean) getIntent().getSerializableExtra("bean");
        Glide.with(this).load(bean.getList_pic_url()).into(iv);
        tv_name.setText(bean.getName());
        tv_desc.setText(bean.getSimple_desc());
        Log.i("TAG",bean.getId()+"--id--------");
        presenter.getBrandData(bean.getId()+"",page+"",size+"");
    }

    @Override
    protected void initView() {
          List<BrandBean.DataBeanX.DataBean> list = new ArrayList<>();
          adapter = new BrandListAdapter(list,this);
          rl_View.setLayoutManager(new GridLayoutManager(this,2));
          rl_View.setAdapter(adapter);

          srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
              @Override
              public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                  if(page < totalPage){
                       page++;
                       presenter.getBrandData(bean.getId()+"",page+"",size+"");
                  }else {
                      Toast.makeText(BrandActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                  }
              }

              @Override
              public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                  page = 1;
                  list.clear();
                  presenter.getBrandData(bean.getId()+"",page+"",size+"");
              }
          });
        //隐藏下拉刷新和上拉加载的头/脚布局
          hideHeader();

          //点击item跳转到购买页面
          adapter.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
              @Override
              public void onItemClick(BaseAdapter.VH vh, int position) {
                  Intent intent = new Intent(BrandActivity.this, GoodInfoActivity.class);
                  intent.putExtra("id",list.get(position).getId());
                  startActivity(intent);
              }
          });
    }

    @Override
    public void getBrandReturn(BrandBean bean) {
        totalPage = bean.getData().getTotalPages();
        adapter.updateMoreList(bean.getData().getData());
    }
    private void hideHeader() {
        srl.finishRefresh();
        srl.finishLoadMore();
    }
}
