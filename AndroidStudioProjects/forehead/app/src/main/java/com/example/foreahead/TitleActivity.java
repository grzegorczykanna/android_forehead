package com.example.foreahead;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class TitleActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_main);

        button = (Button) findViewById(R.id.button_polish_category);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSongActivity();
            }
        });
    }
    public void openSongActivity(){
        Intent intent = new Intent(this, SongActivity.class);
        startActivity(intent);
    }
}