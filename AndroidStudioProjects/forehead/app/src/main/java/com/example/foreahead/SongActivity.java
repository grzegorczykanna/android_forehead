package com.example.foreahead;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.view.MotionEvent;
import com.example.myapplication.R;
import android.os.CountDownTimer;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class SongActivity extends Activity implements SensorEventListener {

    private View decorView;
    // to use magnetometer to detect rotation of the device
    private SensorManager sensorManager;
    private Sensor magnetometer;
    private boolean isActivityOpen = false;

    // to detect screen touch
    LinearLayout linearL;
    private boolean cancelOpenNewActivity = false;

    // to count down 30 seconds (time for each song)
    private CountDownTimer countDownTimer;
    private TextView timerTV;
    private int roundTime = 6000; // ms

    // handle the songs list
    private int songCounter, songsNumber = 5;
    private List<List<String>> songsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide the action bar
        setContentView(R.layout.song_main);

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    decorView.setSystemUiVisibility(hideSystemBars());
            }

        // get and increment counter
        actualizeSongCounter(songCounter);
        // load songs list
        songsList = createSongsList(songsNumber);
        // display each element of songs list
        displaySongToGuess();
        // open PASS if rotated phone
        openPassIfGuessed();
        // open FAIL on touch or ...
        openFailIfNotGuessed();
        // open FAIL after 30 seconds
        openFailIfTimesUp();
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

    // FAIL on touch
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // function to detect screen touch for going to FAIL activity
        cancelOpenNewActivity = true;
        return super.onTouchEvent(event);
    }

    public void openFailIfNotGuessed(){
        linearL = (LinearLayout) findViewById(R.id.linear_layout);
        linearL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the cancelOpenNewActivity flag to true when screen touch is detected
                cancelOpenNewActivity = true;
                openFailActivity();
            }
        });
    }

    // FAIL after 30 seconds
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel the countdown timer to prevent memory leaks
        // helper for counter
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void openFailIfTimesUp(){
        // set the countdown timer
        timerTV = findViewById(R.id.timerTV);
        countDownTimer = new CountDownTimer(roundTime, 100) {
            // counter is ticking, display every second on tick
            @Override
            public void onTick(long millisUntilFinished) {
                timerTV.setText(millisUntilFinished / 1000 + " seconds");
            }
            // open FAIL after 30 seconds
            @Override
            public void onFinish() {
                // Timer has finished, open the new activity
                if (!cancelOpenNewActivity) {
                    // Open the new activity only if touch was not detected
                    openFailActivity();
                }
            }
        };

        // start the counter, open FAIL after 30 seconds if screen touch not detected earlier
        countDownTimer.start();
    }

    // PASS after rotate the device
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // function to go to PASS activity if the phone is rotated
        // works with device, does not work with emulator (other axis)
        // onResume() and onPause()  and onAccuracyChanged() are also needed
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float[] currentMagnetometerValues = event.values;

            if (currentMagnetometerValues != null) {
                float deltaZ = currentMagnetometerValues[2];

                float threshold = -35;
                // Check if the change in magnetometer values is significant
              /*  if (deltaZ > Math.abs(threshold) || deltaZ < threshold) {
                    cancelOpenNewActivity = true;
                    openPassActivity();
                }*/
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing
    }

    public void openPassIfGuessed(){
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    // go to desired activities
    private void openPassActivity() {
        if (!isActivityOpen) {
            isActivityOpen = true;
            Intent intent = new Intent(this, PassActivity.class);
            startActivity(intent);
        }
    }

    public void openFailActivity(){
        Intent intent = new Intent(this, FailActivity.class);
        startActivity(intent);
    }

    // handle song element
    public void actualizeSongCounter(int counter){
        // get number of song
        songCounter = HelperActivity.getCounter();
        // increment song counter
        HelperActivity.setCounter(songCounter + 1);
    }

    public List<List<String>> createSongsList(int songsNumber){

        // code to set chosen songs list
        // Create an empty list of lists of strings
        List<List<String>> songsList = new ArrayList<>();
        songsList.add(new ArrayList<>());
        songsList.add(new ArrayList<>());
        songsList.add(new ArrayList<>());
        songsList.add(new ArrayList<>());
        songsList.add(new ArrayList<>());

        // Add elements to the list, songs and bands names
        songsList.get(0).add("song1");
        songsList.get(0).add("author1");
        songsList.get(1).add("song2");
        songsList.get(1).add("author2");
        songsList.get(2).add("song3");
        songsList.get(2).add("author3");
        songsList.get(3).add("song4");
        songsList.get(3).add("author4");
        songsList.get(4).add("song5");
        songsList.get(4).add("author5");

        return songsList;
    }

    public void displaySongToGuess(){
        // Get the next element of songs list
        String songToGuess = songsList.get(songCounter).get(0);
        String bandToGuess = songsList.get(songCounter).get(1);

        // display song and band name in text views
        TextView songTextView = findViewById(R.id.songTV);
        TextView bandTextView = findViewById(R.id.bandTV);
        songTextView.setText(songToGuess);
        bandTextView.setText(bandToGuess);
    }
}