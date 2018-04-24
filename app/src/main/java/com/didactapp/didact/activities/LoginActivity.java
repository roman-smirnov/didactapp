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


public class LoginActivity extends BaseActivity implements View.OnClickListener {


    // UI references.
    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button registerButton;

    private PublicKey publicKey = null;


    /* these are here to prevent GC from disposing of the anonymous refs*/
    private RemoteGatewayCallback<PublicKey> publicKeyCallback = null;
    private RemoteGatewayCallback<AuthenticationKey> authKeyCallback = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // get the views
        email = findViewById(R.id.login_email_field);
        password = findViewById(R.id.login_password_field);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        /* create network callbacks - do it this way because java generic type erasure */
        publicKeyCallback = getPublicKeyGatewayCallback();
        authKeyCallback = getAuthenticationGatewayCallback();

        PublicKeyRemoteGateway.getInstance().getFromRemote(publicKeyCallback);
    }

//    TODO implement login call (just like register but dif err)

    @Override
    public void onClick(View v) {
        if (publicKey == null || publicKey.getKey() == null) {
            return;
        }
        if (v.getId() == R.id.login_button) {
            User user = new User(email.getText().toString(), password.getText().toString());
            /* encrypt and send to server */
            String encrypt = new JWTEncrypt().encrypt(publicKey.getKey(), user);
            LoginRemoteGateway
                    .getInstance()
                    .getFromRemote(authKeyCallback, new EncryptUser(encrypt, publicKey.getKey()));
        } else if (v.getId() == R.id.register_button) {
            launchActivity(LoginActivity.this, RegisterActivity.class);
        }
    }


    public RemoteGatewayCallback<AuthenticationKey> getAuthenticationGatewayCallback() {
        return new RemoteGatewayCallback<AuthenticationKey>() {

            @Override
            public void onRemoteLoadRSuccess(AuthenticationKey retrieved) {
//                show success and show next activity
                launchActivity(LoginActivity.this, LibraryActivity.class);
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
                LogUtils.d("no data");

            }

            @Override
            public void onRemoteLoadFailed() {
                LogUtils.d("failed ");
            }
        };
    }
}