package com.didactapp.didact.activities;

import android.os.Bundle;
import android.os.Handler;

import com.didactapp.didact.R;
import com.didactapp.didact.network.PublicKeyRemoteGateway;

public class SplashActivity extends BaseActivity {

    private PublicKeyRemoteGateway publicKeyRemoteGateway;
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

    private void showNextActivityAfterDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchActivity(SplashActivity.this, RegisterActivity.class);
                finish();
            }
        }, SPLASH_DELAY_MILLIS);
    }


}