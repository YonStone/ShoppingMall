package com.youdu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.network.http.HttpConstants;
import com.youdu.yonstone_sdk.imageloader.ImageLoaderManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

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
        mLayoutInflater = LayoutInflater.from(mContext);
        mImageLoader = ImageLoaderManager.getInstance(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == BANNER) {
            View itemView = mLayoutInflater.inflate(R.layout.itme_home_banner, null);
            return new BannerViewHolder(itemView, resultBean);
        } else if (i == CHANNEL) {
            View itemView = mLayoutInflater.inflate(R.layout.item_home_channel, null);
            return new ChanelViewHolder(itemView);
        } else {
            View itemView = mLayoutInflater.inflate(R.layout.itme_home_banner, null);
            return new BannerViewHolder(itemView, resultBean);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (getItemViewType(i) == BANNER) {
            BannerViewHolder bannerHolder = (BannerViewHolder) holder;
            bannerHolder.setData(resultBean.getBanner_info());
        } else if (getItemViewType(i) == CHANNEL) {
            ChanelViewHolder chanelHolder = (ChanelViewHolder) holder;
            chanelHolder.setData(resultBean.getChannel_info());
        }
    }

    /**
     * 在item少且排版固定的情况,根据position来确定holder类型
     * 当其他情况,应把type信息放在bean中
     *
     * @param position
     * @return
     */
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

    class ChanelViewHolder extends RecyclerView.ViewHolder {
        private GridView gvChannel;

        public ChanelViewHolder(@NonNull View itemView) {
            super(itemView);
            gvChannel = itemView.findViewById(R.id.gv_channel);
        }

        public void setData(final List<ResultBean.ChannelInfoBean> channelBeans) {
            gvChannel.setAdapter(new ChannelAdapter(mContext, channelBeans));
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContext, "" + i, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        public Banner banner;
        public ResultBean resultBean;

        public BannerViewHolder(@NonNull View itemView, ResultBean resultBean) {
            super(itemView);
            this.resultBean = resultBean;

            banner = itemView.findViewById(R.id.banner);
        }

        public void setData(final List<ResultBean.BannerInfoBean> bannerInfo) {
            final List<String> imageUris = new ArrayList<>();
            for (int i = 0; i < bannerInfo.size(); i++) {
                imageUris.add(bannerInfo.get(i).getImage());
            }

            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            // 设置类似手风琴动画
            banner.setBannerAnimation(Transformer.Accordion);
            // 设置加载图片
            banner.setImages(imageUris, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    mImageLoader.displayImage(view, HttpConstants.Base_URl_IMAGE + url);
                }
            });
            //设置点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "点击了" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
