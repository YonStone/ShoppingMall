package com.youdu.shoppingmall.shoppingcart.fragment;

import android.util.Log;
import android.view.View;

import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.base.BaseFragment;
import com.youdu.shoppingmall.home.bean.GoodsBean;
import com.youdu.shoppingmall.shoppingcart.utils.CartProvider;

import java.util.List;

/**
 * @author YonStone
 * @date 2019/07/15
 * @description
 */
public class ShoppingCartFragment extends BaseFragment {

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shoppingcart, null);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        List<GoodsBean> goodsBeans = CartProvider.getInstance().getAllData();
        for (int i = 0; i < goodsBeans.size(); i++) {
            Log.e(TAG, "initData: " + goodsBeans.get(i).toString());
        }
    }
}
