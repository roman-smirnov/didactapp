package com.didactapp.didact.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;
import com.didactapp.didact.R;
import com.didactapp.didact.entities.AuthenticationKey;
import com.didactapp.didact.entities.EncryptUser;
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

        publicKeyRemoteGateway = PublicKeyRemoteGateway.getInstance();
        publicKeyRemoteGateway.getFromRemote(getPublicKeyGatewayCallback());
    }

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
            registerRemoteGateway = RegisterRemoteGateway.getInstance();
            registerRemoteGateway.getFromRemote(getAuthenticationGatewayCallback(), new EncryptUser(encrypt, publicKey.getKey()));
        }
    }

    public RemoteGatewayCallback<AuthenticationKey> getAuthenticationGatewayCallback() {
        return new RemoteGatewayCallback<AuthenticationKey>() {

            @Override
            public void onRemoteLoadRSuccess(AuthenticationKey retrieved) {
//                show success and show next activity
                launchActivity(RegisterActivity.this, LibraryActivity.class);
                finish();



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
                publicKey = retrieved;
                LogUtils.d("public key success!");
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