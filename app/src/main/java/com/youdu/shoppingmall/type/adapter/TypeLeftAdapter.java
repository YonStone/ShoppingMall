package com.youdu.shoppingmall.type.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youdu.shoppingmall.R;

/**
 * Created by Administrator on 2016/10/3.
 * 分类左边类型adapter
 */
public class TypeLeftAdapter extends BaseAdapter {
    private Context mContext;
    //选中项
    private int mSelect;
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品",
            "办公文具", "数码周边", "游戏专区"};

    public TypeLeftAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_type, null);
            holder = new ViewHolder();
            holder.tv_name = convertView.findViewById(R.id.tv_title);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_name.setText(titles[position]);

        if (mSelect == position) {
            //选中项背景
            convertView.setBackgroundResource(R.drawable.type_item_background_selector);
            holder.tv_name.setTextColor(Color.parseColor("#fd3f3f"));
        } else {
            //其他项背景
            convertView.setBackgroundResource(R.drawable.bg2);
            holder.tv_name.setTextColor(Color.parseColor("#323437"));
        }
        return convertView;
    }

    public void changeSelected(int position) { //刷新方法
        if (position != mSelect) {
            mSelect = position;
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        private TextView tv_name;
    }
}
