package com.jiyun.myshop.ui.dashboard;

import android.content.Intent;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;

import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseActivity;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.interfaces.IBaseView;
import com.jiyun.myshop.interfaces.topic.TopicDetailConstract;
import com.jiyun.myshop.model.bean.CommentBean;
import com.jiyun.myshop.model.bean.TopicDetailBean;
import com.jiyun.myshop.model.bean.TopicTJ;
import com.jiyun.myshop.presenter.topic.TopicDetailPresenter;
import com.jiyun.myshop.ui.dashboard.adapter.CommentAdapter;
import com.jiyun.myshop.ui.dashboard.adapter.TopicTJAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class TopicDetailActivity extends BaseActivity<TopicDetailConstract.Presenter> implements TopicDetailConstract.View {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.iv_write)
    ImageView iv_write;
    @BindView(R.id.rl_comment)
    RecyclerView rl_comment;
    @BindView(R.id.rl_topic)
    RecyclerView rl_topic;
    CommentAdapter adapter;//评论
    TopicTJAdapter tAdatper;//专题

    @Override
    public int getLayout() {
        return R.layout.activity_topicdetail;
    }

    @Override
    protected TopicDetailConstract.Presenter createPresenter() {
        return new TopicDetailPresenter();
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id",0);
        presenter.getTopicDetail(id);//详情数据
        presenter.getComment(id,1,5);//评论
        presenter.getTopicTJ(id);//专题推荐
    }

    @Override
    protected void initView() {
        List<CommentBean.DataBeanX.DataBean> list = new ArrayList<>();
        adapter = new CommentAdapter(list,this);
        rl_comment.setLayoutManager(new LinearLayoutManager(this));
        rl_comment.setAdapter(adapter);

        List<TopicTJ.DataBean> tList = new ArrayList<>();
        tAdatper = new TopicTJAdapter(tList,this);
        rl_topic.setLayoutManager(new LinearLayoutManager(this));
        rl_topic.setAdapter(tAdatper);
        tAdatper.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter.VH vh, int position) {
                int id = getIntent().getIntExtra("id",0);
                Intent intent = new Intent(TopicDetailActivity.this,TopicDetailActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getTopicDetailReturn(TopicDetailBean bean) {
        getWebView(bean);
    }

    @Override
    public void getCommentReturn(CommentBean bean) {
         adapter.updateMoreList(bean.getData().getData());
    }

    @Override
    public void getTopicTJReturn(TopicTJ bean) {
         tAdatper.updateMoreList(bean.getData());
    }

    public void getWebView(TopicDetailBean bean){
        String css_str = getResources().getString(R.string.css_goods2);
        String content = bean.getData().getContent().replace("//","http://").replace("\n","<br/>");
        Log.e("TAG","content: "+content);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append("<style>"+css_str+"</style></head><body>");
        sb.append(content+"</body></html>");
        webView.loadData(sb.toString(),"text/html","utf-8");
    }
}
