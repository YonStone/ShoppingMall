package com.youdu.shoppingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.youdu.yonstone_sdk.imageloader.ImageLoaderManager;

/**
 * @author YonStone
 * @date 2019/07/17
 * @description
 */
public class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected ImageLoaderManager mImageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mImageLoader = ImageLoaderManager.getInstance(mContext);
    }
}
