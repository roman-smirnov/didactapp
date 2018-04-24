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
import com.didactapp.didact.network.LoginRemoteGateway;
import com.didactapp.didact.network.PublicKeyRemoteGateway;
import com.didactapp.didact.network.RemoteGatewayCallback;
import com.didactapp.didact.userEncrypt.JWTEncrypt;

/**
 * This activity allows user to log in to the app. It also handles encryption, authentication, etc
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    /* TODO:
     *  1. move logic to presenter
     *  2. save presenter and state on config change
     *  3. implement user feedback on
     */

    /* view refs */
    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button registerButton;

    /* the public key with which to encrypt communication (e.g user details) - received from server
     * on each new session */
    private PublicKey publicKey = null;


    /* these are here to prevent GC from disposing of the anonymous refs*/
    private RemoteGatewayCallback<PublicKey> publicKeyCallback = null;
    private RemoteGatewayCallback<AuthenticationKey> authKeyCallback = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* get the views */
        email = findViewById(R.id.login_email_field);
        password = findViewById(R.id.login_password_field);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);

        /* set click listeners */
        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        /* create network callbacks - do it this way because java generic type erasure */
        publicKeyCallback = getPublicKeyGatewayCallback();
        authKeyCallback = getAuthenticationGatewayCallback();

        /* request a public key from server */
        PublicKeyRemoteGateway.getInstance().getFromRemote(publicKeyCallback);
    }


    @Override
    public void onClick(View v) {
        /* if there's no public key, there can't be no login */
        if (publicKey == null || publicKey.getKey() == null) {
            return;
        }
        /* login button logic */
        if (v.getId() == R.id.login_button) {
            LogUtils.d("login button pressed");
            User user = new User(email.getText().toString(), password.getText().toString());
            /* encrypt and send to server */
            String encrypt = new JWTEncrypt().encrypt(publicKey.getKey(), user);
            LoginRemoteGateway
                    .getInstance()
                    .getFromRemote(authKeyCallback, new EncryptUser(encrypt, publicKey.getKey()));
            /* register button pressed - show register activity */
        } else if (v.getId() == R.id.register_button) {
            launchActivity(LoginActivity.this, RegisterActivity.class);
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
//                show success and show next activity
                launchActivity(LoginActivity.this, LibraryActivity.class);
                LogUtils.d("auth key succerss!");
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

    /**
     * create an anonymous inner gateway object - we do it this way because Java generics are
     * typed erase so both Authentication and Publickey gateways are compiled so as they have no dif
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
                LogUtils.d("no data");

            }

            @Override
            public void onRemoteLoadFailed() {
                LogUtils.d("failed ");
            }
        };
    }
}