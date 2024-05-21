package com.example.studymessanger;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterViewModel extends ViewModel {
    private FirebaseAuth auth;

    private MutableLiveData<String> error = new MutableLiveData<>();
    private MutableLiveData<FirebaseUser> user = new MutableLiveData<>();


    public LiveData<String> getError() {
        return error;
    }

    public LiveData<FirebaseUser> getUser() {
        return user;
    }

    public RegisterViewModel() {

        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user.setValue(firebaseAuth.getCurrentUser());
            }
        });
    }

    public void singUp(
            String mail,
            String password,
            String name,
            String lastName,
            int age
    ) {
        auth.createUserWithEmailAndPassword(mail, password).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                error.setValue(e.getMessage());
            }
        });
    }
}
