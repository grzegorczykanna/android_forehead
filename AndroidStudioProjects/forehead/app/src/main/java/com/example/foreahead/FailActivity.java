package com.example.foreahead;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class FailActivity extends AppCompatActivity {

    private static final int DELAY_TIME_MS = 1500; // 1.5 seconds
    // set number of songs per round
    private int songsNumber = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fail_main);

        int songCounter = HelperActivity.getCounter();

        // wait 1.5 seconds and back to SONG activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // check number of song, go to the results after finish
                if(songCounter == (songsNumber-1)) {
                    openResultActivity();
                } else{ openSongActivity(); }
            }
        }, DELAY_TIME_MS);
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