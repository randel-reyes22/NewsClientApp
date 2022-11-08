package com.example.newsclientapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.newsclientapp.interfaces.RetroFitAPI;
import com.example.newsclientapp.models.ArticleModel;
import com.example.newsclientapp.models.CategoryModel;
import com.example.newsclientapp.models.NewsModel;
import com.example.newsclientapp.utils.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{

    private RecyclerView newsRV, categoryRV;
    private ProgressBar loadingPB;
    private CategoryModelAdapter categoryModelAdapter;
    private NewsModelAdapter newsModelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV = findViewById(R.id.idRVNews);
        categoryRV = findViewById(R.id.idRVCategories);
        loadingPB = findViewById(R.id.idPBLoading);
        newsModelAdapter = new NewsModelAdapter(Utility.articleModelArrayList, this);
        categoryModelAdapter =  new CategoryModelAdapter(Utility.categoryModelArrayList, this, this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsModelAdapter);
        categoryRV.setAdapter(categoryModelAdapter);

        //get all categories
        getCategories();
        getNews("All");
        newsModelAdapter.notifyDataSetChanged();
    }

    private void getCategories(){
        Utility.categoryModelArrayList.add(new CategoryModel("All"));
        Utility.categoryModelArrayList.add(new CategoryModel("Technology"));
        Utility.categoryModelArrayList.add(new CategoryModel("Science"));
        Utility.categoryModelArrayList.add(new CategoryModel("Sports"));
        Utility.categoryModelArrayList.add(new CategoryModel("General"));
        Utility.categoryModelArrayList.add(new CategoryModel("Business"));
        Utility.categoryModelArrayList.add(new CategoryModel("Entertainment"));
        Utility.categoryModelArrayList.add(new CategoryModel("Health"));
        categoryModelAdapter.notifyDataSetChanged();
    }

    private void getNews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        Utility.articleModelArrayList.clear();

        //urls
        String categoryUrl = "https://newsapi.org/v2/top-headlines?country=ph&category="+category+"&apiKey=c5dc361f9ba242cbb050af03e53b022d";
        String url = "https://newsapi.org/v2/top-headlines?country=ph&apiKey=aacbed560bc0495fb66a35c90f0d0852";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroFitAPI retrofitAPI = retrofit.create(RetroFitAPI.class);
        Call<NewsModel> call;

        if(category.equals("All")){
            call = retrofitAPI.getAllNews(url);
        }
        else{
            call = retrofitAPI.getNewsByCategory(categoryUrl);
        }

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModal = response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<ArticleModel> articles = newsModal.getArticles();
                for (int i = 0; i < articles.size(); i++)
                {
                    Utility.articleModelArrayList.add(new ArticleModel(articles.get(i).getTitle(), articles.get(i).getAuthor(), articles.get(i).getPublishedAt(),articles.get(i).getDescription(), articles.get(i).getUrlToImage(), articles.get(i).getUrl(), articles.get(i).getContent()));

                }
                newsModelAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to get news", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onCategoryClick(int position) {
        String category = Utility.categoryModelArrayList.get(position).getCategory();
        getNews(category);
    }
}