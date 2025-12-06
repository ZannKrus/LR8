package com.example.lr8;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("ege-calc/json")
    Call<List<Specialty>> calculate(@Body UserScores scores);
}