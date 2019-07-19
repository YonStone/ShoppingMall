package com.youdu.shoppingmall.type.fragment;

import android.view.View;

import com.youdu.shoppingmall.R;
import com.youdu.shoppingmall.base.BaseFragment;

/**
 * @author YonStone
 * @date 2019/07/19
 * @description
 */
public class TypeTagFragment extends BaseFragment {

//    private GridView gv_tag;
//    private TagGridViewAdapter adapter;
//    private List<TagBean.ResultBean> result;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type_tag, null);
//        gv_tag = (GridView) view.findViewById(R.id.gv_tag);
        return view;
    }

    @Override
    public void initData() {
//        getDataFromNet();
    }

//
//    public void getDataFromNet() {
//        OkHttpUtils
//                .get()
//                .url(Constants.TAG_URL)
//                .id(100)
//                .build()
//                .execute(new MyStringCallback());
//    }
//
//    public class MyStringCallback extends StringCallback {
//
//
//        @Override
//        public void onBefore(Request request, int id) {
//        }
//
//        @Override
//        public void onAfter(int id) {
//        }
//
//        @Override
//        public void onError(Call call, Exception e, int id) {
//            Log.e("TAG", "联网失败" + e.getMessage());
//        }
//
//        @Override
//        public void onResponse(String response, int id) {
//
//            switch (id) {
//                case 100:
////                    Toast.makeText(mContext, "http", Toast.LENGTH_SHORT).show();
//                    if (response != null) {
//                        processData(response);
//                        adapter = new TagGridViewAdapter(mContext, result);
//                        gv_tag.setAdapter(adapter);
//                    }
//                    break;
//                case 101:
//                    Toast.makeText(mContext, "https", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//
//    }
//
//    private void processData(String json) {
//        Gson gson = new Gson();
//        TagBean tagBean = gson.fromJson(json, TagBean.class);
//        result = tagBean.getResult();
//    }
}
