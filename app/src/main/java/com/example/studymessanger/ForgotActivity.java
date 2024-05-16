package com.example.studymessanger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotActivity extends AppCompatActivity {

    private static final String EXTRA_EMAIL = "email";
    private EditText editViewForgot;
    private Button buttonForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot);
        initViews();

        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        editViewForgot.setText(email);
        buttonForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editViewForgot.getText().toString().trim();
                //
            }
        });
    }

    private void initViews() {
        editViewForgot = findViewById(R.id.editViewForgot);
        buttonForgot = findViewById(R.id.buttonForgot);
    }

    public static Intent newIntent (Context context, String email) {
        Intent intent = new Intent(context, ForgotActivity.class);
        intent.putExtra(EXTRA_EMAIL, email);
        return intent;
    }
}