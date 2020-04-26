package com.example.workouttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timertext;
    SeekBar seekBar;
    Boolean active = false;
    Button ppp;
    CountDownTimer count;
    public void reset()
    {
        timertext.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        count.cancel();
        ppp.setText("GO!");
        active = false;
    }
    public void start(View view) {
        if (active) {
            reset();
        } else {
            active = true;
            seekBar.setEnabled(false);
            ppp.setText("STOP");

            count = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    update((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer media = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    media.start();
                    reset();
                }
            }.start();
        }
    }
    public void update(int secondsleft)
    {
        int minutes = secondsleft/60 ;
        int seconds = secondsleft - (minutes*60);
        String secondstring = Integer.toString(seconds);
        if(seconds<=9)
        {
            secondstring = "0" + secondstring;
        }
        timertext.setText(Integer.toString(minutes) + ":" + secondstring);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timertext = (TextView) findViewById(R.id.timer);
        seekBar = findViewById(R.id.seekBar);
        ppp = findViewById(R.id.pp);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                update(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
