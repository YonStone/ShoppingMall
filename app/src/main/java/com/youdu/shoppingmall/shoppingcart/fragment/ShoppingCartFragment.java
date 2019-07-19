package com.youdu.shoppingmall.shoppingcart.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.app.MainActivity;
import com.youdu.shoppingmall.base.BaseFragment;
import com.youdu.shoppingmall.home.bean.GoodsBean;
import com.youdu.shoppingmall.shoppingcart.adapter.ShopCartAdapter;
import com.youdu.shoppingmall.shoppingcart.utils.CartProvider;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YonStone
 * @date 2019/07/15
 * @description
 */
public class ShoppingCartFragment extends BaseFragment {

    @Bind(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.checkbox_all)
    CheckBox cbAll;
    @Bind(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @Bind(R.id.btn_check_out)
    Button btnCheckOut;
    @Bind(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @Bind(R.id.cb_all_cancel)
    CheckBox cbAllCancel;
    @Bind(R.id.btn_delete)
    Button btnDelete;
    @Bind(R.id.btn_collection)
    Button btnCollection;
    @Bind(R.id.ll_delete)
    LinearLayout llDelete;
    @Bind(R.id.iv_empty)
    ImageView ivEmpty;
    @Bind(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @Bind(R.id.ll_empty_shopcart)
    LinearLayout llEmptyShopcart;

    /**
     * 编辑状态
     */
    private static final int ACTION_EDIT = 0;
    /**
     * 完成状态
     */
    private static final int ACTION_COMPLETE = 1;
    private ShopCartAdapter adapter;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shoppingcart, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
        llCheckAll.setVisibility(View.VISIBLE);
        showData();
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_shopcart_edit, R.id.btn_check_out, R.id.btn_delete, R.id.tv_empty_cart_tobuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
                int tag = (int) tvShopcartEdit.getTag();
                if (tag == ACTION_EDIT) {
                    showDelete();
                } else {
                    hideDelete();
                }
                break;
            //去结算
            case R.id.btn_check_out:
                break;
            case R.id.btn_delete:
                adapter.deleteData();
                adapter.showTotalPrice();
                //显示空空如也
                checkShowEmpty();
                adapter.checkAll();
                break;
            //去逛逛
            case R.id.tv_empty_cart_tobuy:
                //TODO:调到homefragment
//                Intent intent = new Intent(mContext, MainActivity.class);
//                startActivity(intent);
                break;
            default:
        }
    }

    /**
     * 是否显示空视图
     */
    private void checkShowEmpty() {
        if (adapter != null && adapter.getItemCount() > 0) {
            tvShopcartEdit.setVisibility(View.VISIBLE);
            llEmptyShopcart.setVisibility(View.GONE);
        } else {
            llEmptyShopcart.setVisibility(View.VISIBLE);
            tvShopcartEdit.setVisibility(View.GONE);
        }
    }

    private void showData() {
        CartProvider cartProvider = CartProvider.getInstance();
        List<GoodsBean> datas = cartProvider.getAllData();
        if (datas != null && datas.size() > 0) {
            tvShopcartEdit.setVisibility(View.VISIBLE);
            llEmptyShopcart.setVisibility(View.GONE);

            adapter = new ShopCartAdapter(mContext, datas, cartProvider,
                    tvShopcartTotal, cbAll, cbAllCancel);
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerview.setAdapter(adapter);
        } else {
            // 显示空的
            tvShopcartEdit.setVisibility(View.GONE);
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 删除页默认就是不选中
     */
    private void showDelete() {
        tvShopcartEdit.setText("完成");
        tvShopcartEdit.setTag(ACTION_COMPLETE);

        adapter.checkAll_none(false);

        llDelete.setVisibility(View.VISIBLE);
        llCheckAll.setVisibility(View.GONE);
        adapter.showTotalPrice();
    }

    /**
     * 结算页默认就是选中
     */
    private void hideDelete() {
        tvShopcartEdit.setText("编辑");
        tvShopcartEdit.setTag(ACTION_EDIT);

        adapter.checkAll_none(true);

        llDelete.setVisibility(View.GONE);
        llCheckAll.setVisibility(View.VISIBLE);
        adapter.showTotalPrice();
    }
}
