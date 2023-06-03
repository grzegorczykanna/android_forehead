package com.example.foreahead;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.view.MotionEvent;
import com.example.myapplication.R;
import android.os.CountDownTimer;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class SongActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor magnetometer;
    private float[] lastMagnetometerValues = new float[3];
    private boolean isActivityOpen = false; // To prevent opening the activity multiple times

    LinearLayout linearLayout;

    private boolean touchDetected = false;
    private CountDownTimer countDownTimer;
    private TextView countdownTextView;
    private TextView songTextView;
    private TextView bandTextView;
    private int nonrandomIndex;
    private List<Integer> randomIndicesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_main);

        // make songs list
        // Create an empty list of strings
        List<List<String>> songsList = new ArrayList<>();
        songsList.add(new ArrayList<>());
        songsList.add(new ArrayList<>());
        songsList.add(new ArrayList<>());
        songsList.add(new ArrayList<>());
        songsList.add(new ArrayList<>());

        // Add elements to the list
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

        randomIndicesList = GlobalActivity.getMyList();

        if(randomIndicesList.size() < 5){
            nonrandomIndex = randomIndicesList.size();
        } else {
            openResultActivity();
        }

        randomIndicesList.add(nonrandomIndex);

        // Get the random element
        String randomSong = songsList.get(nonrandomIndex).get(0);
        String randomBand = songsList.get(nonrandomIndex).get(1);

        songTextView = findViewById(R.id.songTV);
        bandTextView = findViewById(R.id.bandTV);

        // Access elements in the list
        songTextView.setText(randomSong);
        // Access elements in the list
        bandTextView.setText(randomBand);

        // open FAIL on touch or after 30 secs
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the touchDetected flag to true
                touchDetected = true;
                openFailActivity();
            }
        });

        // Start the countdown timer
        countdownTextView = findViewById(R.id.timerTV);
        countDownTimer = new CountDownTimer(6000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                countdownTextView.setText(millisUntilFinished / 1000 + " seconds");
                // Countdown is ticking, do nothing
            }

            @Override
            public void onFinish() {
                // Timer has finished, open the new activity
                if (!touchDetected) {
                    // Open the new activity only if touch was not detected
                    openFailActivity();
                }
            }
        };
        countDownTimer.start();

        // FAIL if rotate phone
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    // FAIL on touch
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // function to detect screen touch for going to FAIL activity
        touchDetected = true;
        return super.onTouchEvent(event);
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
                /*if (deltaZ > Math.abs(threshold) || deltaZ < threshold) {
                    touchDetected = true;
                    openPassActivity();
                }*/
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing
    }
    // go to desired activity
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
    public void openResultActivity(){
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}