package com.youdu.shoppingmall.network.http;

import com.youdu.yonstone_sdk.okhttp.CommonOkHttpClient;
import com.youdu.yonstone_sdk.okhttp.listener.DisposeDataHandle;
import com.youdu.yonstone_sdk.okhttp.listener.DisposeDataListener;
import com.youdu.yonstone_sdk.okhttp.request.CommonRequest;
import com.youdu.yonstone_sdk.okhttp.request.RequestParams;

/**
 * @author YonStone
 * @date 2019/07/02
 * @description 存放应用中所有请求
 */
public class RequestCenter {
    //根据参数发送 所有post请求
    private static void postRequest(String url, RequestParams params,
                                    DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.sendRequest(
                CommonRequest.createPostRequest(url, params), new DisposeDataHandle(listener,
                        clazz)
        );
    }

    public static void requestRecommendData(Class<?> clazz, DisposeDataListener listener) {
        RequestCenter.postRequest(Constants.HOME_URL, null,
                listener, clazz);
    }
}
