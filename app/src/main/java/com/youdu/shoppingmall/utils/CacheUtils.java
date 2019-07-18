package com.youdu.shoppingmall.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author YonStone
 * @date 2019/07/18
 * @description
 */
public class CacheUtils {
    public static final String SP_NAME = "News";

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static void putBoolean(Context context, String key, boolean b) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, b).commit();
    }

    public static String getString(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }
}
