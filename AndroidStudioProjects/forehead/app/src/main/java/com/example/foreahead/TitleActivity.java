package com.example.foreahead;

import  android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class TitleActivity extends AppCompatActivity {
    private View decorView;
    ImageButton startButton, rockButton, discoButton, tvButton, popButton, oldTimeButton;
    private boolean isResumeRock, isResumeDisco, isResumeTV, isResumePOP, isResumeOld;
    //private Integer categoryCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_main);

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

        startButton = (ImageButton) findViewById(R.id.imageButton_start_category);
        rockButton = (ImageButton) findViewById(R.id.imageButton_rock_category);
        discoButton = (ImageButton) findViewById(R.id.imageButton_disco_category);
        tvButton = (ImageButton) findViewById(R.id.imageButton_tv_category);
        popButton = (ImageButton) findViewById(R.id.imageButton_pop_category);
        oldTimeButton = (ImageButton) findViewById(R.id.imageButton_oldtime_category);
        //categoryCounter = HelperActivity.getCategoryCounter();
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openWaitingRoomActivity(); }
        });
        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResumeRock){
                    isResumeRock = true;
                    //HelperActivity.setIsResumeRock(isResumeRock);
                    //HelperActivity.setCategoryCounter(categoryCounter++);
                    rockButton.setImageDrawable(getResources().getDrawable(R.drawable.on_rock_button));
                }
                else{
                    isResumeRock = false;
                    //HelperActivity.setIsResumeRock(isResumeRock);
                    //HelperActivity.setCategoryCounter(categoryCounter--);
                    rockButton.setImageDrawable(getResources().getDrawable(R.drawable.rock_button));
                }
            }
        });

        discoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResumeDisco){
                    isResumeDisco = true;
                    discoButton.setImageDrawable(getResources().getDrawable(R.drawable.on_disco_button));
                }
                else{
                    isResumeDisco = false;
                    discoButton.setImageDrawable(getResources().getDrawable(R.drawable.disco_button));
                }
            }
        });

        tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResumeTV){
                    isResumeTV = true;
                    tvButton.setImageDrawable(getResources().getDrawable(R.drawable.on_tv_button));
                }
                else{
                    isResumeTV = false;
                    tvButton.setImageDrawable(getResources().getDrawable(R.drawable.tv_button));
                }
            }
        });

        oldTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResumeOld){
                    isResumeOld = true;
                    oldTimeButton.setImageDrawable(getResources().getDrawable(R.drawable.on_oldtime_button));
                }
                else{
                    isResumeOld = false;
                    oldTimeButton.setImageDrawable(getResources().getDrawable(R.drawable.oldtime_button));
                }
            }
        });

        popButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResumePOP){
                    isResumePOP = true;
                    //HelperActivity.setIsResumePOP(isResumePOP);
                    //HelperActivity.setCategoryCounter(categoryCounter++);
                    popButton.setImageDrawable(getResources().getDrawable(R.drawable.on_pop_button));
                }
                else{
                    isResumePOP = false;
                    //HelperActivity.setIsResumePOP(isResumePOP);
                    //HelperActivity.setCategoryCounter(categoryCounter++);
                    popButton.setImageDrawable(getResources().getDrawable(R.drawable.pop_button));
                }
            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }
    private int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
    public void openWaitingRoomActivity(){
        Intent intent = new Intent(this, WaitingRoomActivity.class);
        startActivity(intent);
    }
}