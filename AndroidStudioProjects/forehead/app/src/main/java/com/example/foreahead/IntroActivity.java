package com.example.foreahead;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class IntroActivity extends AppCompatActivity {

    private static final int DELAY_TIME_MS = 2000; // 2 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_main);

        // Hide the navigation bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() { openTitleActivity();}
        }, DELAY_TIME_MS);
    }

    private void openTitleActivity() {
        Intent intent = new Intent(IntroActivity.this, TitleActivity.class);
        startActivity(intent);
        finish(); // Optional: Finish the current activity if you don't want to return to it
    }
}
