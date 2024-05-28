package com.example.studymessanger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ChatActivity extends AppCompatActivity {
    private static final String EXTRA_CURRENT_USER_ID = "current_id";
    private static final String EXTRA_OTHER_USER_ID = "other_id";

    private TextView textViewTitle;
    private View viewIsOnline;
    private RecyclerView recyclerViewChat;
    private EditText editTextMessage;
    private ImageView sendMessage;

    private ChatAdapter chatAdapter;
    private String curentUserId;
    private String otherUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);
        initViews();
        curentUserId = getIntent().getStringExtra("EXTRA_CURRENT_USER_ID");
        otherUserId = getIntent().getStringExtra("EXTRA_OTHER_USER_ID");
        chatAdapter = new ChatAdapter(curentUserId);
        recyclerViewChat.setAdapter(chatAdapter);
    }

    private void initViews() {
        textViewTitle = findViewById(R.id.textViewTitle);
        viewIsOnline = findViewById(R.id.viewIsOnline);
        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        editTextMessage = findViewById(R.id.editTextMessage);
        sendMessage = findViewById(R.id.sendMessage);
    }

    public static Intent newIntent(Context context, String curentUserId, String otherUserId) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(EXTRA_CURRENT_USER_ID, curentUserId);
        intent.putExtra(EXTRA_OTHER_USER_ID, otherUserId);
        return intent;
    }
}