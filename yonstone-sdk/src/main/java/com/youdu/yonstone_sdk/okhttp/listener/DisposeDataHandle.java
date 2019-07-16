package com.youdu.yonstone_sdk.okhttp.listener;

/**
 * @author YonStone
 * @date 2019/07/01
 * @description 封装相应回调和需要转换的字节码对象
 */
public class DisposeDataHandle {
    public DisposeDataListener mListener;
    public Class<?> mClass;

    public DisposeDataHandle(DisposeDataListener listener) {
        mListener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener, Class<?> aClass) {
        mListener = listener;
        mClass = aClass;
    }
}
