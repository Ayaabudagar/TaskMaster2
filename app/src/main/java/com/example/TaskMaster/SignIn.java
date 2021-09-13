package com.example.TaskMaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), "my@email.com")
                .build();
        Amplify.Auth.signUp("username", "Password123", options,
                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
                error -> Log.e("AuthQuickStart", "Sign up failed", error)
        );

    }
}