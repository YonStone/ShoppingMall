package com.youdu.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.network.http.Constants;
import com.youdu.yonstone_sdk.imageloader.ImageLoaderManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.youdu.shoppingmall.home.bean.ResultBeanData.ResultBean;

/**
 * @author YonStone
 * @date 2019/07/17
 * @description
 */
public class HotViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBean.HotInfoBean> data;
    private ImageLoaderManager imageLoader;

    public HotViewAdapter(Context mContext, List<ResultBean.HotInfoBean> data) {
        this.mContext = mContext;
        this.data = data;
        imageLoader = ImageLoaderManager.getInstance(mContext);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ResultBean.HotInfoBean hotInfoBean = data.get(position);
        imageLoader.displayImage(holder.ivHot, Constants.BASE_URl_IMAGE +
                hotInfoBean.getFigure());
        holder.tvName.setText(hotInfoBean.getName());
        holder.tvPrice.setText("¥" + hotInfoBean.getCover_price());
        return convertView;

    }

    static class ViewHolder {
        @Bind(R.id.iv_hot)
        ImageView ivHot;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
