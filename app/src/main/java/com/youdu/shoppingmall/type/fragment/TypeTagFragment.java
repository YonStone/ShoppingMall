package com.youdu.shoppingmall.type.fragment;

import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.google.gson.Gson;
import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.base.BaseFragment;
import com.youdu.shoppingmall.network.http.RequestCenter;
import com.youdu.shoppingmall.type.adapter.TagGridViewAdapter;
import com.youdu.shoppingmall.type.bean.TagBean;
import com.youdu.yonstone_sdk.okhttp.listener.DisposeDataListener;

import java.util.List;

/**
 * @author YonStone
 * @date 2019/07/19
 * @description
 */
public class TypeTagFragment extends BaseFragment {

    private GridView gv_tag;
    private TagGridViewAdapter adapter;
    private List<TagBean.ResultBean> result;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type_tag, null);
        gv_tag = view.findViewById(R.id.gv_tag);
        return view;
    }

    @Override
    public void initData() {
        getDataFromNet();
    }

    private void processData(String json) {
        Gson gson = new Gson();
        TagBean tagBean = gson.fromJson(json, TagBean.class);
        result = tagBean.getResult();
    }

    private void getDataFromNet() {
        RequestCenter.requestTagData(null, new DisposeDataListener() {
            @Override
            public void onSuccess(Object response) {
                if (response != null) {
                    processData(response.toString());
                    adapter = new TagGridViewAdapter(mContext, result);
                    gv_tag.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.e(TAG, "联网失败" + reasonObj);
            }
        });
    }
}
