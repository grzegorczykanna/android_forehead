package com.example.foreahead;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class WaitingRoomActivity extends AppCompatActivity {
    private static final long COUNTDOWN_TIME = 4000; // 3 seconds
    private static final long COUNTDOWN_INTERVAL = 1000; // 1 second
    private TextView countdownTextView;
    private CountDownTimer countDownTimer;
    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waitingroom_main);
        hideBars();
        openSongIfTimesUp();
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
    public void openSongIfTimesUp(){
        // set the countdown timer
        countdownTextView = findViewById(R.id.waitingTimerTV);
        countDownTimer = new CountDownTimer(COUNTDOWN_TIME, COUNTDOWN_INTERVAL) {
            // counter is ticking, display every second on tick
            @Override
            public void onTick(long millisUntilFinished) {
                countdownTextView.setText(millisUntilFinished / 1000 + " seconds");
            }
            // open FAIL after 30 seconds
            @Override
            public void onFinish() {
                // Timer has finished, open the new activity
                    // Open the new activity only if touch was not detected
                    openSongActivity();
            }
        };

        // start the counter, open FAIL after 30 seconds if screen touch not detected earlier
        countDownTimer.start();
    }

    private void openSongActivity() {
        Intent intent = new Intent(this, SongActivity.class);
        startActivity(intent);
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
}