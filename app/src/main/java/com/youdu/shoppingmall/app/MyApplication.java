package com.youdu.shoppingmall.app;

import android.app.Application;

/**
 * @author YonStone
 * @date 2019/07/15
 * @description
 */
public class MyApplication extends Application {

    private static MyApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static MyApplication getInstance() {
        return mApplication;
    }
}
