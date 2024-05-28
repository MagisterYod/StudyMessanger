package com.example.studymessanger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class UserActivity extends AppCompatActivity {

    private static final String EXTRA_CURRENT_USER_ID = "current_id";
    private RecyclerView recyclerViewUsers;
    private UserAdapter userAdapter;
    private UserViewModel viewModel;
//    private Toolbar toolbar;
    private String curentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);
//        toolbar = findViewById(R.id.toolBar);
        initViews();
        curentUserId = getIntent().getStringExtra(EXTRA_CURRENT_USER_ID);
//        setSupportActionBar(toolbar);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        observeViewModel();
        userAdapter.setOnUserClickListner(new UserAdapter.OnUserClickListner() {
            @Override
            public void onUserClick(User user) {
                Intent intent = ChatActivity.newIntent(
                        UserActivity.this,
                        curentUserId,
                        user.getId());
                startActivity(intent);
            }

        });
    }

    private void initViews() {
        recyclerViewUsers = findViewById(R.id.recyclerViewUser);
        userAdapter = new UserAdapter();
        recyclerViewUsers.setAdapter(userAdapter);
    }

    private void observeViewModel() {
        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser == null) {
                    Intent intent = LoginActivity.newIntent(UserActivity.this);
                    startActivity(intent);
                    finish();
                }
            }
        });
        viewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userAdapter.setUsers(users);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_logout) {
            viewModel.logout();
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent newIntent(Context context, String curentUserId) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(EXTRA_CURRENT_USER_ID, curentUserId);
        return intent;
    }
}