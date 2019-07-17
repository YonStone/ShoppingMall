package com.youdu.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.network.http.HttpConstants;
import com.youdu.yonstone_sdk.imageloader.ImageLoaderManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.youdu.shoppingmall.home.bean.ResultBeanData.ResultBean;

/**
 * @author YonStone
 * @date 2019/07/17
 * @description 首页channel
 */
public class ChannelAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBean.ChannelInfoBean> channelInfo;
    private ImageLoaderManager imageLoader;

    public ChannelAdapter(Context context, List<ResultBean.ChannelInfoBean> channelInfo) {
        mContext = context;
        this.channelInfo = channelInfo;
        imageLoader = ImageLoaderManager.getInstance(mContext);
    }

    @Override
    public int getCount() {
        return channelInfo == null ? 0 : channelInfo.size();
    }

    @Override
    public Object getItem(int i) {
        return channelInfo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_channel_grid, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ResultBean.ChannelInfoBean channelInfoBean = channelInfo.get(i);
        holder.tvChannel.setText(channelInfoBean.getChannel_name());
        imageLoader.displayImage(
                holder.ivChannel, HttpConstants.Base_URl_IMAGE + channelInfoBean.getImage());
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.iv_channel)
        ImageView ivChannel;
        @Bind(R.id.tv_channel)
        TextView tvChannel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
