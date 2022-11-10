package com.example.newsclientapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsclientapp.NewsDetails;
import com.example.newsclientapp.R;
import com.example.newsclientapp.models.ArticleModel;
import com.example.newsclientapp.utils.Utility;
import com.squareup.picasso.Picasso;

public class NewsModelAdapter extends RecyclerView.Adapter<NewsModelAdapter.ViewHolder>{
    private Context context;

    public NewsModelAdapter(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTV, publishedAtTV,subTitleTV;
        private ImageView newsIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.idTVNewsHeading);
            subTitleTV = itemView.findViewById(R.id.idTVSubtitle);
            publishedAtTV = itemView.findViewById(R.id.idTVPublishedAt);
            newsIV = itemView.findViewById(R.id.idIVNews);
        }
    }

    @NonNull
    @Override
    public NewsModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_items, parent,false);
        return new NewsModelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsModelAdapter.ViewHolder container, int index) {
        //get the current model position
        ArticleModel article = Utility.articleModelArrayList.get(index);

        //set the parameters
        container.titleTV.setText(article.getTitle());
        container.subTitleTV.setText(article.getDescription());
        container.publishedAtTV.setText(article.getPublishedAt());

        //put the url image in the picasso api
        if(article.getUrlToImage() == null || article.getUrlToImage().isEmpty()){
            Picasso.get().load(Utility.emptyImageLink).into(container.newsIV);
        }else{
            Picasso.get().load(article.getUrlToImage()).into(container.newsIV);
        }

        //call the news details layout
        //listens for clicks in the containers
        callNewsDetailsView(container, article);

    }

    private void callNewsDetailsView(@NonNull NewsModelAdapter.ViewHolder container, ArticleModel arc){
        try {
            container.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, NewsDetails.class);
                intent.putExtra("title", arc.getTitle());
                intent.putExtra("author", arc.getAuthor());
                intent.putExtra("publishedAt", arc.getPublishedAt());
                intent.putExtra("description", arc.getDescription());
                intent.putExtra("content", arc.getContent());
                intent.putExtra("image", arc.getUrlToImage());
                intent.putExtra("url", arc.getUrl());
                context.startActivity(intent);
            });
        }
        catch (Exception e){
           e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return Utility.articleModelArrayList.size();
    }
}
