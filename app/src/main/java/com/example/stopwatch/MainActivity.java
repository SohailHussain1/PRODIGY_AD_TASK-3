package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageButton start,stop,reset;
    boolean run=false;
    Runnable r;
    TextView time;
    int seconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        start=findViewById(R.id.start);
        reset=findViewById(R.id.reset);
        stop=findViewById(R.id.stop);
        time=findViewById(R.id.time);
        final Handler handler = new Handler();
        handler.post(r = new Runnable() {
            @Override
            public void run() {
                int hr = (seconds/100)/(60*60);
                int min = ((seconds/100)%3600)/60;
                int sec = (seconds/100)%60;
                int mil = (seconds)%100;
                String timeText = String.format(Locale.getDefault(),"%d:%02d:%02d:%02d",hr,min,sec,mil);
                time.setText(timeText);
                if (run) {
                    seconds++;
                }
                handler.postDelayed(this,10);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start.setVisibility(View.INVISIBLE);
                stop.setVisibility(View.VISIBLE);
                time.setVisibility(View.VISIBLE);
                run = true;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);
                run = false;
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);
                seconds = 0;
                run = false;
            }
        });

    }
}