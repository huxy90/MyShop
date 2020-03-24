package com.jiyun.myshop.ui.notifications;

import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseActivity;
import com.jiyun.myshop.interfaces.notification.SearchConstract;
import com.jiyun.myshop.model.bean.GoodListBean;
import com.jiyun.myshop.presenter.notification.SearchPresenter;
import com.jiyun.myshop.ui.notifications.adapter.GoodListAdapter;
import com.jiyun.myshop.utils.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchConstract.Presenter> implements SearchConstract.View {

    @BindView(R.id.fl)
    FlowLayout fl;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.tv_search_no_result)
    TextView tvSearchNoResult;
    @BindView(R.id.rv_search_result)
    RecyclerView rvSearchResult;
    private String keyWord;
    private ArrayList<GoodListBean.DataBeanX.DataBean> list;
    private GoodListAdapter adapter;
    private ArrayList<String> historyList;
    private String keyword, sort = "default", order = "desc";
    private int page = 1, size = 100, categoryId = 0;

    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchConstract.Presenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void initView() {
        historyList = new ArrayList<>();
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //隐藏软键盘
                    hideKeyboard(etSearch);

                    //在这里做操作，一般网络请求、数据库查询
                    keyWord = etSearch.getText().toString();
                    if (list != null && list.size() > 0) {
                        list.clear();
                    }
                    initData2();
                    return true;
                }
                return false;
            }
        });

        rvSearchResult.setLayoutManager(new GridLayoutManager(this, 2));
        list = new ArrayList<>();
        adapter = new GoodListAdapter(list,this);
        rvSearchResult.setAdapter(adapter);
    }

    public void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    protected void initData() {

    }

    protected void initData2() {
        rvSearchResult.setVisibility(View.GONE);
        tvSearchNoResult.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(keyWord)) {
            tvHistory.setVisibility(View.GONE);
            fl.setVisibility(View.GONE);

            if (!historyList.contains(keyWord)) {
                historyList.add(keyWord);
                TextView lable = (TextView) LayoutInflater.from(this).inflate(R.layout.item_fl, null);
                lable.setText(keyWord);

                lable.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etSearch.setText(lable.getText().toString());
                    }
                });

                fl.addView(lable);
            }
            presenter.getGoodList(keyword,page,size,sort,order,categoryId);
        } else {
            tvHistory.setVisibility(View.VISIBLE);
            fl.setVisibility(View.VISIBLE);
        }
    }
    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void getGoodListReturn(GoodListBean bean) {
        List<GoodListBean.DataBeanX.DataBean> data = bean.getData().getData();
        if (data.size() > 0) {
            rvSearchResult.setVisibility(View.VISIBLE);
            //list.addAll(data);
            adapter.updateMoreList(data);
        } else {
            tvSearchNoResult.setVisibility(View.VISIBLE);
        }
    }
}
