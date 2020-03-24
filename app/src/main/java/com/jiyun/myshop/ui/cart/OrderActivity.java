package com.jiyun.myshop.ui.cart;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseActivity;
import com.jiyun.myshop.interfaces.cart.OrderConstract;
import com.jiyun.myshop.model.bean.CartBean;
import com.jiyun.myshop.presenter.cart.OrderPresenter;
import com.jiyun.myshop.ui.cart.adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * 下单
 */
public class OrderActivity extends BaseActivity<OrderConstract.Presenter> implements OrderConstract.View {

    @BindView(R.id.rl_address)
    RelativeLayout rl_address;//收货地址
    @BindView(R.id.tv_sum)
    TextView tv_sum;//商品合计
    @BindView(R.id.tv_yf)
    TextView tv_yf;//运费
    @BindView(R.id.tv_yhq)
    TextView tv_yhq;//优惠券
    @BindView(R.id.rl_View)
    RecyclerView rl_View;
    @BindView(R.id.tv_payPrice)
    TextView tv_payPrice;//实付价格
    private List<CartBean.DataBean.CartListBean> list;
    OrderAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_order;
    }

    @Override
    protected OrderConstract.Presenter createPresenter() {
        return new OrderPresenter();
    }

    @Override
    protected void initData() {
        //获取上个页面传递的商品信息
        list = (List<CartBean.DataBean.CartListBean>) getIntent().getSerializableExtra("getAll");
        setPrice();//计算总价
        //设置适配器
        adapter = new OrderAdapter(list,this);
        rl_View.setLayoutManager(new LinearLayoutManager(this));
        rl_View.setAdapter(adapter);
    }

    @Override
    protected void initView() {

    }

    //计算总价
    private void setPrice() {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getRetail_price()*list.get(i).getNumber();
        }
        tv_sum.setText("￥"+sum);
        tv_payPrice.setText("实付：￥"+sum);
    }
}
