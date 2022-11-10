package com.example.newsclientapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsclientapp.utils.Utility;
import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);

        //get data
        String imageURL = getIntent().getStringExtra("image");
        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String publishedAt = getIntent().getStringExtra("publishedAt");
        String description = getIntent().getStringExtra("description");
        String content = getIntent().getStringExtra("content");
        String url = getIntent().getStringExtra("url");

        //widgets
        ImageView newsIV = findViewById(R.id.idIVNews);
        TextView titleTV = findViewById(R.id.idTVTitle);
        TextView authorTV = findViewById(R.id.idTVAuthor);
        TextView subDescriptionTV = findViewById(R.id.idTVSubDescription);
        TextView contentTV = findViewById(R.id.idTVContent);
        Button readNewsBtn = findViewById(R.id.idBtnReadNews);

        //load image
        Picasso.get().load(imageURL).into(newsIV);

        //set text and url image
        //check image if null
        titleTV.setText(title);
        authorTV.setText(author + ", " + publishedAt);
        subDescriptionTV.setText(description);
        contentTV.setText(content);

        //show the full story
        readNewsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
    }
}
