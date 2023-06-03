package com.example.foreahead;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class TitleActivity extends AppCompatActivity {
    ImageButton startButton;
    ImageButton rockButton;
    ImageButton discoButton;
    ImageButton tvButton;
    // ImageButton popButton;
    ImageButton oldTimeButton;
    private boolean isResume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_main);

        // Hide the navigation bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        startButton = (ImageButton) findViewById(R.id.button_start_category);
        rockButton = (ImageButton) findViewById(R.id.button_rock_category);
        discoButton = (ImageButton) findViewById(R.id.button_disco_category);
        tvButton = (ImageButton) findViewById(R.id.button_tv_category);
        // popButton = (ImageButton) findViewById(R.id.button_pop_category);
        oldTimeButton = (ImageButton) findViewById(R.id.button_oldtime_category);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSongActivity();
            }
        });
        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResume){
                    isResume = true;
                    rockButton.setImageDrawable(getResources().getDrawable(R.drawable.on_rock_button));
                }
                else{
                    isResume = false;
                    rockButton.setImageDrawable(getResources().getDrawable(R.drawable.rock_button));
                }
            }
        });

        discoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResume){
                    isResume = true;
                    discoButton.setImageDrawable(getResources().getDrawable(R.drawable.on_disco_button));
                }
                else{
                    isResume = false;
                    discoButton.setImageDrawable(getResources().getDrawable(R.drawable.disco_button));
                }
            }
        });

        tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResume){
                    isResume = true;
                    tvButton.setImageDrawable(getResources().getDrawable(R.drawable.on_tv_button));
                }
                else{
                    isResume = false;
                    tvButton.setImageDrawable(getResources().getDrawable(R.drawable.tv_button));
                }
            }
        });

        oldTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResume){
                    isResume = true;
                    oldTimeButton.setImageDrawable(getResources().getDrawable(R.drawable.on_oldtime_button));
                }
                else{
                    isResume = false;
                    oldTimeButton.setImageDrawable(getResources().getDrawable(R.drawable.oldtime_button));
                }
            }
        });

        /*popButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResume){
                    isResume = true;
                    popButton.setImageDrawable(getResources().getDrawable(R.drawable.on_pop_button));
                }
                else{
                    isResume = false;
                    popButton.setImageDrawable(getResources().getDrawable(R.drawable.pop_button));
                }
            }
        });*/
    }
    public void openSongActivity(){
        Intent intent = new Intent(this, SongActivity.class);
        startActivity(intent);
    }
}