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

public class SongActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor magnetometer;
    private float[] lastMagnetometerValues = new float[3];
    private boolean isActivityOpen = false; // To prevent opening the activity multiple times

    LinearLayout linearLayout;

    private boolean touchDetected = false;
    private CountDownTimer countDownTimer;
    private TextView countdownTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_main);

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
                countdownTextView.setText("Time remaining: " + millisUntilFinished / 1000 + " seconds");
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

        //

        // FAIL if rotate phone
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchDetected = true;
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel the countdown timer to prevent memory leaks
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

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
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float[] currentMagnetometerValues = event.values;

            // Compare the current values with the last recorded values
            if (lastMagnetometerValues != null && currentMagnetometerValues != null) {
                float deltaX = currentMagnetometerValues[0];

                float threshold = 45;
                // Check if the change in magnetometer values is significant
                if (deltaX > threshold) {
                    openPassActivity();
                }
            }

            lastMagnetometerValues = currentMagnetometerValues.clone();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing
    }

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

}