package com.youdu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.home.bean.ResultBeanData.ResultBean;
import com.youdu.shoppingmall.network.http.Constants;
import com.youdu.yonstone_sdk.imageloader.ImageLoaderManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author YonStone
 * @date 2019/07/17
 * @description
 */
public class SeckillAdapter extends RecyclerView.Adapter<SeckillAdapter.ViewHolder> {
    private Context mContext;
    private ResultBean.SeckillInfoBean data;
    private final List<ResultBean.SeckillInfoBean.ListBean> list;
    private ImageLoaderManager imageLoader;

    public SeckillAdapter(Context context, ResultBean.SeckillInfoBean data) {
        mContext = context;
        this.data = data;
        list = data.getList();
        imageLoader = ImageLoaderManager.getInstance(mContext);
    }

    @NonNull
    @Override
    public SeckillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.item_seckill_recycler, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.setData(i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_figure)
        ImageView ivFigure;
        @Bind(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @Bind(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @Bind(R.id.ll_root)
        LinearLayout llRoot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(final int position) {
            ResultBean.SeckillInfoBean.ListBean listBean =
                    list.get(position);
            tvCoverPrice.setText("¥" + listBean.getCover_price());
            tvOriginPrice.setText("¥" + listBean.getOrigin_price());
            imageLoader.displayImage(ivFigure, Constants.BASE_URl_IMAGE + listBean.getFigure());
            llRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onSeckillRecyclerView != null) {
                        onSeckillRecyclerView.onItemClick(position);
                    }
                }
            });
        }
    }

    public interface OnSeckillRecyclerView {
        void onItemClick(int position);
    }

    private OnSeckillRecyclerView onSeckillRecyclerView;

    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView) {
        this.onSeckillRecyclerView = onSeckillRecyclerView;
    }
}
