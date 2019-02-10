package com.example.nathanmsika.Android5778_6244_8742_01.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.nathanmsika.my_navigation_drawer.R;

/**
 * Created by nathanmsika on 09/11/2017.
 */

public class SplashScreen  extends Activity{

    final String  LOG = "MyApp";

    private static int SPLASH_TIME_OUT = 1000;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);


        Log.d(LOG,"page de demmarage");


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
