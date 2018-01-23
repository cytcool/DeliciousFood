package com.example.cyt.deliciousfood;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mBtnDetail;
    private LinearLayout mCheckBoxContainer;
    private CheckBox mCheckBoxChineseFood;
    private CheckBox mCheckBoxFastFood;
    private CheckBox mCheckBoxDesertFood;

    private RadioGroup mRadioGroup;
    private RadioButton mRadioYes;
    private RadioButton mRadioNo;

    private TextView mTvPrice;
    private SeekBar mSeekBarPrcie;

    private Button mBtnReset;
    private Button mBtnSearch;

    private ImageView mImageFood;

    private Button mBtnPre;
    private Button mBtnNext;

    private List<FoodData> mFoodList;
    private int mCurrentPage;


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

        mCheckBoxContainer = findViewById(R.id.checkbox_container);
        mCheckBoxChineseFood = findViewById(R.id.checkbox_chinesefood);
        mCheckBoxFastFood = findViewById(R.id.checkbox_fastfood);
        mCheckBoxDesertFood = findViewById(R.id.checkbox_desert);
        checkAll();

        mRadioGroup = findViewById(R.id.radio_group);
        mRadioYes = findViewById(R.id.radio_yes);
        mRadioNo = findViewById(R.id.radio_no);
        mRadioGroup.check(R.id.radio_no);

        mTvPrice = findViewById(R.id.tv_price);
        mSeekBarPrcie = findViewById(R.id.seekbar_price);
        mSeekBarPrcie.setProgress(100);
        mTvPrice.setText(""+mSeekBarPrcie.getProgress());
        mSeekBarPrcie.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTvPrice.setText(""+ progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mImageFood = findViewById(R.id.image_food);
        mFoodList = initData();
        mCurrentPage = 0;
        mImageFood.setImageResource(mFoodList.get(mCurrentPage).getImgResId());

    }

    private List<FoodData> initData(){
        return FoodAPI.getDemoFood(this);
    }


    /*
    选中mCheckBoxContainer中所有的CheckBox
     */
    private void checkAll() {
        int checkBoxCount = mCheckBoxContainer.getChildCount();
        for (int i = 0; i < checkBoxCount ; i++) {
            View checkBox = mCheckBoxContainer.getChildAt(i);
            if (checkBox instanceof CheckBox){
                ((CheckBox) checkBox).setChecked(true);
            }
        }
    }
}
