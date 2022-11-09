package com.example.newsclientapp.models;

import com.example.newsclientapp.utils.Utility;

public class ArticleModel {
    private String title;
    private String author;
    private String publishedAt;
    private String description;
    private String urlToImage;
    private String url;
    private String content;

    public ArticleModel(String title, String author, String publishedAt, String description, String urlToImage, String url, String content) {
        this.title = title;
        this.author = author;
        this.publishedAt = publishedAt;
        this.description = description;
        this.urlToImage = urlToImage;
        this.url = url;
        this.content = content;
    }

    public String getTitle() {
        return title == null || title.isEmpty() ? "No title" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author == null || author.isEmpty() ? "No author" : author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedAt() {
        return publishedAt.substring(0,10);
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getDescription() {
        return description == null || description.isEmpty() ? "No description" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content == null || content.isEmpty() ? "No content" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
