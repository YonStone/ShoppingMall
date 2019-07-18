package com.youdu.shoppingmall.home.bean;

import java.io.Serializable;

/**
 * @author YonStone
 * @date 2019/07/17
 * @description
 */
public class GoodsBean implements Serializable {

    /**
     * cover_price : 49.00
     * figure : /1438680345318.jpg
     * name : 【古风原创】 自动直柄伞 晴雨伞 【青竹词】包邮  新增折叠伞
     * origin_price : 59.00
     * product_id : 555
     */
    private String cover_price;
    private String figure;
    private String name;
    private String origin_price;
    private String product_id;
    private String url;
    private int number;
    /**
     * 是否被选中
     */
    private boolean isChildSelected;

    public GoodsBean() {
    }

    public GoodsBean(String name, String cover_price, String figure, String product_id) {
        this.name = name;
        this.cover_price = cover_price;
        this.figure = figure;
        this.product_id = product_id;
    }

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin_price() {
        return origin_price;
    }

    public void setOrigin_price(String origin_price) {
        this.origin_price = origin_price;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isChildSelected() {
        return isChildSelected;
    }

    public void setIsChildSelected(boolean childSelected) {
        isChildSelected = childSelected;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", origin_price='" + origin_price + '\'' +
                ", product_id='" + product_id + '\'' +
                ", url='" + url + '\'' +
                ", number=" + number +
                '}';
    }
}
