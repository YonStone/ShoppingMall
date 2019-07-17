package com.youdu.shoppingmall.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.base.BaseFragment;
import com.youdu.shoppingmall.home.adapter.HomeRecyclerAdapter;
import com.youdu.shoppingmall.home.bean.ResultBeanData;
import com.youdu.shoppingmall.network.http.RequestCenter;
import com.youdu.yonstone_sdk.okhttp.listener.DisposeDataListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YonStone
 * @date 2019/07/15
 * @description
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.tv_search_home)
    TextView tvSearchHome;
    @Bind(R.id.tv_message_home)
    TextView tvMessageHome;
    @Bind(R.id.rv_home)
    RecyclerView rvHome;
    @Bind(R.id.ib_top)
    ImageButton ibTop;

    private ResultBeanData.ResultBean resultBean;
    private HomeRecyclerAdapter adapter;

    @Override
    protected View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        RequestCenter.requestRecommendData(ResultBeanData.class, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.e(TAG, "onSuccess: " + responseObj.toString());
                resultBean = ((ResultBeanData) responseObj).getResult();
//
//                Toast.makeText(mContext, "" + resultBean.getHot_info().get(0).getName(), Toast
//                        .LENGTH_SHORT).show();


                if (resultBean != null) {
                    rvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
//                    TestAdapter adapter = new TestAdapter(getActivity());
//                    rvHome.setAdapter(adapter);
                    adapter = new HomeRecyclerAdapter(getActivity(), resultBean);
                    rvHome.setAdapter(adapter);
//                    GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);

                    //设置滑动到哪个位置了的监听
//                    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                        @Override
//                        public int getSpanSize(int position) {
//                            if (position <= 3) {
//                                ib_top.setVisibility(View.GONE);
//                            } else {
//                                ib_top.setVisibility(View.VISIBLE);
//                            }
//                            return 1;
//                        }
//                    });
                    //设置网格布局
//                    rvHome.setLayoutManager(manager);
                } else {

                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                Toast.makeText(mContext, "失败", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: " + reasonObj.toString());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_search_home, R.id.tv_message_home, R.id.ib_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search_home:
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home:
                Toast.makeText(mContext, "信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_top:
                rvHome.scrollToPosition(0);
                break;
            default:
        }
    }
}
