package com.youdu.yonstone_sdk.okhttp.listener;

/**
 * @author YonStone
 * @date 2019/07/01
 * @description 自定义事件监听
 */
public interface DisposeDataListener {
    /**
     * 请求成功回调
     *
     * @param responseObj
     */
    void onSuccess(Object responseObj);

    /**
     * 请求失败回调
     *
     * @param reasonObj
     */
    void onFailure(Object reasonObj);
}
