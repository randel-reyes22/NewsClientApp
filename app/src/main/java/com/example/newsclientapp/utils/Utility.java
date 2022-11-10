package com.example.newsclientapp.utils;

import com.example.newsclientapp.models.ArticleModel;
import com.example.newsclientapp.models.CategoryModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Utility {
    //URLs
    private static final String categoriesURI = "https://newsapi.org/v2/top-headlines?country=ph&category=";
    private static final String apiKey = "&apiKey=c5dc361f9ba242cbb050af03e53b022d";

    public static final String URL = "https://newsapi.org/";
    public static final String ALL_URL = "https://newsapi.org/v2/top-headlines?country=ph" + apiKey;

    //image empty URL
    public static final String emptyImageLink = "https://taawon.com/images_default/default.jpg";

    //lists
    public static ArrayList<ArticleModel> articleModelArrayList = new ArrayList<>();
    public static final ArrayList<CategoryModel> categoryModelArrayList = new ArrayList<>(
            Arrays.asList(new CategoryModel("All"), new CategoryModel("Business"), new CategoryModel("Technology"),
                    new CategoryModel("Entertainment"), new CategoryModel("Sports"), new CategoryModel("Science"),
                    new CategoryModel("Health")));

    public static String categoryURI(String category){
        return categoriesURI + category +  apiKey;
    }
}
