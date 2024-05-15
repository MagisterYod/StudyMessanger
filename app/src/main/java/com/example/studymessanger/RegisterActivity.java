package com.example.studymessanger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private EditText editViewEmail;
    private EditText editViewPassword;
    private EditText editViewName;
    private EditText editViewLastName;
    private EditText editViewOld;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        initViews();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getTrimmedValue(editViewEmail);
                String password = getTrimmedValue(editViewPassword);
                String name = getTrimmedValue(editViewName);
                String lastName = getTrimmedValue(editViewLastName);
                int age = Integer.parseInt(getTrimmedValue(editViewOld));
                //register
            }
        });
    }

    private void initViews() {
         editViewEmail = findViewById(R.id.editViewEmail);
         editViewPassword = findViewById(R.id.editViewPassword);
         editViewName = findViewById(R.id.editViewName);
         editViewLastName = findViewById(R.id.editViewLastName);
         editViewOld = findViewById(R.id.editViewOld);
         buttonRegister = findViewById(R.id.buttonRegister);
    }

    private String getTrimmedValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }
}