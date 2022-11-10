package com.example.newsclientapp.interfaces;

import com.example.newsclientapp.models.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface IRetroFitAPI {
    @GET Call<NewsModel> getAllNews(@Url String url);
    @GET Call<NewsModel> getNewsByCategory(@Url String url);
}
