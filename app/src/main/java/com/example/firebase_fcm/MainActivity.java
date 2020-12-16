package com.example.firebase_fcm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private TextView textViewData;
    private Context context;
    private int sdkVersion;
    private int minVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        textViewData = (TextView) findViewById(R.id.textView_data);
        textViewData.setText("FCM data");

        sdkVersion = Build.VERSION.SDK_INT;
        Log.d("main","sdk version = "+sdkVersion);
        minVersion = Build.VERSION_CODES.O;
        Log.d("main","min version = "+minVersion);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(task.isSuccessful()){
                    String token = task.getResult();
                    Log.d("main","token = "+token);
                } else {
                    Log.d("main","FCM token fail");
                }
            }
        });
        //TODO:取出資料
        if(getIntent().getExtras() !=null){
            textViewData.setText("");
            Log.d("main","get Intent");
            for(String key : getIntent().getExtras().keySet()){
                textViewData.append("Key = "+key+" , ");
                Object value = getIntent().getExtras().get(key);
                textViewData.append("value = "+value+ "\n\n");
                Log.d("main","key = "+key);
            }
        }

    }
}