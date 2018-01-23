package com.example.cyt.deliciousfood;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mBtnDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        List<FoodData> foods = FoodAPI.getDemoFood(this);
    }

    private void initViews() {
        mBtnDetail = findViewById(R.id.btn_detail);
        Paint paint = mBtnDetail.getPaint();
        paint.setFlags(paint.getFlags()|Paint.UNDERLINE_TEXT_FLAG);
    }
}
