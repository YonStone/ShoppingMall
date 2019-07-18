package com.youdu.shoppingmall.app;

import android.app.Application;
import android.content.Context;

/**
 * @author YonStone
 * @date 2019/07/15
 * @description
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    // 获取全局上下文
    public static Context getContext() {
        return mContext;
    }
}
