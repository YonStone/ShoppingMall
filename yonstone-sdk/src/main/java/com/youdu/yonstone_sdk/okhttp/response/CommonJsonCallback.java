package com.youdu.yonstone_sdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import com.youdu.yonstone_sdk.okhttp.exception.OkHttpException;
import com.youdu.yonstone_sdk.okhttp.listener.DisposeDataHandle;
import com.youdu.yonstone_sdk.okhttp.listener.DisposeDataListener;
import com.youdu.yonstone_sdk.util.ResponseEntityToModule;

/**
 * @author YonStone
 * @date 2019/07/01
 * @description 专门处理JSON的回调响应
 */
public class CommonJsonCallback implements Callback {
    /**
     * the logic layer exception, may alter in different app
     */
    protected final String RESULT_CODE = "code"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 200;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";
    protected final String COOKIE_STORE = "Set-Cookie"; // decide the server it
    // can has the value of
    // set-cookie2

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    private Handler mDeliveryHandler;//进行消息的转发
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        mListener = handle.mListener;
        mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    //请求失败处理
    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    //真正的响应处理函数
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    /**
     * 处理服务器返回的响应数据
     */
    private void handleResponse(Object responseObj) {
        if (responseObj == null || responseObj.toString().isEmpty()) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        try {
            /**
             * 协议确定后看这里如何修改
             */
            JSONObject result = new JSONObject(responseObj.toString());
            if (result.has(RESULT_CODE)) {
                //从json对象中取出我们的响应码,若为0,则是正确的响应
                if (result.getInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    //不需要解析
                    if (mClass == null) {
                        mListener.onSuccess(responseObj);
                    } else {
                        //即,需要我们将json对象转化为实体对象
                        Object obj = ResponseEntityToModule.parseJsonObjectToModule(result, mClass);
                        if (obj != null) {
                            mListener.onSuccess(obj);
                        } else {
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                }
            } else {
                //将服务器返回的异常回调到应用层去处理
                mListener.onFailure(new OkHttpException(OTHER_ERROR, result.get(RESULT_CODE)));
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
        }
    }
}
