package com.didactapp.didact.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.R;
import com.didactapp.didact.entities.AuthenticationKey;
import com.didactapp.didact.entities.EncryptUser;
import com.didactapp.didact.entities.User;
import com.didactapp.didact.network.RegisterRemoteGateway;
import com.didactapp.didact.network.RemoteGatewayCallback;
import com.didactapp.didact.userEncrypt.JWTEncrypt;
import com.didactapp.didact.utils.Constants;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, RemoteGatewayCallback<AuthenticationKey> {
    private EditText emailText;
    private EditText passwordText;
    private Button registerButton;
    private RegisterRemoteGateway registerRemoteGateway;
    private String publicKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /* get layout elements */
        emailText = findViewById(R.id.register_email_edittext);
        passwordText = findViewById(R.id.register_password_edittext);
        registerButton = findViewById(R.id.activity_create_button);
        registerButton.setOnClickListener(this);
        registerRemoteGateway = RegisterRemoteGateway.getInstance();

        /* retrieve the public key */
        publicKey = getIntent().getStringExtra("PUBLIC_KEY");
    }


    @Override
    public void onRemoteLoadRSuccess(AuthenticationKey item) {
        System.out.print(item.getKey());
        Constants.AUTHENTICATION_KEY = item.getKey();
        launchActivity(RegisterActivity.this, LibraryActivity.class);

    }

    @Override
    public void onRemoteDataNotAvailable() {

    }

    @Override
    public void onRemoteLoadFailed() {
        LogUtils.d("onRemoteLoadFailed");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.activity_create_button) {
            User user = new User(emailText.getText().toString(), passwordText.getText().toString());
            /* encrypt and send to server */
            String encrypt = new JWTEncrypt().encrypt(publicKey, user);
            registerRemoteGateway.getFromRemote(this, new EncryptUser(encrypt, publicKey));
        }
//        int a = v.getId();
//        System.out.print(a
    }
}