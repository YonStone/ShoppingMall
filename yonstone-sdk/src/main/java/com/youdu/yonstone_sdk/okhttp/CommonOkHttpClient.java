package com.youdu.yonstone_sdk.okhttp;

import android.content.Context;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import com.youdu.yonstone_sdk.okhttp.https.HttpsUtils;
import com.youdu.yonstone_sdk.okhttp.listener.DisposeDataHandle;
import com.youdu.yonstone_sdk.okhttp.response.CommonJsonCallback;


/**
 * @author YonStone
 * @date 2019/07/01
 * @description 请求的发送, 请求参数的配置, https支持
 */
public class CommonOkHttpClient {

    //超时参数
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    //为我们的client配置参数
    static {
        //创建client对象构建者
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                //为构建者填充超时时间
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .followRedirects(true)
                //https支持
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
        //生成client对象
        mOkHttpClient = okHttpBuilder.build();
    }

    /**
     * 发送具体的http/https请求
     *
     * @param request
     * @param handle
     * @return
     */
    public static Call sendRequest(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }
}
