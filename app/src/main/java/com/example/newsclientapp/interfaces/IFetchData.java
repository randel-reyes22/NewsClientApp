package com.example.newsclientapp.interfaces;

import android.content.Context;
import android.widget.ProgressBar;

import com.example.newsclientapp.adapters.NewsModelAdapter;
import com.example.newsclientapp.models.ArticleModel;
import com.example.newsclientapp.models.NewsModel;

import java.util.ArrayList;

public interface IFetchData {
    //represents the all category
    String all = "All";
    void fetchNews(String category, Context context, ProgressBar progressBar, NewsModelAdapter newsModelAdapter);
    void addNewsToArray(ArrayList<ArticleModel> articleModel);
}
