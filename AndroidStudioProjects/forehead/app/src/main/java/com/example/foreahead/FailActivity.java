package com.example.foreahead;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class FailActivity extends AppCompatActivity {

    private static final int DELAY_TIME_MS = 1500; // 1.5 seconds
    // set number of songs per round
    private int songsNumber = 5;
    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fail_main);
        hideBars();

        int songCounter = HelperActivity.getCounter();

        // wait 1.5 seconds and back to SONG activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // check number of song, go to the results after finish
                if(songCounter == (songsNumber)) {
                    openResultActivity();
                } else{ openSongActivity(); }
            }
        }, DELAY_TIME_MS);
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

    private void openSongActivity() {
        Intent intent = new Intent(FailActivity.this, SongActivity.class);
        startActivity(intent);
        finish(); // Optional: Finish the current activity if you don't want to return to it
    }

    public void openResultActivity(){
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}