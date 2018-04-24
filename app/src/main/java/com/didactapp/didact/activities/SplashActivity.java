package com.didactapp.didact.activities;

import android.os.Bundle;
import android.os.Handler;

import com.didactapp.didact.R;

/**
 * This activity displays a brief loading animation at app launch
 */
public class SplashActivity extends BaseActivity {

    /* for how long to show the loading animation */
    private static final int SPLASH_DELAY_MILLIS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showNextActivityAfterDelay();
    }

    /**
     * wait some time and then launch the login activity
     */
    private void showNextActivityAfterDelay() {
        /* will wake up and call run method after pre-set time */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchActivity(SplashActivity.this, LoginActivity.class);
                finish();
            }
        }, SPLASH_DELAY_MILLIS);
    }


}