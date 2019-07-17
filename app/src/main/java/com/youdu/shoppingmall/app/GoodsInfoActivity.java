package com.youdu.shoppingmall.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.home.bean.GoodsBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsInfoActivity extends AppCompatActivity {

    @Bind(R.id.ib_good_info_back)
    ImageButton ibGoodInfoBack;
    @Bind(R.id.ib_good_info_more)
    ImageButton ibGoodInfoMore;
    @Bind(R.id.iv_good_info_image)
    ImageView ivGoodInfoImage;
    @Bind(R.id.tv_good_info_name)
    TextView tvGoodInfoName;
    @Bind(R.id.tv_good_info_desc)
    TextView tvGoodInfoDesc;
    @Bind(R.id.tv_good_info_price)
    TextView tvGoodInfoPrice;
    @Bind(R.id.tv_good_info_store)
    TextView tvGoodInfoStore;
    @Bind(R.id.tv_good_info_style)
    TextView tvGoodInfoStyle;
    @Bind(R.id.wb_good_info_more)
    WebView wbGoodInfoMore;
    @Bind(R.id.tv_good_info_callcenter)
    TextView tvGoodInfoCallcenter;
    @Bind(R.id.tv_good_info_collection)
    TextView tvGoodInfoCollection;
    @Bind(R.id.tv_good_info_cart)
    TextView tvGoodInfoCart;
    @Bind(R.id.btn_good_info_addcart)
    Button btnGoodInfoAddcart;
    @Bind(R.id.ll_goods_root)
    LinearLayout llGoodsRoot;
    @Bind(R.id.tv_more_share)
    TextView tvMoreShare;
    @Bind(R.id.tv_more_search)
    TextView tvMoreSearch;
    @Bind(R.id.tv_more_home)
    TextView tvMoreHome;
    @Bind(R.id.btn_more)
    Button btnMore;
    @Bind(R.id.ll_root)
    LinearLayout llRoot;

    public static final String GOODS_BEAN = "goods_bean";

    //    private List<GoodsBean> goodsBeans;
    private GoodsBean goodsBean;

    public static Intent actionView(Context context, GoodsBean bean) {
        Intent intent = new Intent(context, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN, bean);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
        goodsBean = (GoodsBean) getIntent().getSerializableExtra(GOODS_BEAN);
        if (goodsBean != null) {
            Gson gson = new Gson();
            Toast.makeText(this, "" + gson.toJson(goodsBean), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.ib_good_info_back, R.id.ib_good_info_more, R.id.tv_good_info_callcenter, R.id
            .tv_good_info_collection, R.id.tv_good_info_cart, R.id.btn_good_info_addcart, R.id
            .tv_more_share, R.id.tv_more_search, R.id.tv_more_home, R.id.btn_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                finish();
                break;
            case R.id.ib_good_info_more:
                llRoot.setVisibility(llGoodsRoot.getVisibility() == View.VISIBLE ?
                        View.GONE : View.VISIBLE);
                break;
            case R.id.btn_more:
                llRoot.setVisibility(View.GONE);
                break;
            case R.id.tv_more_share:
                Toast.makeText(GoodsInfoActivity.this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_search:
                Toast.makeText(GoodsInfoActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_home:
//                Constants.isBackHome = true;
//                finish();
                break;
            case R.id.tv_good_info_callcenter:
                Toast.makeText(GoodsInfoActivity.this, "客服", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_collection:
                Toast.makeText(GoodsInfoActivity.this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_cart:
                Toast.makeText(GoodsInfoActivity.this, "购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_good_info_addcart:
                Toast.makeText(GoodsInfoActivity.this, "添加购物车", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }
}
