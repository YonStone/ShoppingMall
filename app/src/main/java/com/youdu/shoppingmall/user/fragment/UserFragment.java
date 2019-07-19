package com.youdu.shoppingmall.user.fragment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YonStone
 * @date 2019/07/15
 * @description
 */
public class UserFragment extends BaseFragment {
    @Bind(R.id.ib_user_icon_avator)
    ImageButton ibUserIconAvator;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.tv_all_order)
    TextView tvAllOrder;
    @Bind(R.id.tv_user_pay)
    TextView tvUserPay;
    @Bind(R.id.tv_user_receive)
    TextView tvUserReceive;
    @Bind(R.id.tv_user_finish)
    TextView tvUserFinish;
    @Bind(R.id.tv_user_drawback)
    TextView tvUserDrawback;
    @Bind(R.id.tv_user_location)
    TextView tvUserLocation;
    @Bind(R.id.tv_user_collect)
    TextView tvUserCollect;
    @Bind(R.id.tv_user_coupon)
    TextView tvUserCoupon;
    @Bind(R.id.tv_user_score)
    TextView tvUserScore;
    @Bind(R.id.tv_user_prize)
    TextView tvUserPrize;
    @Bind(R.id.tv_user_ticket)
    TextView tvUserTicket;
    @Bind(R.id.tv_user_invitation)
    TextView tvUserInvitation;
    @Bind(R.id.tv_user_callcenter)
    TextView tvUserCallcenter;
    @Bind(R.id.tv_user_feedback)
    TextView tvUserFeedback;
    @Bind(R.id.scrollview)
    ScrollView scrollview;
    @Bind(R.id.tv_usercenter)
    TextView tvUsercenter;
    @Bind(R.id.ib_user_setting)
    ImageButton ibUserSetting;
    @Bind(R.id.ib_user_message)
    ImageButton ibUserMessage;
    private TextView textView;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.ib_user_icon_avator, R.id.ib_user_setting, R.id.ib_user_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_user_icon_avator:
//                Intent intent = new Intent(mContext, LoginActivity.class);
//                startActivity(intent);
                break;
            case R.id.ib_user_setting:
                Toast.makeText(mContext, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_user_message:
//                Intent intent = new Intent(mContext, MessageCenterActivity.class);
//                startActivity(intent);
                break;
        }
    }
}
