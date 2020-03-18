package com.jiyun.myshop.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.jiyun.myshop.R;
import com.jiyun.myshop.base.BaseActivity;
import com.jiyun.myshop.base.BaseAdapter;
import com.jiyun.myshop.interfaces.notification.GoodInfoConstract;
import com.jiyun.myshop.model.bean.GoodInfoBean;
import com.jiyun.myshop.model.bean.GoodInfoBo;
import com.jiyun.myshop.presenter.notification.GoodInfoPresenter;
import com.jiyun.myshop.ui.notifications.adapter.GoodBoAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商品详情
 */
public class GoodInfoActivity extends BaseActivity<GoodInfoConstract.Presenter> implements GoodInfoConstract.View, View.OnClickListener {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_brief)
    TextView tv_brief;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.cl_select)
    ConstraintLayout cl_select;//请选择规格数量
    @BindView(R.id.ll_param)
    LinearLayout ll_param;//商品参数
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.ll_issue)
    LinearLayout ll_issue;//常见问题
    @BindView(R.id.rl_View)
    RecyclerView rl_View;
    @BindView(R.id.v_line)
    View v_line;
    @BindView(R.id.cl_View)
    ConstraintLayout cl_View;
    @BindView(R.id.iv_store)
    ImageView iv_store;//收藏
    @BindView(R.id.iv_cart)
    ImageView iv_cart;//购物车
    @BindView(R.id.tv_go)
    TextView tv_go;//立即购买
    @BindView(R.id.tv_addcart)
    TextView tv_addcart;//加入购物车

    GoodBoAdapter adapter;
    private PopupWindow pwMyPopWindow;
    //购买个数
    int num = 1;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            et_num.setText(num+"");
        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_goodinfo;
    }

    @Override
    protected GoodInfoConstract.Presenter createPresenter() {
        return new GoodInfoPresenter();
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id",0);
        presenter.getGoodInfo(id);//商品详情
        presenter.getGoodInfoBo(id);//商品底部列表
    }

    @Override
    protected void initView() {
        List<GoodInfoBo.DataBean.GoodsListBean> list = new ArrayList<>();
        adapter = new GoodBoAdapter(list,GoodInfoActivity.this);
        rl_View.setLayoutManager(new GridLayoutManager(this,2));
        rl_View.setAdapter(adapter);
        adapter.addOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseAdapter.VH vh, int position) {
                int id = list.get(position).getId();
                Intent intent = new Intent(GoodInfoActivity.this, GoodInfoActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.cl_select,R.id.tv_go,R.id.tv_addcart})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.cl_select:
                //弹框
                if (pwMyPopWindow.isShowing()) {
                    pwMyPopWindow.dismiss();// 关闭
                } else {
                    // pwMyPopWindow.showAsDropDown(rl_View);// 显示
                    pwMyPopWindow.showAtLocation(v_line, Gravity.BOTTOM,0,cl_View.getHeight());
                    backgroundAlpha(0.7f);
                }
                break;
            case R.id.tv_go://立即购买

                break;
            case R.id.tv_addcart://加入购物车

                break;
        }

    }

    @Override
    public void getGoodInfoReturn(GoodInfoBean bean) {
        iniPopupWindow(bean);
        //轮播图
        List<GoodInfoBean.DataBeanX.GalleryBean> gallery = bean.getData().getGallery();
        banner.setImages(gallery).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                GoodInfoBean.DataBeanX.GalleryBean bean = (GoodInfoBean.DataBeanX.GalleryBean)path;
                Glide.with(GoodInfoActivity.this).load(bean.getImg_url()).into(imageView);
            }
        }).start();
        //标题
        GoodInfoBean.DataBeanX.InfoBean info = bean.getData().getInfo();
        tv_name.setText(info.getName());
        tv_brief.setText(info.getGoods_brief());
        tv_price.setText("¥"+info.getRetail_price());
        //商品参数
        List<GoodInfoBean.DataBeanX.AttributeBean> attribute = bean.getData().getAttribute();
        for(GoodInfoBean.DataBeanX.AttributeBean abean : attribute){
            View view = LayoutInflater.from(this).inflate(R.layout.param_item,null);
            TextView tv_one = view.findViewById(R.id.tv_one);
            TextView tv_two = view.findViewById(R.id.tv_two);
            tv_one.setText(abean.getName());
            tv_two.setText(abean.getValue());
            ll_param.addView(view);
        }
        //webview
        String css_str = getResources().getString(R.string.css_goods);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append("<style>"+css_str+"</style></head><body>");
        sb.append(info.getGoods_desc()+"</body></html>");
        webView.loadData(sb.toString(),"text/html","utf-8");

        //常见问题
        List<GoodInfoBean.DataBeanX.IssueBean> issue = bean.getData().getIssue();
        for(GoodInfoBean.DataBeanX.IssueBean ibean : issue){
            View view = LayoutInflater.from(this).inflate(R.layout.issue_item,null);
            TextView tv_one = view.findViewById(R.id.tv_one);
            TextView tv_two = view.findViewById(R.id.tv_two);
            tv_one.setText(ibean.getQuestion());
            tv_two.setText(ibean.getAnswer());
            ll_issue.addView(view);
        }

    }

    @Override
    public void getGoodInfoBoReturn(GoodInfoBo bo) {
        adapter.updateMoreList(bo.getData().getGoodsList());
    }

    EditText et_num;//购买个数
    private void iniPopupWindow(GoodInfoBean bean) {
        if(pwMyPopWindow == null){
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popupwindow, null);
            //显示数据
            ImageView iv_close = (ImageView) layout.findViewById(R.id.iv_close);
            ImageView iv = (ImageView) layout.findViewById(R.id.iv);
            TextView tv_price = (TextView) layout.findViewById(R.id.tv_price);
            TextView tv_add = (TextView) layout.findViewById(R.id.tv_add);
            TextView tv_jian = (TextView) layout.findViewById(R.id.tv_jian);
            et_num = (EditText) layout.findViewById(R.id.et_num);
            et_num.setText("1");
            tv_price.setText("¥"+bean.getData().getInfo().getRetail_price());
            Glide.with(this).load(bean.getData().getGallery().get(0).getImg_url()).into(iv);
            //监听
            iv_close.setOnClickListener(this);
            tv_add.setOnClickListener(this);
            tv_jian.setOnClickListener(this);

            pwMyPopWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            pwMyPopWindow.setFocusable(true);
            pwMyPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    backgroundAlpha(1f);//popupwindow消失的时候恢复成原来的透明度
                }
            });
        }
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                pwMyPopWindow.dismiss();
                break;
            case R.id.tv_add:
                num = Integer.parseInt(et_num.getText().toString().trim()) + 1;
                handler.sendEmptyMessage(0);
                break;
            case R.id.tv_jian:
                int num1 = Integer.parseInt(et_num.getText().toString().trim());
                if(num1 > 1){
                    num = num1-1;
                    handler.sendEmptyMessage(0);
                }
                break;
        }
    }
}
