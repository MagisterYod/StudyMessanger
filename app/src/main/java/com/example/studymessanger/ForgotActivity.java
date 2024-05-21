package com.example.studymessanger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ForgotActivity extends AppCompatActivity {

    private static final String EXTRA_EMAIL = "email";
    private EditText editViewForgot;
    private Button buttonForgot;
    private ForgotVewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot);
        initViews();

        viewModel = new ViewModelProvider(this).get(ForgotVewModel.class);
        observeViewModel();
        String email = getIntent().getStringExtra(EXTRA_EMAIL);
        editViewForgot.setText(email);
        buttonForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editViewForgot.getText().toString().trim();
                viewModel.resetPassword(email);
            }
        });
    }

    private void observeViewModel() {
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMess) {
                if (errorMess != null) {
                    Toast.makeText(
                            ForgotActivity.this,
                            errorMess,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.getSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if (success) {
                    Toast.makeText(
                            ForgotActivity.this,
                            R.string.reset_message,
                            Toast.LENGTH_SHORT).show();
                }
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