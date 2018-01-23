package com.example.cyt.deliciousfood;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by CYT on 2018/1/23.
 */

public class DetailActivity extends AppCompatActivity {

    private TextView mTvTitle;
    private ImageView mImageFood;
    private TextView mTvName;
    private TextView mTvPrice;
    private TextView mTvDesc;
    private RatingBar mRatingBar;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
        Bundle bundle = getIntent().getExtras();
        FoodData food = parseFoodFromBundle(bundle);
        loadViews(food);
    }

    @SuppressLint("DefaultLocale")
    private void loadViews(FoodData food) {
        mImageFood.setImageResource(food.getImgResId());
        mTvTitle.setText(food.getName());
        mTvName.setText(String.format("名称：%s",food.getName()));
        mTvPrice.setText(String.format("价格：%d",food.getPrice()));
        mTvDesc.setText(food.getDescription());
        mRatingBar.setRating(food.getRating());
    }

    private void initViews() {
        mTvTitle = findViewById(R.id.tv_title);
        mImageFood = findViewById(R.id.image_food);
        mRatingBar = findViewById(R.id.rating_bar);
        mTvDesc = findViewById(R.id.tv_description);
        mTvName = findViewById(R.id.tv_name);
        mTvPrice = findViewById(R.id.tv_price);
    }

    private FoodData parseFoodFromBundle(Bundle bundle){
        String name = bundle.getString("name");
        int imgResId = bundle.getInt("imgRes");
        String desc = bundle.getString("description");
        int price = bundle.getInt("price");
        int type = bundle.getInt("type");
        float rating = bundle.getFloat("rating");
        boolean isSpicy = bundle.getBoolean("isSpicy");

        return new FoodData(name,imgResId,price,type,isSpicy,rating,desc);
    }

    public void closeActivity(View view) {
        onBackPressed();
    }
}
