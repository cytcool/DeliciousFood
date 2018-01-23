package com.example.cyt.deliciousfood;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtnDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mBtnDetail = findViewById(R.id.btn_detail);
        Paint paint = mBtnDetail.getPaint();
        paint.setFlags(paint.getFlags()|Paint.UNDERLINE_TEXT_FLAG);
    }
}
