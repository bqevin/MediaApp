package com.projects.kevinbarassa.mediaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by admin on 5/1/2016.
 */
public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        Thread timer = new Thread(){
            public void run(){
                try{

                    sleep(3000);

                }catch (InterruptedException e){
                      e.printStackTrace();
                }finally{
                    Intent intent = new Intent(Splash.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

        };
        timer.start();







    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
