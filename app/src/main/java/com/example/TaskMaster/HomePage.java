package com.example.TaskMaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    public static final String TAG = HomePage.class.getSimpleName();

    private static PinpointManager pinpointManager;

    public static PinpointManager getPinpointManager(final Context applicationContext) {
        if (pinpointManager == null) {
            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    Log.i("INIT", String.valueOf(userStateDetails.getUserState()));
                }

                @Override
                public void onError(Exception e) {
                    Log.e("INIT", "Initialization error.", e);
                }
            });

            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
                    applicationContext,
                    AWSMobileClient.getInstance(),
                    awsConfig);

            pinpointManager = new PinpointManager(pinpointConfig);

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            final String token = task.getResult();
                            Log.d(TAG, "Registering push notifications token: " + token);
                            pinpointManager.getNotificationClient().registerDeviceToken(token);
                        }
                    });
        }
        return pinpointManager;
    }















    public void singIn(){
        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), "haneenalwatan993@gmail.com")
                .build();
//        Amplify.Auth.signUp("aya", "Password12366", options,
//                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
//                error -> Log.e("AuthQuickStart", "Sign up failed", error)
//        );
//        Amplify.Auth.confirmSignUp(
//                "aya",
//                "655897",
//                result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
//        Amplify.Auth.signIn(
//                "aya",
//                "Password12366",
//                result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
        Amplify.Auth.signInWithWebUI(
                HomePage.this,
                result -> Log.i("AuthQuickStart", result.toString()),
                error -> Log.e("AuthQuickStart", error.toString())
        );

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            getPinpointManager(getApplicationContext());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        singIn();
        Button signOut = findViewById(R.id.signOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        () -> { singIn();
                            Log.i("AuthQuickstart", "Signed out successfully");},

                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });








//
//        appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "tasks").allowMainThreadQueries()
//        .build();
//        List<Task> taskList = appDataBase.taskDao().getAll();
//        // get the recycler view
//        RecyclerView allTasksRecuclerView = findViewById(R.id.rs);
//        // set a layout manager for this view
//        allTasksRecuclerView.setLayoutManager(new LinearLayoutManager(this));
//        // set the adapter for this recyclerView
//        allTasksRecuclerView.setAdapter(new TaskAdapter(taskList));




        Button addTask = (Button) findViewById(R.id.button);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNewTask = new Intent(HomePage.this, Addtask.class);
                startActivity(addNewTask);
            }
        });
        Button allTaskss = (Button) findViewById(R.id.button2);
        allTaskss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAllTasks = new Intent(HomePage.this, Alltasks.class);
                startActivity(toAllTasks);
            }
        });
        Button setting =  findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSettingPage = new Intent(HomePage.this, Setting.class);
                startActivity(toSettingPage);
            }
        });
        Button labs401= (Button) findViewById(R.id.button4);
        labs401.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLabsPage = new Intent(HomePage.this, Labs401.class);
                startActivity(toLabsPage);
            }
        });
        Button codeChallenges401= (Button) findViewById(R.id.button5);
        codeChallenges401.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCodechallengesPage = new Intent(HomePage.this, CodeChallenges401.class);
                startActivity(toCodechallengesPage);
            }
        });
        Button sport= (Button) findViewById(R.id.button6);
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSportPagePage = new Intent(HomePage.this, Sport.class);
                startActivity(toSportPagePage);
            }
        });
        ArrayList<Todo> allTasks = new ArrayList<Todo>();

        RecyclerView allTask = findViewById(R.id.rs);


        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {  //It will notify the recyclerview that there are a data changed
                allTask.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Todo.class),
                response -> {

                    for (Todo todo : response.getData()) {
                        Log.i("MyAmplifyApp", todo.getTitle());
                        Log.i("MyAmplifyApp", todo.getBody());
                        Log.i("MyAmplifyApp", todo.getState());

                        allTasks.add(todo);

                    }
                    handler.sendEmptyMessage(1); // send to the handler
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        allTask.setLayoutManager(new LinearLayoutManager(this));
        allTask.setAdapter(new TaskAdapter(allTasks));




    }

    @Override
    protected void onResume() {
        super.onResume();
        String userTaskMessage = "’s tasks";

//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(HomePage.this);
//        String instructorName = sharedPreferences.getString("instructorName", "Instructor");
        TextView instructorNameView = findViewById(R.id.textView14);
        instructorNameView.setText( com.amazonaws.mobile.client.AWSMobileClient.getInstance().getUsername()+userTaskMessage );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE) {
            Amplify.Auth.handleWebUISignInResponse(data);
        }
    }
}
