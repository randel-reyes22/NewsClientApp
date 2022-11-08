package com.example.newsclientapp.models;

import java.util.ArrayList;

public class NewsModel {
    private int totalResults;
    private String status;
    private ArrayList<ArticleModel> articles;

    public NewsModel(int totalResults, String status, ArrayList<ArticleModel> articles) {
        this.totalResults = totalResults;
        this.status = status;
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ArticleModel> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ArticleModel> articles) {
        this.articles = articles;
    }
}
