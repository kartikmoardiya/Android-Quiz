package com.example.quizapp.API;

import com.example.quizapp.Models.Questions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QueInterface {
    @GET("/que/easy")
    Call<List<Questions>> getEasyQue();

    @GET("/que/medium")
    Call<List<Questions>> getMediumQue();

    @GET("/que/hard")
    Call<List<Questions>> getHardQue();

}
