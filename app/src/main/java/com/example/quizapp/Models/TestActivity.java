package com.example.quizapp.Models;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.example.quizapp.QuestionBank;
import com.example.quizapp.R;
import com.example.quizapp.Result;
import com.example.quizapp.databinding.ActivityQuestionBankBinding;
import com.example.quizapp.databinding.ActivityTestBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    int randomNumber;
    int flag = 0;
    ActivityTestBinding binding;
    ArrayList<Questions> easylist = new ArrayList<>();
    ArrayList<Questions> mediumlist = new ArrayList<>();
    ArrayList<Questions> hardlist = new ArrayList<>();
    ArrayList<Questions> finalListFirst = new ArrayList<>();
    ArrayList<String> finalListSecond = new ArrayList<>();
    int queNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityTestBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        easyQueations();
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the ID of the selected RadioButton within the RadioGroup
                int selectedRadioButtonId = binding.radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {

                    // At least one RadioButton is selected within the RadioGroup
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                    finalListSecond.add(selectedRadioButton.getText().toString());
                    if (queNumber >= 1 && queNumber <= 3) {
                        finalListFirst.add(easylist.get(randomNumber));
                        easylist.remove(randomNumber);
                    } else if (queNumber >= 4 && queNumber <= 6) {
                        finalListFirst.add(mediumlist.get(randomNumber));
                        mediumlist.remove(randomNumber);
                    } else if (queNumber >= 7 && queNumber <= 10) {
                        finalListFirst.add(hardlist.get(randomNumber));
                        hardlist.remove(randomNumber);
                    }
                    funcQueNumber();
                } else {
                    Toast.makeText(TestActivity.this, "Bhai Kyk Selecet to Kr", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void easyQueations() {
        QueInterface apiService = QueClient.getClient().create(QueInterface.class);
        Call<List<Questions>> easy = apiService.getEasyQue();

        easy.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    easylist.addAll(response.body());
                    mediumQuestions();
                } else {
                    Toast.makeText(getApplicationContext(), "Santi Rakh Problem chhe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Santi Rakh Problem chhee", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mediumQuestions() {
        QueInterface apiService = QueClient.getClient().create(QueInterface.class);
        Call<List<Questions>> medium = apiService.getMediumQue();

        medium.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mediumlist.addAll(response.body());
                    hardQuestions();
                } else {
                    Toast.makeText(getApplicationContext(), "Santi Rakh Problem chhe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Santi Rakh Problem chhee", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void hardQuestions() {
        QueInterface apiService = QueClient.getClient().create(QueInterface.class);
        Call<List<Questions>> hard = apiService.getHardQue();

        hard.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    hardlist.addAll(response.body());
                    funcQueNumber();
                } else {
                    Toast.makeText(getApplicationContext(), "Santi Rakh Problem chhe", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Santi Rakh Problem chhe", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void funcQueNumber() {
        binding.radioGroup.clearCheck();
        countDown();
        queNumber = queNumber + 1;
        Random random = new Random();
        Questions questions = new Questions();
        int min = 0, max;
        if (queNumber >= 1 && queNumber <= 3) {
            max = easylist.size() - 1;
            randomNumber = random.nextInt(max - min + 1) + min;
            questions = easylist.get(randomNumber);
        } else if (queNumber >= 4 && queNumber <= 6) {
            max = mediumlist.size() - 1;
            randomNumber = random.nextInt(max - min + 1) + min;
            questions = mediumlist.get(randomNumber);
        } else if (queNumber >= 7 && queNumber <= 10) {
            max = hardlist.size() - 1;
            randomNumber = random.nextInt(max - min + 1) + min;
            questions = hardlist.get(randomNumber);
        }else{
            // Create a Bundle and put the ArrayList in it
            Bundle bundle = new Bundle();
            bundle.putSerializable("finalListFirst", finalListFirst);
            bundle.putStringArrayList("finalListSecond", finalListSecond);
            Intent i = new Intent(TestActivity.this, Result.class);
            i.putExtras(bundle);
            startActivity(i);
            return;
        }
        binding.txtQue.setText(queNumber + ". " + questions.getQue());
        binding.txtOptionA.setText(questions.getOptionA());
        binding.txtOptionB.setText(questions.getOptionB());
        binding.txtOptionC.setText(questions.getOptionC());
        binding.txtOptionD.setText(questions.getOptionD());
        if (flag == 0) {
            binding.progressBar.setVisibility(View.GONE);
            binding.linearLayout.setVisibility(View.VISIBLE);
            flag = 1;
        }
        return;
    }

    private CountDownTimer timer; // Declare a member variable to hold the active countdown timer

    public void countDown() {
        long countdownDuration = 30000; // 1 minute

        // Cancel the previous countdown timer if it's active
        if (timer != null) {
            timer.cancel();
        }

        // Create the countdown timer
        timer = new CountDownTimer(countdownDuration, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // Update the countdown text with the remaining time
                long seconds = millisUntilFinished / 1000;
                // Log.d("Tagy", "" + seconds);
                binding.txtCountDown.setText("" + seconds);
            }

            @Override
            public void onFinish() {
                if(queNumber > 10)
                {
                    return;
                }
                // Get the ID of the selected RadioButton within the RadioGroup
                int selectedRadioButtonId = binding.radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {

                    // At least one RadioButton is selected within the RadioGroup
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                    finalListSecond.add(selectedRadioButton.getText().toString());
                    if (queNumber >= 1 && queNumber <= 3) {
                        finalListFirst.add(easylist.get(randomNumber));
                        easylist.remove(randomNumber);
                    } else if (queNumber >= 4 && queNumber <= 6) {
                        finalListFirst.add(mediumlist.get(randomNumber));
                        mediumlist.remove(randomNumber);
                    } else if (queNumber >= 7 && queNumber <= 10) {
                        finalListFirst.add(hardlist.get(randomNumber));
                        hardlist.remove(randomNumber);
                    }
                } else {
                    // At least one RadioButton is selected within the RadioGroup
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                    finalListSecond.add("-");
                    if (queNumber >= 1 && queNumber <= 3) {
                        finalListFirst.add(easylist.get(randomNumber));
                        easylist.remove(randomNumber);
                    } else if (queNumber >= 4 && queNumber <= 6) {
                        finalListFirst.add(mediumlist.get(randomNumber));
                        mediumlist.remove(randomNumber);
                    } else if (queNumber >= 7 && queNumber <= 10) {
                        finalListFirst.add(hardlist.get(randomNumber));
                        hardlist.remove(randomNumber);
                    }
                }
                funcQueNumber();
                // When the countdown finishes, update the text to indicate completion
                // Log.d("Tagy", "Countdown Complete");
            }
        }.start();
    }
}