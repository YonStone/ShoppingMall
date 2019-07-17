package com.youdu.shoppingmall.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.network.http.HttpConstants;
import com.youdu.yonstone_sdk.imageloader.ImageLoaderManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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
            View view = mLayoutInflater.inflate(R.layout.itme_banner, null);
            return new BannerViewHolder(view, resultBean);
        } else if (i == CHANNEL) {
            View view = mLayoutInflater.inflate(R.layout.item_channel, null);
            return new ChanelViewHolder(view);
        } else if (i == ACT) {
            View view = mLayoutInflater.inflate(R.layout.item_act, null);
            return new ActViewHolder(view);
        } else if (i == SECKILL) {
            View view = mLayoutInflater.inflate(R.layout.item_seckill, null);
            return new SeckillHolder(view);
        } else if (i == RECOMMEND) {
            View view = mLayoutInflater.inflate(R.layout.item_recommend, null);
            return new RecommendHolder(view);
        } else if (i == HOT) {
            View view = mLayoutInflater.inflate(R.layout.item_hot, null);
            return new HotHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (getItemViewType(i) == BANNER) {
            BannerViewHolder bannerHolder = (BannerViewHolder) holder;
            bannerHolder.setData(resultBean.getBanner_info());
        } else if (getItemViewType(i) == CHANNEL) {
            ChanelViewHolder chanelHolder = (ChanelViewHolder) holder;
            chanelHolder.setData(resultBean.getChannel_info());
        } else if (getItemViewType(i) == ACT) {
            ActViewHolder actHolder = (ActViewHolder) holder;
            actHolder.setData(resultBean.getAct_info());
        } else if (getItemViewType(i) == SECKILL) {
            SeckillHolder seckillHolder = (SeckillHolder) holder;
            seckillHolder.setData(resultBean.getSeckill_info());
        } else if (getItemViewType(i) == RECOMMEND) {
            RecommendHolder recommendHolder = (RecommendHolder) holder;
            recommendHolder.setData(resultBean.getRecommend_info());
        } else if (getItemViewType(i) == HOT) {
            HotHolder hotHolder = (HotHolder) holder;
            hotHolder.setData(resultBean.getHot_info());
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

    class HotHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_more_hot)
        TextView tvMoreHot;
        @Bind(R.id.gv_hot)
        GridView gvHot;

        public HotHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(final List<ResultBean.HotInfoBean> data) {
            HotViewAdapter adapter = new HotViewAdapter(mContext, data);
            gvHot.setAdapter(adapter);
            gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                }
            });
        }
    }

    class RecommendHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_more_recommend)
        TextView tvMoreRecommend;
        @Bind(R.id.gv_recommend)
        GridView gvRecommend;

        RecommendHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(List<ResultBean.RecommendInfoBean> data) {
            RecommendAdapter adapter = new RecommendAdapter(mContext, data);
            gvRecommend.setAdapter(adapter);
            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContext, "" + i, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 倒计时
     */
    private boolean isFirst = true;
    private int dt;
    private TextView tvTime;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                dt = dt - 1000;
                SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
                tvTime.setText(sd.format(new Date(dt)));

                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 1000);
                if (dt == 0) {
                    handler.removeMessages(0);
                }
            }

        }
    };

    /**
     * 秒杀holder
     */
    class SeckillHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_time_seckill)
        TextView tvTimeSeckill;
        @Bind(R.id.tv_more_seckill)
        TextView tvMoreSeckill;
        @Bind(R.id.rv_seckill)
        RecyclerView rvSeckill;

        public SeckillHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            tvTime = itemView.findViewById(R.id.tv_time_seckill);
        }

        public void setData(final ResultBean.SeckillInfoBean data) {
            //设置时间
            if (isFirst) {
                dt = (Integer.parseInt(data.getEnd_time()) -
                        (Integer.parseInt(data.getStart_time())));
                isFirst = false;
            }
            //设置水平列表
            rvSeckill.setLayoutManager(new LinearLayoutManager(mContext,
                    LinearLayoutManager.HORIZONTAL, false));
            SeckillAdapter adapter = new SeckillAdapter(mContext, data);
            rvSeckill.setAdapter(adapter);
            //倒计时
            handler.sendEmptyMessageDelayed(0, 1000);
            adapter.setOnSeckillRecyclerView(new SeckillAdapter.OnSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {
        private ViewPager actViewPager;

        public ActViewHolder(@NonNull View itemView) {
            super(itemView);
            actViewPager = itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<ResultBean.ActInfoBean> data) {
            actViewPager.setPageMargin(20);
            actViewPager.setOffscreenPageLimit(3);
            actViewPager.setPageTransformer(true,
                    new AlphaPageTransformer(new ScaleInTransformer()));
            actViewPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return data.size();
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    ImageView view = new ImageView(mContext);
                    view.setScaleType(ImageView.ScaleType.FIT_XY);
                    //绑定数据
                    mImageLoader.displayImage(view, HttpConstants.Base_URl_IMAGE
                            + data.get(position).getIcon_url());
                    container.addView(view);
                    return view;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
            actViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    Toast.makeText(mContext, "position:" + i, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
        }
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
