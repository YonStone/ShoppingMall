package com.youdu.shoppingmall.type.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.youdu.shoppingmall.base.BaseFragment;

/**
 * @author YonStone
 * @date 2019/07/15
 * @description
 */
public class TypeFragment extends BaseFragment {
    private TextView textView;

    @Override
    protected View initView() {
        Log.e(TAG, "分类视图被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, "分类数据被初始化了");
        textView.setText("分类");
    }
}