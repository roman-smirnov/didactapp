package com.didactapp.didact.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.didactapp.didact.R;

public class LoginActivity extends BaseActivity {


    // UI references.
    private EditText passwordEditText;
    private TextInputLayout textInputLayout;
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //get the views
        textInputLayout = findViewById(R.id.login_password_layout);
        passwordEditText = findViewById(R.id.login_password_field);
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
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}