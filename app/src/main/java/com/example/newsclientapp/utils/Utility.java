package com.example.newsclientapp.utils;

import com.example.newsclientapp.models.ArticleModel;
import com.example.newsclientapp.models.CategoryModel;

import java.util.ArrayList;

public class Utility {
    //URLs
    private static final String categoriesURI = "https://newsapi.org/v2/top-headlines?country=ph&category=";
    private static final String apiKey = "&apiKey=c5dc361f9ba242cbb050af03e53b022d";
    public static final String URL = "https://newsapi.org/";
    public static final String ALL_URL = "https://newsapi.org/v2/top-headlines?country=ph" + apiKey;

    //image empty URL
    public static final String emptyImageLink = "https://taawon.com/images_default/default.jpg";

    //lists
    public static ArrayList<ArticleModel> articleModelArrayList;
    public static ArrayList<CategoryModel> categoryModelArrayList;

    //categories that will be displayed at home
    public static void fetchCategories(){
        categoryModelArrayList.add(new CategoryModel("All"));
        categoryModelArrayList.add(new CategoryModel("Business"));
        categoryModelArrayList.add(new CategoryModel("Technology"));
        categoryModelArrayList.add(new CategoryModel("Entertainment"));
        categoryModelArrayList.add(new CategoryModel("Sports"));
        categoryModelArrayList.add(new CategoryModel("Science"));
        categoryModelArrayList.add(new CategoryModel("Health"));
    }

    public static String categoryURI(String category){
        return categoriesURI + category +  apiKey;
    }
}
