package com.example.foreahead;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    private View decorView;
    private TextView resultsPoints;
    private static List<ListItemActivity> songsList = new ArrayList<>();
    private static List<Integer> categoryList;
    private static int songCounter = 0;
    private Integer points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_main);
        hideBars();

        Button backToStartBtn = findViewById(R.id.backToStartBtn);
        points = HelperActivity.getResult();

        resultsPoints = findViewById(R.id.resultsPointsTV);
        resultsPoints.setText(String.valueOf(points));

        points = 0;
        categoryList = new ArrayList<>();

        backToStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to avoid crush
                HelperActivity.setSongsList(songsList);
                HelperActivity.setSongCounter(songCounter);
                HelperActivity.setResult(points);
                HelperActivity.setCategoryList(categoryList);

                openTitleActivity();
            }
        });

    }
    public void hideBars(){
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
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
    private void openTitleActivity() {
        Intent intent = new Intent(ResultActivity.this, TitleActivity.class);
        startActivity(intent);
        finish(); // Optional: Finish the current activity if you don't want to return to it
    }

}