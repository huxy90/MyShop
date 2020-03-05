package com.jiyun.myshop.ui.dashboard;

import android.util.Log;
import android.widget.TextView;

import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseFragment;
import com.jiyun.myshop.interfaces.topic.TopicConstract;
import com.jiyun.myshop.model.adapter.TopicAdapter;
import com.jiyun.myshop.model.bean.TopicBean;
import com.jiyun.myshop.presenter.topic.TopicPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class DashboardFragment extends BaseFragment<TopicConstract.Presenter> implements TopicConstract.View {

    int page = 1;
    int size = 10;

    //@BindView(R.id.tv_up)
    TextView mTvUp;
    //@BindView(R.id.tv_down)
    TextView mTvDown;
    TopicAdapter adapter;
    //@BindView(R.id.rl_View)
    RecyclerView mRlView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_dashboard;
    }
    @Override
    protected TopicConstract.Presenter createPresenter() {
        return new TopicPresenter();
    }
    @Override
    protected void initView() {
        mRlView = getView().findViewById(R.id.rl_View);
        mTvUp = getView().findViewById(R.id.tv_up);
        mTvDown = getView().findViewById(R.id.tv_down);
        mRlView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
        List<TopicBean.DataBeanX.DataBean> list = new ArrayList<>();
        adapter = new TopicAdapter(context);
        mRlView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.getTopicData(page, size);
    }



    @Override
    public void getTopicDataReturn(TopicBean bean) {
        adapter.addData(bean.getData().getData());
    }
}