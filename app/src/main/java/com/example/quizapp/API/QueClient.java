package com.example.quizapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QueClient {
    private static final String BASE_URL = "https://quiz-olive-two.vercel.app/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
