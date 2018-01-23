package com.example.cyt.deliciousfood;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CYT on 2018/1/23.
 */

public class FoodAPI {

    public static List<FoodData> getDemoFood(Context context){
        List<FoodData> foods = new ArrayList<>();
        foods = new ArrayList<>();
        foods.add(new FoodData("提拉米苏", R.drawable.tiramisu, 80, FoodData.DESERT_FOOD, false, 4.5f, context.getString(R.string.tiramisu)));
        foods.add(new FoodData("舒芙蕾", R.drawable.souffle, 65, FoodData.DESERT_FOOD, false, 4f, context.getString(R.string.souffle)));
        foods.add(new FoodData("欧培拉", R.drawable.opera, 48, FoodData.DESERT_FOOD, false, 3.5f, context.getString(R.string.opera)));
        foods.add(new FoodData("汉堡包", R.drawable.hamburger, 15, FoodData.FAST_FOOD, false, 4.0f, context.getString(R.string.hamburger)));
        foods.add(new FoodData("三明治", R.drawable.sanwich, 8, FoodData.FAST_FOOD, false, 4.5f, context.getString(R.string.sandwich)));
        foods.add(new FoodData("麦乐鸡", R.drawable.malejikuai, 10, FoodData.FAST_FOOD, false, 3.0f, context.getString(R.string.maleji)));
        foods.add(new FoodData("宫保鸡丁", R.drawable.gongbaojiding, 20, FoodData.CHINESE_FOOD, true, 5.0f, context.getString(R.string.gongbaojiding)));
        foods.add(new FoodData("鱼香肉丝", R.drawable.yuxiangrousi, 24, FoodData.CHINESE_FOOD, false, 4.0f, context.getString(R.string.yuxiangrousi)));
        foods.add(new FoodData("水煮肉片", R.drawable.shuizhurou, 32, FoodData.CHINESE_FOOD, true, 4.5f, context.getString(R.string.shuizhurou)));
        foods.add(new FoodData("红烧肉", R.drawable.hongshaorou, 38, FoodData.CHINESE_FOOD, false, 5.0f, context.getString(R.string.hongshaorou)));
        return foods;
    }
}
