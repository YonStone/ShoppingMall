package com.youdu.shoppingmall.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.home.bean.GoodsBean;
import com.youdu.shoppingmall.network.http.Constants;
import com.youdu.shoppingmall.shoppingcart.utils.CartProvider;
import com.youdu.shoppingmall.shoppingcart.view.NumberAddSubView;
import com.youdu.yonstone_sdk.imageloader.ImageLoaderManager;

import java.util.Iterator;
import java.util.List;

/**
 * @author YonStone
 * @date 2019/07/15
 * @description
 */
public class ShopCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<GoodsBean> datas;
    private ImageLoaderManager imageLoader;

    private CartProvider cartProvider;
    private TextView tvShopcartTotal;
    private CheckBox checkAll;
    private CheckBox checkAllCancel;

    public ShopCartAdapter(Context context, final List<GoodsBean> datas, CartProvider cartProvider,
                           TextView tvShopcartTotal, CheckBox checkAll, CheckBox checkAllCancel) {
        this.mContext = context;
        this.datas = datas;
        this.tvShopcartTotal = tvShopcartTotal;
        this.cartProvider = cartProvider;
        this.checkAll = checkAll;
        this.checkAllCancel = checkAllCancel;
        imageLoader = ImageLoaderManager.getInstance(mContext);
        initView();
    }

    private void initView() {
        //首次加载数据
        checkAll.setChecked(true);
        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setIsChildSelected(true);
        }
        showTotalPrice();

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                GoodsBean goodsBean = datas.get(position);
                goodsBean.setIsChildSelected(!goodsBean.isChildSelected());
                notifyItemChanged(position);
                checkAll();
                showTotalPrice();
            }
        });

        //设置全选点击事件
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = checkAll.isChecked();
                checkAll_none(checked);
                showTotalPrice();
            }
        });

        checkAllCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = checkAllCancel.isChecked();
                checkAll_none(checked);
                showTotalPrice();
            }
        });
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_shop_cart, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 点击单个按钮时,检索设置所有item和另一个按钮选中状态
     *
     * @param checked
     */
    public void checkAll_none(boolean checked) {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                datas.get(i).setIsChildSelected(checked);
                checkAll.setChecked(checked);
                checkAllCancel.setChecked(checked);
                notifyItemChanged(i);
            }
        } else {
            checkAll.setChecked(false);
            checkAllCancel.setChecked(false);
        }
    }

    /**
     * 点击item时,检索设置两个按钮是否被选中
     */
    public void checkAll() {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                if (!datas.get(i).isChildSelected()) {
                    checkAll.setChecked(false);
                    checkAllCancel.setChecked(false);
                    return;
                } else {
                    checkAll.setChecked(true);
                    checkAllCancel.setChecked(true);
                }
            }
        }
    }

    public void deleteData() {
        if (datas != null && datas.size() > 0) {
            for (Iterator iterator = datas.iterator(); iterator.hasNext(); ) {
                GoodsBean cart = (GoodsBean) iterator.next();
                if (cart.isChildSelected()) {
                    //这行代码放在前面
                    int position = datas.indexOf(cart);
                    //1.删除本地缓存的
                    cartProvider.deleteData(cart);
                    //2.删除当前内存的
                    iterator.remove();
                    //3.刷新数据
                    notifyItemRemoved(position);
                }
            }
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cbGov;
        private ImageView ivGov;
        private TextView tvDescGov;
        private TextView tvPriceGov;
        private NumberAddSubView numberAddSubView;

        ViewHolder(View itemView) {
            super(itemView);
            cbGov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            ivGov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tvDescGov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tvPriceGov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            numberAddSubView = (NumberAddSubView) itemView.findViewById(R.id.numberAddSubView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickListener(v, getLayoutPosition());
                    }
                }
            });
        }

        public void setData(final GoodsBean goodsBean) {
            cbGov.setChecked(goodsBean.isChildSelected());
            imageLoader.displayImage(ivGov, Constants.BASE_URl_IMAGE + goodsBean.getFigure());
            tvDescGov.setText(goodsBean.getName());
            tvPriceGov.setText("￥" + goodsBean.getCover_price());

            //设置数字加减回调
            numberAddSubView.setValue(goodsBean.getNumber());
            numberAddSubView.setOnNumberChangeListener(new NumberAddSubView
                    .OnNumberChangeListener() {
                @Override
                public void addNumber(View view, int value) {
                    goodsBean.setNumber(value);
                    cartProvider.updataData(goodsBean);
                    showTotalPrice();
                }

                @Override
                public void subNumber(View view, int value) {
                    goodsBean.setNumber(value);
                    cartProvider.updataData(goodsBean);
                    showTotalPrice();
                }
            });
        }
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText(getTotalPrice() + "");
    }

    private double getTotalPrice() {
        double total = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isChildSelected()) {
                    total += Double.parseDouble(goodsBean.getCover_price()) * Double.parseDouble
                            (goodsBean.getNumber() + "");
                }
            }
        }
        return total;
    }

    //回调点击事件的监听
    private OnItemClickListener onItemClickListener;

    interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
