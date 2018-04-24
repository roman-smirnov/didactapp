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

/**
 * This activity allows users to register to the app. It also handles encryption, authentication, etc
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    /* view refs */
    private EditText emailText;
    private EditText passwordText;
    private Button registerButton;

    /* create network callbacks - we do it this way because they're created as anonymous inner
     * classes and will therefore be collected by GC if they're not referenced
     */
    private RegisterRemoteGateway registerRemoteGateway;
    private PublicKeyRemoteGateway publicKeyRemoteGateway;

    /* the public key with which to encrypt communication (e.g user details) - received from server
     * on each new session */
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

        /* init network callbacks */
        publicKeyRemoteGateway = PublicKeyRemoteGateway.getInstance();
        publicKeyRemoteGateway.getFromRemote(getPublicKeyGatewayCallback());
    }


    @Override
    public void onClick(View v) {
        /* if there's no public key, there can't be no login */
        if (publicKey == null || publicKey.getKey() == null) {
            return;
        }
        /* register button clicked */
        if (v.getId() == R.id.activity_create_button) {
            User user = new User(emailText.getText().toString(), passwordText.getText().toString());
            /* encrypt and send to server */
            String encrypt = new JWTEncrypt().encrypt(publicKey.getKey(), user);
            registerRemoteGateway = RegisterRemoteGateway.getInstance();
            registerRemoteGateway.getFromRemote(getAuthenticationGatewayCallback(), new EncryptUser(encrypt, publicKey.getKey()));
        }
    }

    /**
     * create an anonymous inner gateway object - we do it this way because Java generics are
     * typed erase so both Authentication and Publickey gateways are compiled so as they have no dif
     *
     * @return
     */
    public RemoteGatewayCallback<AuthenticationKey> getAuthenticationGatewayCallback() {
        return new RemoteGatewayCallback<AuthenticationKey>() {

            @Override
            public void onRemoteLoadRSuccess(AuthenticationKey retrieved) {
                /* show success and show next activity */
                launchActivity(RegisterActivity.this, LibraryActivity.class);
                finish();
            }

            @Override
            public void onRemoteDataNotAvailable() {
                /* shouldn't be called*/
            }

            @Override
            public void onRemoteLoadFailed() {
                /* show error */
            }
        };
    }

    /**
     * create an anonymous inner gateway object - we do it this way because Java generics are
     * typed erase so both Authentication and Publickey gateways are compiled so as they have no dif
     *
     * @return
     */
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