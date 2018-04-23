package com.didactapp.didact.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.didactapp.didact.R;
import com.didactapp.didact.entities.AuthenticationKey;
import com.didactapp.didact.entities.EncryptUser;
import com.didactapp.didact.entities.PublicKey;
import com.didactapp.didact.entities.User;
import com.didactapp.didact.network.PublicKeyRemoteGateway;
import com.didactapp.didact.network.RegisterRemoteGateway;
import com.didactapp.didact.network.RemoteGatewayCallback;
import com.didactapp.didact.userEncrypt.JWTEncrypt;


public class LoginActivity extends BaseActivity implements View.OnClickListener {


    // UI references.
    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button registerButton;

    private com.didactapp.didact.entities.PublicKey publicKey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // get the views
        email = findViewById(R.id.login_email_field);
        password = findViewById(R.id.login_password_field);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(LoginActivity.this, LibraryActivity.class);
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchActivity(LoginActivity.this, RegisterActivity.class);
            }
        });

        PublicKeyRemoteGateway publicKeyRemoteGateway = PublicKeyRemoteGateway.getInstance();
        publicKeyRemoteGateway.getFromRemote(getPublicKeyGatewayCallback(), "");
    }

//    TODO implement login call (just like register but dif err)

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
            User user = new User(email.getText().toString(), password.getText().toString());
            /* encrypt and send to server */
            String encrypt = new JWTEncrypt().encrypt(publicKey.getKey(), user);
            RegisterRemoteGateway registerRemoteGateway = RegisterRemoteGateway.getInstance();
            registerRemoteGateway.getFromRemote(this, new EncryptUser(encrypt, publicKey.getKey()));
        }
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