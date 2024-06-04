package com.example.studymessanger;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ChatViewModelFactory implements ViewModelProvider.Factory {

    private String currentUserId;
    private String otherUserid;

    public ChatViewModelFactory(String currentUserId, String otherUserid) {
        this.currentUserId = currentUserId;
        this.otherUserid = otherUserid;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ChatViewModel(currentUserId, otherUserid);
    }
}
