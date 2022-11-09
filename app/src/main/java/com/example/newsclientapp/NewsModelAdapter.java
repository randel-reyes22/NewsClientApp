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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsModelAdapter extends RecyclerView.Adapter<NewsModelAdapter.ViewHolder>{
    private ArrayList<ArticleModel> articlesArrayList;
    private Context context;

    public NewsModelAdapter(ArrayList<ArticleModel> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_items, parent,false);
        return new NewsModelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsModelAdapter.ViewHolder holder, int position) {
        ArticleModel articles = articlesArrayList.get(position);
        holder.subTitleTV.setText(articles.getDescription());
        holder.titleTV.setText(articles.getTitle());
        holder.publishedAtTV.setText(articles.getPublishedAt());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsIV);

        /*holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context,NewsDetails.class);
            i.putExtra("title",articles.getTitle());
            i.putExtra("author",articles.getAuthor());
            i.putExtra("publishedAt",articles.getPublishedAt());
            i.putExtra("content",articles.getContent());
            i.putExtra("description",articles.getDescription());
            i.putExtra("image",articles.getUrlToImage());
            i.putExtra("url",articles.getUrl());
            context.startActivity(i);

        });*/
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
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
