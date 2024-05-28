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

import com.google.firebase.auth.FirebaseUser;

import javax.xml.transform.sax.SAXResult;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private EditText editViewEmail;
    private EditText editViewPassword;
    private EditText editViewName;
    private EditText editViewLastName;
    private EditText editViewOld;
    private Button buttonRegister;

    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        initViews();

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        observeViewModel();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getTrimmedValue(editViewEmail);
                String password = getTrimmedValue(editViewPassword);
                String name = getTrimmedValue(editViewName);
                String lastName = getTrimmedValue(editViewLastName);
                int age = Integer.parseInt(getTrimmedValue(editViewOld));


                viewModel.singUp(
                        email, password, name, lastName, age
                );
            }
        });
    }

    private void observeViewModel() {
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMess) {
                if (errorMess != null) {
                    Toast.makeText(RegisterActivity.this, errorMess, Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    Intent intent = UserActivity.newIntent(
                            RegisterActivity.this,
                            firebaseUser.getUid());
                    startActivity(intent);
                    finish();
                }
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