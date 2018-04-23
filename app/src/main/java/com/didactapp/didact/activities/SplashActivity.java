package com.didactapp.didact.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.didactapp.didact.R;
import com.didactapp.didact.entities.AuthenticationKey;
import com.didactapp.didact.network.AuthenticationGatewayCallback;
import com.didactapp.didact.network.PublicKeyRemoteGateway;

public class SplashActivity extends BaseActivity implements AuthenticationGatewayCallback<AuthenticationKey> {

    private PublicKeyRemoteGateway publicKeyRemoteGateway;
    private static final int SPLASH_DELAY_MILLIS = 1000;

    @Override
    public void onRemoteLoadRSuccess(AuthenticationKey item) {
//        TODO keep the pub key in gateway
        Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
        intent.putExtra("PUBLIC_KEY", item.getKey());
        startActivity(intent);

    }

    @Override
    public void onRemoteDataNotAvailable() {

    }

    @Override
    public void onRemoteLoadFailed() {
//        TODO handle failed key fetch - sever error try later
        System.out.print("dd");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        publicKeyRemoteGateway = PublicKeyRemoteGateway.getInstance();
        publicKeyRemoteGateway.getItem(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        showNextActivityAfterDelay();
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