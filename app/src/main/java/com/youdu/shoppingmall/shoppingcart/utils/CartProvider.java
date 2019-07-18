package com.youdu.shoppingmall.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youdu.shoppingmall.app.MyApplication;
import com.youdu.shoppingmall.home.bean.GoodsBean;
import com.youdu.shoppingmall.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YonStone
 * @date 2019/07/18
 * @description
 */
public class CartProvider {
    private static final String JSON_CART = "json_cart";

    private Context context;
    //SparseArray性能优于HashMap
    private SparseArray<GoodsBean> sparseArray;
    private static CartProvider instance;

    public CartProvider(Context context) {
        this.context = context;
        sparseArray = new SparseArray<>(100);
        listToSparse();
    }

    public static CartProvider getInstance() {
        if (instance == null) {
            instance = new CartProvider(MyApplication.getContext());
        }
        return instance;
    }

    //本地获取json数据,并通过Gson解析成list列表数据
    private List<GoodsBean> getDataFromLocal() {
        List<GoodsBean> carts = new ArrayList<>();
        String saveJson = CacheUtils.getString(context, JSON_CART);
        if (!TextUtils.isEmpty(saveJson)) {
            carts = new Gson().fromJson(saveJson, new TypeToken<List<GoodsBean>>() {
            }.getType());
        }
        return carts;
    }

    /**
     * 取数据时调用,把SP中存放json数据转成SparseArray,方便以键取值
     */
    private void listToSparse() {
        List<GoodsBean> carts = getDataFromLocal();
        //放到sparseArray中
        if (carts != null && carts.size() > 0) {
            for (int i = 0; i < carts.size(); i++) {
                GoodsBean goodsBean = carts.get(i);
                sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
            }
        }
    }

    /**
     * 存数据时调用,把SparseArray中数据转ArrayList
     */
    private List<GoodsBean> sparseToList() {
        List<GoodsBean> carts = new ArrayList<>();
        if (sparseArray != null && sparseArray.size() > 0) {
            for (int i = 0; i < sparseArray.size(); i++) {
                GoodsBean bean = sparseArray.valueAt(i);
                carts.add(bean);
            }
        }
        return carts;
    }

    //保存数据
    private void commit() {
        List<GoodsBean> carts = sparseToList();
        String json = new Gson().toJson(carts);
        CacheUtils.putString(context, JSON_CART, json);
    }

    public void addData(GoodsBean bean) {
        GoodsBean temp = sparseArray.get(Integer.parseInt(bean.getProduct_id()));
        if (temp != null) {
            temp.setNumber(temp.getNumber() + bean.getNumber());
        } else {
            temp = bean;
            temp.setNumber(1);
        }
        sparseArray.put(Integer.parseInt(temp.getProduct_id()), temp);
        commit();
    }

    public void deleteData(GoodsBean bean) {
        sparseArray.delete(Integer.parseInt(bean.getProduct_id()));
        commit();
    }

    public void updataData(GoodsBean bean) {
        sparseArray.put(Integer.parseInt(bean.getProduct_id()), bean);
        commit();
    }

    public GoodsBean findData(GoodsBean bean) {
        GoodsBean temp = sparseArray.get(Integer.parseInt(bean.getProduct_id()));
        if (temp != null) {
            return temp;
        }
        return null;
    }

    public List<GoodsBean> getAllData() {
        return getDataFromLocal();
    }
}
