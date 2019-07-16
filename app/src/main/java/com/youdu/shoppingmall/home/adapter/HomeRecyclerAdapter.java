package com.youdu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.network.http.HttpConstants;
import com.youdu.yonstone_sdk.imageloader.ImageLoaderManager;


import java.util.ArrayList;
import java.util.List;

import static com.youdu.shoppingmall.home.bean.ResultBeanData.ResultBean;

/**
 * @author YonStone
 * @date 2019/07/16
 * @description
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter {
    public static final String GOODS_BEAN = "goods_bean";
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 数据Bean对象
     */
    private ResultBean resultBean;
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;

    /**
     * 当前类型
     */
    public int currentType = BANNER;

    private final LayoutInflater mLayoutInflater;
    private ImageLoaderManager mImageLoader;

    public HomeRecyclerAdapter(Context context, ResultBean resultBean) {
        mContext = context;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(context);
        mImageLoader = ImageLoaderManager.getInstance(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == BANNER) {
            View itemView = mLayoutInflater.inflate(R.layout.banner_viewpager, null);
            return new BannerViewHolder(itemView, mContext, resultBean);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (getItemViewType(i) == BANNER) {
            BannerViewHolder bannerHolder = (BannerViewHolder) holder;
            bannerHolder.setData(resultBean.getBanner_info());
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
            default:
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        public Context mContext;
//        public Banner banner;
        public ResultBean resultBean;

        public BannerViewHolder(@NonNull View itemView, Context context, ResultBean resultBean) {
            super(itemView);
//            banner = itemView.findViewById(R.id.banner);
            this.mContext = context;
            this.resultBean = resultBean;
        }

        public void setData(final List<ResultBean.BannerInfoBean> bannerInfo) {
            List<String> imageUris = new ArrayList<>();
            for (int i = 0; i < resultBean.getBanner_info().size(); i++) {
                imageUris.add(resultBean.getBanner_info().get(i).getImage());
            }

//            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//            // 设置类似手风琴动画
//            banner.setBannerAnimation(Transformer.Accordion);
//            // 设置加载图片
//            banner.setImages(imageUris, new OnLoadImageListener() {
//                @Override
//                public void OnLoadImage(ImageView view, Object url) {
//                    mImageLoader.displayImage(view, HttpConstants.Base_URl_IMAGE + url);
//                }
//            });
//             设置点击事件
//            banner.setOnBannerClickListener(new OnBannerClickListener()) {
//                @Override
//                public void OnBannerClick(int position) {
//                    int realPosition = position - 1;
//                    if (realPosition < bannerInfo.size()) {
//                        String product_id = "";
//                        String name = "";
//                        String cover_price = "";
//                        if (realPosition == 0) {
//                            product_id = "627";
//                            cover_price = "32.00";
//                            name = "剑三 T 恤批发";
//                        } else if (realPosition == 1) {
//                            product_id = "21";
//                            cover_price = "8.00";
//                            name = "同人原创】剑网 3 剑侠情缘叁 Q 版成男 口袋 袋胸针 ";
//                        } else {
//                            product_id = "1341";
//                            cover_price = "50.00";
//                            name = "【蓝诺】《天下吾双》 剑网 3 同人本";
//                        }
//                        String image = bannerInfo.get(realPosition).getImage();
////                        GoodsBean goodsBean = new GoodsBean(name, cover_price, image, product_id);
//                    }
//                }
//            }

        }
    }
}
