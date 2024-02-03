package com.example.foreahead;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class WaitingRoomActivity extends AppCompatActivity {
    private static final long COUNTDOWN_TIME = 4000; // 3 seconds
    private static final long COUNTDOWN_INTERVAL = 1000; // 1 second
    private TextView countdownTextView;
    private View decorView;
    private List<ListItemActivity> songsList;
    private List<Integer> categoryList;
    private Set<Integer> randomIndices;
    private boolean isResumeRock, isResumePOP, isResumeOld, isResumeTV, isResumeDisco;
    private int songsFile;
    private Integer categoryIndicesNumber, points;
    private List<ListItemActivity> allCategoriesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waitingroom_main);
        hideBars();

        points = HelperActivity.getResult();

        isResumeRock = HelperActivity.getIsResumeRock();
        isResumeDisco = HelperActivity.getIsResumeDisco();
        isResumeOld = HelperActivity.getIsResumeOld();
        isResumePOP = HelperActivity.getIsResumePOP();
        isResumeTV = HelperActivity.getIsResumeTV();

        categoryList = HelperActivity.getCategoryList();

        // count categories
        // we also need chosen category list not only number
        // to iterate through this list when creating songs list
        // also then we can get size of list as a category counter
        // for now we want ten songs and two categories to be worked

        // divide all songs number to all categories
        categoryIndicesNumber = HelperActivity.getSongsNumber() / categoryList.size();
        // randomizing loop, one iteration per category
        for (int i = 0; i < categoryList.size(); i++)
        {
            Log.d(String.valueOf(categoryList.get(0)), "category");
            randomIndices = chooseRandomIndices(categoryIndicesNumber); // indices must be in range (0-songsfile.size())
            songsList = createSongsList(randomIndices, categoryList.get(i));
            allCategoriesList.addAll(songsList);
        }
//        randomIndices = chooseRandomIndices(HelperActivity.getSongsNumber());
//        songsList = createSongsList(randomIndices, categoryList.get(0));
        songsList = allCategoriesList;
        Collections.shuffle(songsList); // shuffle list items to mix categories
//        Log.d(String.valueOf(randomIndices.size()), "indices");
//        Log.d(String.valueOf(songsList.size()), "songs");
        HelperActivity.setSongsList(songsList);
//        HelperActivity.setSongsList(allCategoriesList);
        openSongIfTimesUp();
    }

    public Set<Integer> chooseRandomIndices(int songsNumber){

        Set<Integer> randomIndices = new HashSet<>();
        Random random = new Random();

        int min = 0;  // Minimum value of the range
        int max = 9;  // number of elements in first column in file
        int count = songsNumber;  // Number of random numbers to generate

        while (randomIndices.size() < count) {
            int randomNumber = random.nextInt(max - min + 1) + min;
            randomIndices.add(randomNumber);
        }
        return randomIndices;
    }
    public List<ListItemActivity> createSongsList(Set<Integer> randomIndices, Integer songsFile){

        int rowCounter = 0; // needed to choose random titles
        List<ListItemActivity> songsList = new ArrayList<>();
//        songsFile = R.raw.pop;

        try {
//            if (isResumeRock) {
//                songsFile = R.raw.rock;
//                Log.d(String.valueOf(songsFile), "songsfile");
//
//            }
//            if (isResumeTV) {
//                songsFile = R.raw.tv;
//            }
//            if (isResumeDisco) {
//                songsFile = R.raw.disco;
//            }
//            if (isResumeOld) {
//                songsFile = R.raw.old;
//            }
            // read the file with songs
            InputStream inputStream = getResources().openRawResource(songsFile);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // read chosen titles by random indices
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (randomIndices.contains(rowCounter)) {
                    String[] columns = line.split("\t"); // Split the line by tabs to get individual values
                    if (columns.length == 2) {
                        ListItemActivity songBandItem = new ListItemActivity(columns[1], columns[0]); // [0] title, [1] band
                        songsList.add(songBandItem);
                    }
                }
                rowCounter++; // increment the counter
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
        CountDownTimer countDownTimer = new CountDownTimer(COUNTDOWN_TIME, COUNTDOWN_INTERVAL) {
            // counter is ticking, display every second on tick
            @Override
            public void onTick(long millisUntilFinished) {
                countdownTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }
            // open SONG after 3 seconds
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