package com.example.foreahead;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class IntroActivity extends AppCompatActivity {
    private View decorView;
    private static final int DELAY_TIME_MS = 2000; // 2 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_main);

      //////////////////
    /*    // Hide the navigation bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        // Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }*/
      //////////////////

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() { openTitleActivity();}
        }, DELAY_TIME_MS);
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
        Intent intent = new Intent(IntroActivity.this, TitleActivity.class);
        startActivity(intent);
        finish(); // Optional: Finish the current activity if you don't want to return to it
    }
}
