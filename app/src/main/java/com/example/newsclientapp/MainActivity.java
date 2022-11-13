package com.example.newsclientapp;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsclientapp.adapters.CategoryModelAdapter;
import com.example.newsclientapp.adapters.NewsModelAdapter;
import com.example.newsclientapp.helpers.FetchData;
import com.example.newsclientapp.utils.Utility;

public class MainActivity extends AppCompatActivity implements CategoryModelAdapter.CategoryClickInterface{

    private CategoryModelAdapter categoryModelAdapter;
    private NewsModelAdapter newsModelAdapter;
    private RecyclerView newsRV, categoryRV;
    private ProgressBar progressBar;
    private FetchData getData = new FetchData();
    private final String all = "All";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //views
        newsRV = findViewById(R.id.idRVNews);
        categoryRV = findViewById(R.id.idRVCategories);
        progressBar = findViewById(R.id.idCircularProgress);

        newsModelAdapter = new NewsModelAdapter(this);
        categoryModelAdapter =  new CategoryModelAdapter(this, this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsModelAdapter);
        categoryRV.setAdapter(categoryModelAdapter);

        getData.fetchNews(all, this.context, progressBar);
        newsModelAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCategoryClick(int position) {
        String category = Utility.categoryModelArrayList.get(position).getCategory();
        getData.fetchNews(category, this.context, progressBar);
        newsModelAdapter.notifyDataSetChanged();
    }
}
