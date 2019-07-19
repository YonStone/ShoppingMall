package com.youdu.shoppingmall.type.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.base.BaseFragment;
import com.youdu.shoppingmall.network.http.Constants;
import com.youdu.shoppingmall.network.http.RequestCenter;
import com.youdu.shoppingmall.type.adapter.TypeLeftAdapter;
import com.youdu.shoppingmall.type.adapter.TypeRightAdapter;
import com.youdu.shoppingmall.type.bean.TypeBean;
import com.youdu.yonstone_sdk.okhttp.listener.DisposeDataListener;

import java.util.List;

/**
 * 分类页面
 * A simple {@link Fragment} subclass.
 */
public class TypeListFragment extends BaseFragment {
    private FrameLayout fl_list_container;
    private ListView lv_left;
    private RecyclerView rv_right;
    private List<TypeBean.ResultBean> result;

    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants
            .PANTS_URL, Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants
            .HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};

    private TypeLeftAdapter leftAdapter;
    private TypeRightAdapter rightAdapter;
    private boolean isFirst = true;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type_list, null);
        lv_left = view.findViewById(R.id.lv_left);
        rv_right = view.findViewById(R.id.rv_right);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //联网请求
        getDataFromNet(urls[0]);
    }

    private void processData(String json) {
        Gson gson = new Gson();
        TypeBean typeBean = gson.fromJson(json, TypeBean.class);
        result = typeBean.getResult();
    }

    private void getDataFromNet(String url) {
        RequestCenter.requestTypeData(url, null, new DisposeDataListener() {
            @Override
            public void onSuccess(Object response) {
                if (response != null) {
                    //解析数据
                    processData(response.toString());
                    //分类列表只在第一次时候设置adapter
                    if (isFirst) {
                        leftAdapter = new TypeLeftAdapter(mContext);
                        lv_left.setAdapter(leftAdapter);
                    }

                    initListener();

                    //解析右边数据
                    TypeRightAdapter rightAdapter = new TypeRightAdapter(mContext, result);
                    rv_right.setAdapter(rightAdapter);

                    GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);

                    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            if (position == 0) {
                                return 3;
                            } else {
                                return 1;
                            }
                        }
                    });
                    rv_right.setLayoutManager(manager);
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initListener() {
        if (leftAdapter == null) {
            return;
        }
        //点击监听
        lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //刷新
                leftAdapter.changeSelected(position);
                if (position != 0) {
                    isFirst = false;
                }
                getDataFromNet(urls[position]);
                leftAdapter.notifyDataSetChanged();
            }
        });
    }
}