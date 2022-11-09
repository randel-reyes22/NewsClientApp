package com.example.newsclientapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsclientapp.models.ArticleModel;
import com.example.newsclientapp.utils.Utility;
import com.squareup.picasso.Picasso;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class NewsModelAdapter extends RecyclerView.Adapter<NewsModelAdapter.ViewHolder>{
    private ArrayList<ArticleModel> articleModelArrayList;
    private Context context;


    public NewsModelAdapter(ArrayList<ArticleModel> articlesArrayList, Context context) {
        this.articleModelArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_items, parent,false);
        return new NewsModelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsModelAdapter.ViewHolder container, int position) {
        ArticleModel articles = articleModelArrayList.get(position);
        container.titleTV.setText(articles.getTitle());
        container.subTitleTV.setText(articles.getDescription());
        container.publishedAtTV.setText(articles.getPublishedAt());

        //put the url image in the picasso api
        if(articles.getUrlToImage() == null || articles.getUrlToImage().isEmpty()){
            Picasso.get().load(Utility.emptyImageLink).into(container.newsIV);
        }else{
            Picasso.get().load(articles.getUrlToImage()).into(container.newsIV);
        }

        try {
            container.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, NewsDetails.class);
                intent.putExtra("title", articles.getTitle());
                intent.putExtra("author", articles.getAuthor());
                intent.putExtra("publishedAt",articles.getPublishedAt());
                intent.putExtra("description",articles.getDescription());
                intent.putExtra("content",articles.getContent());
                intent.putExtra("image",articles.getUrlToImage());
                intent.putExtra("url",articles.getUrl());
                context.startActivity(intent);
            });
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public int getItemCount() {
        return articleModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        private TextView titleTV, publishedAtTV,subTitleTV;
        private ImageView newsIV;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.idTVNewsHeading);
            subTitleTV = itemView.findViewById(R.id.idTVSubtitle);
            publishedAtTV = itemView.findViewById(R.id.idTVPublishedAt);
            newsIV = itemView.findViewById(R.id.idIVNews);
        }
    }
}
