package com.example.cyt.deliciousfood;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int STATE_FILTER = 1;
    private static final int STATE_NORMAL = 2;
    private int mSTATE = STATE_NORMAL;

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
    private List<FoodData> mFilteredFoodList;
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

        mTvPrice = findViewById(R.id.tv_price);
        mSeekBarPrcie = findViewById(R.id.seekbar_price);
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

        
        //翻页功能
        mBtnPre = findViewById(R.id.btn_pre);
        mBtnNext = findViewById(R.id.btn_next);
        mBtnPre.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        //搜索与重置
        mBtnReset = findViewById(R.id.btn_reset);
        mBtnSearch = findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);
        mBtnReset.setOnClickListener(this);

        loadInitialView();

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pre:
                showPrePage();
                break;
            case R.id.btn_next:
                showNextPage();
                break;
            case R.id.btn_reset:
                mSTATE = STATE_NORMAL;
                loadInitialView();
                break;
            case R.id.btn_search:
                mSTATE = STATE_FILTER;
                showFilterFoods();
                break;
        }
    }

    private void loadInitialView() {
        mBtnPre.setEnabled(true);
        mBtnNext.setEnabled(true);
        mBtnDetail.setEnabled(true);
        checkAll();
        mSeekBarPrcie.setProgress(100);
        mRadioGroup.check(R.id.radio_no);
        showPageAtIndex(0);
    }

    private void showFilterFoods() {
        mFilteredFoodList = filteredFoods();
        boolean enablePageButton = (mFilteredFoodList.size() > 1);
        mBtnPre.setEnabled(enablePageButton);
        mBtnNext.setEnabled(enablePageButton);

        mBtnDetail.setEnabled(!mFilteredFoodList.isEmpty());

        if (mFilteredFoodList.isEmpty()){
            mImageFood.setImageResource(R.drawable.nodata);
        }else {
            showPageAtIndex(0);
        }
    }

    private List<FoodData> currentShowingFoods(){
        switch (mSTATE){
            case STATE_FILTER:
                return mFilteredFoodList;
            case STATE_NORMAL:
                return mFoodList;
            default:
                return mFoodList;
        }
    }

    private List<FoodData> filteredFoods(){
        //1、价格
        int maxPrice = mSeekBarPrcie.getProgress();
        //2、是否免辣
        boolean isSpicy = (mRadioGroup.getCheckedRadioButtonId() == R.id.radio_no);
        //3、美食类型
        List<Integer> selectedFoodType = new ArrayList<>();
        if (mCheckBoxChineseFood.isChecked()){
            selectedFoodType.add(FoodData.CHINESE_FOOD);
        }
        if (mCheckBoxFastFood.isChecked()){
            selectedFoodType.add(FoodData.FAST_FOOD);
        }
        if (mCheckBoxDesertFood.isChecked()){
            selectedFoodType.add(FoodData.DESERT_FOOD);
        }
        List<FoodData> results = new ArrayList<>();
        for (FoodData food:mFoodList) {
            if (food.getPrice()<maxPrice && selectedFoodType.contains(food.getType())){
                if (isSpicy || !food.isSpicy()){
                    results.add(food);
                }
            }

        }
        return results;
    }

    private void showNextPage() {
        int nextPage = (mCurrentPage - 1 + currentShowingFoods().size()) % currentShowingFoods().size();
        showPageAtIndex(nextPage);
    }

    private void showPrePage() {
        int prePage = (mCurrentPage + 1) % currentShowingFoods().size();
        showPageAtIndex(prePage);
    }

    private void showPageAtIndex(int index){
        FoodData food = currentShowingFoods().get(index);
        mImageFood.setImageResource(food.getImgResId());
        mCurrentPage = index;
    }


    public void openActivity(View view) {
        Intent intent = new Intent(this,DetailActivity.class);
        Bundle bundle = new Bundle();
        FoodData food = currentShowingFoods().get(mCurrentPage);
        bundle.putString("name",food.getName());
        bundle.putInt("imgRes",food.getImgResId());
        bundle.putFloat("rating",food.getRating());
        bundle.putInt("price",food.getPrice());
        bundle.putString("description",food.getDescription());
        bundle.putBoolean("isSpicy",food.isSpicy());
        bundle.putInt("type",food.getType());
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void closeActivity(View view) {
        finish();
    }
}
