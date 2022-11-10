package com.example.newsclientapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.newsclientapp.adapters.CategoryModelAdapter;
import com.example.newsclientapp.adapters.NewsModelAdapter;
import com.example.newsclientapp.interfaces.RetroFitAPI;
import com.example.newsclientapp.models.ArticleModel;
import com.example.newsclientapp.models.NewsModel;
import com.example.newsclientapp.utils.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryModelAdapter.CategoryClickInterface{

    private CategoryModelAdapter categoryModelAdapter;
    private NewsModelAdapter newsModelAdapter;
    private RecyclerView newsRV, categoryRV;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //views
        newsRV = findViewById(R.id.idRVNews);
        categoryRV = findViewById(R.id.idRVCategories);
        progressBar = findViewById(R.id.idPBLoading);

        //arraylist
        Utility.articleModelArrayList = new ArrayList<>();
        Utility.categoryModelArrayList = new ArrayList<>();

        newsModelAdapter = new NewsModelAdapter(this);
        categoryModelAdapter =  new CategoryModelAdapter(this, this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsModelAdapter);
        categoryRV.setAdapter(categoryModelAdapter);

        //get all categories
        Utility.fetchCategories();
        categoryModelAdapter.notifyDataSetChanged();

        fetchNews("All");
        newsModelAdapter.notifyDataSetChanged();
    }

    private void fetchNews(String category){
        //while fetching the JSON
        //display progress bar until parsing finishes
        progressBar.setVisibility(View.VISIBLE);
        Utility.articleModelArrayList.clear();

        //api call and parse the JSON
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utility.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroFitAPI retrofitAPI = retrofit.create(RetroFitAPI.class);
        Call<NewsModel> call;

        if(category.equals("All")){
            call = retrofitAPI.getAllNews(Utility.ALL_URL);
        }
        else{
            call = retrofitAPI.getNewsByCategory(Utility.categoryURI(category));
        }

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();

                progressBar.setVisibility(View.GONE);
                ArrayList<ArticleModel> articles = newsModel.getArticles();

                //add the parse json to the constructor article model
                for(ArticleModel article: articles){
                    Utility.articleModelArrayList.add(new ArticleModel(article.getTitle(), article.getAuthor(), article.getPublishedAt(), article.getDescription(),
                            article.getUrlToImage(), article.getUrl(), article.getContent()));
                }
                newsModelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong while fetching news", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        String category = Utility.categoryModelArrayList.get(position).getCategory();
        fetchNews(category);
    }
}