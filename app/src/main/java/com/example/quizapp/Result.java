package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.quizapp.Adapter.ResultAdapter;
import com.example.quizapp.Models.Questions;
import com.example.quizapp.Models.TestActivity;
import com.example.quizapp.databinding.ActivityResultBinding;
import com.example.quizapp.databinding.ActivityTestBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Result extends AppCompatActivity {

    int correctAns = 0;
    ActivityResultBinding binding;
    ArrayList<Questions> finalListFirst = new ArrayList<>();
    ArrayList<String> finalListSecond = new ArrayList<>();
    ArrayList<Pair<Questions,String>> finalList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityResultBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Retrieve the Bundle from the Intent
        Bundle bundle = getIntent().getExtras();

        // Check if the Bundle is not null
        if (bundle != null) {
            // Retrieve the ArrayList from the Bundle
            finalListFirst = (ArrayList<Questions>) bundle.getSerializable("finalListFirst");
            finalListSecond = (ArrayList<String>) bundle.getStringArrayList("finalListSecond");

            if (finalListFirst != null && finalListSecond != null) {
                for (int i = 0; i < finalListFirst.size(); i++) {
                    if (finalListFirst.get(i).getAnswer().equalsIgnoreCase(finalListSecond.get(i)))
                    {
                        correctAns++;
                    }
                    finalList.add(new Pair<>(finalListFirst.get(i), finalListSecond.get(i)));
                }
                if(correctAns <= 3)
                {
                    binding.victorymsg.setText("LOSE");
                }
                binding.right.setText(correctAns+"");
                binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
                ResultAdapter adapter = new ResultAdapter(finalList);
                binding.recyclerview.setAdapter(adapter);
            } else {
                // Handle the case where the list is not received properly
            }
        } else {
            // Handle the case where the Bundle is null
        }

        binding.btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Result.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        binding.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Result.this, TestActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}