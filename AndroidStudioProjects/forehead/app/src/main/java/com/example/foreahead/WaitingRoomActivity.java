package com.example.foreahead;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaitingRoomActivity extends AppCompatActivity {
    private static final long COUNTDOWN_TIME = 4000; // 3 seconds
    private static final long COUNTDOWN_INTERVAL = 1000; // 1 second
    private TextView countdownTextView;
    private CountDownTimer countDownTimer;
    private View decorView;
    private int songsNumber = 5;
    private List<ListItemActivity> songsList;
    private List<Integer> randomSongs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waitingroom_main);
        hideBars();
        chooseRandomTitles(songsNumber);
        songsList = createSongsList(songsNumber);
        HelperActivity.setSongsList(songsList);
        openSongIfTimesUp();
    }

    public List<Integer> chooseRandomTitles(int songsNumber){
        int min = 0;  // Minimum value of the range
        int max = 10;  // number of elements in first column in file
        int count = songsNumber;  // Number of random numbers to generate

        List<Integer> randomNumbers = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int randomNumber = random.nextInt(max - min + 1) + min;
            randomNumbers.add(randomNumber);
        }
        return randomSongs;
    }

    public List<ListItemActivity> createSongsList(int songsNumber){

        List<ListItemActivity> songsList = new ArrayList<>();

        try {
            // read the file with songs
            InputStream inputStream = getResources().openRawResource(R.raw.songs);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] columns = line.split("\t"); // Split the line by tabs to get individual values
                if (columns.length == 2) {
                    ListItemActivity songBandItem = new ListItemActivity(columns[0], columns[1]); // [0] title, [1] band
                    songsList.add(songBandItem);
                }
            }

            bufferedReader.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //return list with songs titles and bands names;
        return songsList;
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