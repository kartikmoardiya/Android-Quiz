package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.quizapp.API.QueClient;
import com.example.quizapp.API.QueInterface;
import com.example.quizapp.Adapter.QueBankAdapter;
import com.example.quizapp.Models.Questions;
import com.example.quizapp.databinding.ActivityMainBinding;
import com.example.quizapp.databinding.ActivityQuestionBankBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionBank extends AppCompatActivity {

    ActivityQuestionBankBinding binding;
    ArrayList<Questions> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question_bank);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityQuestionBankBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        easyQueations();

        binding.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuestionBank.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void easyQueations()
    {
        QueInterface apiService = QueClient.getClient().create(QueInterface.class);
        Call<List<Questions>> easy = apiService.getEasyQue();

        easy.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    list.addAll(response.body());
                    mediumQuestions();
                }else{
                    Toast.makeText(QuestionBank.this, "Santi Rakh Problem chhe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                Toast.makeText(QuestionBank.this, "Santi Rakh Problem chhee", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mediumQuestions() {
        QueInterface apiService = QueClient.getClient().create(QueInterface.class);
        Call<List<Questions>> medium = apiService.getMediumQue();

        medium.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    list.addAll(response.body());
                    hardQuestions();
                }else{
                    Toast.makeText(QuestionBank.this, "Santi Rakh Problem chhe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                Toast.makeText(QuestionBank.this, "Santi Rakh Problem chhee", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hardQuestions() {
        QueInterface apiService = QueClient.getClient().create(QueInterface.class);
        Call<List<Questions>> hard = apiService.getHardQue();

        hard.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    list.addAll(response.body());
                    binding.recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    QueBankAdapter adapter = new QueBankAdapter(list);// baki
                    binding.progressBar.setVisibility(View.GONE);
                    binding.recyclerview.setAdapter(adapter);
                }else{
                    Toast.makeText(QuestionBank.this, "Santi Rakh Problem chhe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                Toast.makeText(QuestionBank.this, "Santi Rakh Problem chhe", Toast.LENGTH_SHORT).show();
            }
        });
    }
}