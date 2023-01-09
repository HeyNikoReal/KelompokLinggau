package com.mdp.kelompoklinggau.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.mdp.kelompoklinggau.R;

public class SplashScreenActivity extends AppCompatActivity {

    MediaPlayer audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        audio = MediaPlayer.create(this, R.raw.backsound);
        audio.setLooping(true);
        audio.setVolume(1, 1);
        audio.start();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this,
                        MainActivity.class));
                finish();
            }
        }, 2000);
    }
}