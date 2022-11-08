package com.example.newsclientapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {
    String title, author, publishedAt, description, content, imageURL, url;
    private TextView titleTV,authorTV, publishedAtTV, subDescriptionTV, contentTV;
    private ImageView newsIV;
    private Button readNewsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);
        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");
        publishedAt = getIntent().getStringExtra("publishedAt");
        description = getIntent().getStringExtra("description");
        content = getIntent().getStringExtra("content");
        imageURL = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");
        titleTV = findViewById(R.id.idTVTitle);
        authorTV = findViewById(R.id.idTVAuthor);
        publishedAtTV = findViewById(R.id.idTVPublishedAt);
        subDescriptionTV = findViewById(R.id.idTVSubDescription);
        contentTV = findViewById(R.id.idTVContent);
        newsIV = findViewById(R.id.idIVNews);
        readNewsBtn = findViewById(R.id.idBtnReadNews);
        titleTV.setText(title);
        authorTV.setText("By: " + author);
        publishedAtTV.setText("Date of Published: " + publishedAt);
        subDescriptionTV.setText(description);
        contentTV.setText(content);
        Picasso.get().load(imageURL).into(newsIV);
        readNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }

}
