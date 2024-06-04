package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quizapp.Models.TestActivity;
import com.example.quizapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // Question Bank vali Activity ma java mate
        binding.linearQueBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, QuestionBank.class);
                startActivity(i);
                finish();
            }
        });

        // Test vali activity ma java mate
        binding.linearTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TestActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}




//long countdownDuration = 5000; // 1 minute
//
//// Create the countdown timer
//CountDownTimer timer = new CountDownTimer(countdownDuration, 1000) {
//
//    @Override
//    public void onTick(long millisUntilFinished) {
//        // Update the countdown text with the remaining time
//        long seconds = millisUntilFinished / 1000;
//        Log.d("Tagy",""+seconds);
//    }
//
//    @Override
//    public void onFinish() {
//        // When the countdown finishes, update the text to indicate completion
//        Log.d("Tagy","Countdown Complete");
//    }
//}.start();
