package com.didactapp.didact.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.didactapp.didact.R;
import com.didactapp.didact.entities.AuthenticationKey;
import com.didactapp.didact.entities.PublicKey;
import com.didactapp.didact.entities.User;
import com.didactapp.didact.network.PublicKeyRemoteGateway;
import com.didactapp.didact.network.RegisterRemoteGateway;
import com.didactapp.didact.network.RemoteGatewayCallback;
import com.didactapp.didact.userEncrypt.JWTEncrypt;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText emailText;
    private EditText passwordText;
    private Button registerButton;
    private RegisterRemoteGateway registerRemoteGateway;
    private PublicKeyRemoteGateway publicKeyRemoteGateway;
    private PublicKey publicKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /* get layout elements */
        emailText = findViewById(R.id.register_email_edittext);
        passwordText = findViewById(R.id.register_password_edittext);
        registerButton = findViewById(R.id.activity_create_button);
        registerButton.setOnClickListener(this);

//        publicKeyRemoteGateway
//        registerRemoteGateway = RegisterRemoteGateway.getInstance();
//        registerRemoteGateway.getFromRemote();
    }


//    @Override
//    public void onRemoteLoadRSuccess(AuthenticationKey item) {
//        System.out.print(item.getKey());
//        Constants.AUTHENTICATION_KEY = item.getKey();
//        launchActivity(RegisterActivity.this, LibraryActivity.class);
//
//    }

//    @Override
//    public void onRemoteDataNotAvailable() {
//
//    }
//
//    @Override
//    public void onRemoteLoadFailed() {
//        LogUtils.d("onRemoteLoadFailed");
//    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        if (publicKey == null || publicKey.getKey() == null) {
            return;
        }
        if (v.getId() == R.id.activity_create_button) {
            User user = new User(emailText.getText().toString(), passwordText.getText().toString());
            /* encrypt and send to server */
            String encrypt = new JWTEncrypt().encrypt(publicKey.getKey(), user);
//            registerRemoteGateway.getFromRemote(this, new EncryptUser(encrypt, publicKey.getKey()));
        }
//        int a = v.getId();
//        System.out.print(a
    }

    public RemoteGatewayCallback<AuthenticationKey> getAuthenticationGatewayCallback() {
        return new RemoteGatewayCallback<AuthenticationKey>() {

            @Override
            public void onRemoteLoadRSuccess(AuthenticationKey retrieved) {
//                show success and show next activity
            }

            @Override
            public void onRemoteDataNotAvailable() {
//              shouldn't be called
            }

            @Override
            public void onRemoteLoadFailed() {
//              show error
            }
        };
    }

    public RemoteGatewayCallback<PublicKey> getPublicKeyGatewayCallback() {
        return new RemoteGatewayCallback<PublicKey>() {
            @Override
            public void onRemoteLoadRSuccess(PublicKey retrieved) {

            }

            @Override
            public void onRemoteDataNotAvailable() {

            }

            @Override
            public void onRemoteLoadFailed() {

            }
        };
    }
}