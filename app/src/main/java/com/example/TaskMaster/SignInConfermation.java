package com.example.TaskMaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.core.Amplify;

public class SignInConfermation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_confermation);
        Amplify.Auth.confirmSignUp(
                "username",
                "the code you received via email",
                result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }
}