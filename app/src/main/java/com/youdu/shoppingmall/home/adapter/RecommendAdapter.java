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
 * @description 首页推荐
 */
public class RecommendAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBean.RecommendInfoBean> data;
    private ImageLoaderManager imageLoader;

    public RecommendAdapter(Context mContext, List<ResultBean.RecommendInfoBean> data) {
        this.mContext = mContext;
        this.data = data;
        imageLoader = ImageLoaderManager.getInstance(mContext);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ResultBean.RecommendInfoBean recommendInfoBean = data.get(i);
        imageLoader.displayImage(holder.ivRecommend, HttpConstants.Base_URl_IMAGE +
                recommendInfoBean.getFigure());
        holder.tvName.setText(recommendInfoBean.getName());
        holder.tvPrice.setText("¥" + recommendInfoBean.getCover_price());
        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.iv_recommend)
        ImageView ivRecommend;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
