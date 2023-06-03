package com.example.foreahead;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import android.content.Intent;
import android.os.Handler;

public class PassActivity extends AppCompatActivity {

    private static final int DELAY_TIME_MS = 1500; // 1.5 seconds
    private int songsNumber = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_main);

        int songCounter = HelperActivity.getCounter();

        // wait 1.5 seconds and back to SONG activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(songCounter == (songsNumber-1)) {
                    openResultActivity();
                } else{ openSongActivity(); }
            }
        }, DELAY_TIME_MS);
    }

    private void openSongActivity() {
        Intent intent = new Intent(PassActivity.this, SongActivity.class);
        startActivity(intent);
        finish(); // Optional: Finish the current activity if you don't want to return to it
    }

    public void openResultActivity(){
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}
