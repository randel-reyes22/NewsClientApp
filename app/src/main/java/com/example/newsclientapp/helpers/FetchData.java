package com.example.newsclientapp.helpers;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsclientapp.adapters.NewsModelAdapter;
import com.example.newsclientapp.interfaces.IFetchData;
import com.example.newsclientapp.interfaces.IRetroFitAPI;
import com.example.newsclientapp.models.ArticleModel;
import com.example.newsclientapp.models.NewsModel;
import com.example.newsclientapp.utils.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchData extends AppCompatActivity implements IFetchData {

    @Override
    public void fetchNews(String category, Context context, ProgressBar progressBar, NewsModelAdapter newsModelAdapter) {
        Utility.articleModelArrayList.clear();
        progressBar.setVisibility(View.VISIBLE);  //show circular progress indicator
        //api call and parse the JSON
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utility.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetroFitAPI retrofitAPI = retrofit.create(IRetroFitAPI.class);  //GET method
        Call<NewsModel> call;

        if (category.equals(all)) {
            call = retrofitAPI.getAllNews(Utility.ALL_URL);
        } else {
            call = retrofitAPI.getNewsByCategory(Utility.categoryURI(category));
        }

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                try {
                    NewsModel newsModel = response.body();

                    if (!response.isSuccessful()) {
                        Toast.makeText(FetchData.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }else {
                        addNewsToArray(newsModel.getArticles()); //add to the list
                        newsModelAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE); //hide progress bar
                    }

                } catch (Exception e) {
                    Toast.makeText(FetchData.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(FetchData.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void addNewsToArray(ArrayList<ArticleModel> articleModel) {
        //add the parse json to the constructor article model
        for (ArticleModel article : articleModel) {
            Utility.articleModelArrayList.add(new ArticleModel(article.getTitle(), article.getAuthor(), article.getPublishedAt(), article.getDescription(),
                    article.getUrlToImage(), article.getUrl(), article.getContent()));
        }
    }
}
