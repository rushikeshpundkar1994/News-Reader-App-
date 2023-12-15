package com.example.newsreaderapprushikesh.restapi;


import com.example.newsreaderapprushikesh.model.TotalNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestInterface {
    @GET("v2/top-headlines")
    Call<TotalNews> getTotalNews(@Query("country") String country, @Query("pageSize") int pageSize, @Query("apiKey") String apiKey);

}
