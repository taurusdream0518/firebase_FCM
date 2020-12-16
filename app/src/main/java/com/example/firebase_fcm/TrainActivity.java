package com.example.firebase_fcm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TrainActivity extends AppCompatActivity {

    private TextView textViewTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        textViewTrain = (TextView) findViewById(R.id.textView_train);
        textViewTrain.setText("");



    }
}