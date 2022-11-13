package com.example.newsclientapp.helpers;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsclientapp.adapters.NewsModelAdapter;
import com.example.newsclientapp.interfaces.IFetchData;
import com.example.newsclientapp.models.ArticleModel;
import com.example.newsclientapp.models.NewsModel;
import com.example.newsclientapp.utils.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FetchData extends AppCompatActivity implements IFetchData {

    private OkHttpClient client =  new OkHttpClient();
    private Gson parseGson = new Gson();

    @Override
    public void fetchNews(String category, Context context, ProgressBar progressBar)  {
        Utility.articleModelArrayList.clear();

        progressBar.setVisibility(View.VISIBLE);

        try{
            Request request = new Request.Builder()
                    .url(getUrl(category))
                    .build();

            //get method
            try(Response res = client.newCall(request).execute()){
                if(res.isSuccessful()){
                    NewsModel newsModel = parseGson.fromJson(res.body().string(), NewsModel.class);
                    addNewsToArray(newsModel.getArticles());

                }
            }

            progressBar.setVisibility(View.GONE);
        }
        catch (IOException | IllegalStateException | JsonSyntaxException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addNewsToArray(ArrayList<ArticleModel> articleModel) {
        //add the parse json to the constructor article model
        for (ArticleModel article : articleModel) {
            Utility.articleModelArrayList.add(new ArticleModel(article.getTitle(), article.getAuthor(), article.getPublishedAt(), article.getDescription(),
                    article.getUrlToImage(), article.getUrl(), article.getContent()));
        }
    }

    private String getUrl(String category) {
        if(category == all)
            return Utility.ALL_URL;
        else
            return Utility.categoryURI(category);
    }
}
