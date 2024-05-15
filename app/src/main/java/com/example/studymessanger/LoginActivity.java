package com.example.studymessanger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText inputEmail;
    private EditText inputPassword;
    private Button inputButton;
    private TextView textViewForgotPass;
    private TextView textViewRegister;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        initViews();

        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                //login
            }
        });
        textViewForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = RegisterActivity.newIntent(LoginActivity.this);
                startActivity(intent);
            }
        });


    }

    private void initViews() {
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputButton = findViewById(R.id.inputButton);
        textViewForgotPass = findViewById(R.id.textViewForgotPass);
        textViewRegister = findViewById(R.id.textViewRegister);
    }
}