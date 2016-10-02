package com.example.hp.sharedpreferences;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

/**
 * Created by HP on 02-10-2016.
 */
public class splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(splash.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
