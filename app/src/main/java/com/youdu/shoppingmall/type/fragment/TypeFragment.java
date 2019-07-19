package com.youdu.shoppingmall.type.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author YonStone
 * @date 2019/07/15
 * @description Type主fragment, 容纳分类, 标签两个fragment
 */
public class TypeFragment extends BaseFragment {

    @Bind(R.id.tl_1)
    SegmentTabLayout segmentTabLayout;
    @Bind(R.id.iv_type_search)
    ImageView ivTypeSearch;
    @Bind(R.id.fl_type)
    FrameLayout flType;

    private List<BaseFragment> fragmentList;
    private Fragment tempFragment;
    private TypeListFragment listFragment;
    private TypeTagFragment tagFragment;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        initFragment();

        String[] titles = {"分类", "标签"};
        segmentTabLayout.setTabData(titles);
        segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(tempFragment, fragmentList.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        listFragment = new TypeListFragment();
        tagFragment = new TypeTagFragment();

        fragmentList.add(listFragment);
        fragmentList.add(tagFragment);

        switchFragment(tempFragment, fragmentList.get(0));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getActivity().
                        getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.fl_type, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

}
