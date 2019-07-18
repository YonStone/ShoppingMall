package com.youdu.shoppingmall.shoppingcart.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

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
    private TextView textView;

    @Override
    protected View initView() {
        Log.e(TAG, "购物车视图被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, "购物车数据被初始化了");
        textView.setText("购物车");

        List<GoodsBean> goodsBeans = CartProvider.getInstance().getAllData();
        for (int i = 0; i < goodsBeans.size(); i++) {
            Log.e(TAG, "initData: " + goodsBeans.get(i).toString());
        }
    }
}
