package com.example.foreahead;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class FailActivity extends AppCompatActivity {

    private static final int DELAY_TIME_MS = 3000; // 3 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fail_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openSongActivity();
            }
        }, DELAY_TIME_MS);
    }

    private void openSongActivity() {
        Intent intent = new Intent(FailActivity.this, SongActivity.class);
        startActivity(intent);
        finish(); // Optional: Finish the current activity if you don't want to return to it
    }
}