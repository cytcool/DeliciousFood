package com.example.cyt.deliciousfood;

/**
 * Created by CYT on 2018/1/23.
 */

public class FoodData {

    public static final int CHINESE_FOOD = 1;

    public static final int FAST_FOOD = 2;

    public static final int DESERT_FOOD = 3;

    //美食名称
    private String name;

    //美食图片资源
    private int imgResId;

    //美食价格
    private int price;

    /*
    美食类型
    CHINESE_FOOD
    FAST_FOOD
    DESERT_FOOD
     */
    private int type;

    //美食评分
    private float rating;

    //美食简介
    private String description;

    //是否辣
    private boolean isSpicy;

    public FoodData(String name, int imgResId, int price, int type, boolean isSpicy, float rating, String description) {
        this.name = name;
        this.imgResId = imgResId;
        this.price = price;
        this.type = type;
        this.isSpicy = isSpicy;
        this.rating = rating;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSpicy() {
        return isSpicy;
    }

    public void setSpicy(boolean spicy) {
        isSpicy = spicy;
    }
}
