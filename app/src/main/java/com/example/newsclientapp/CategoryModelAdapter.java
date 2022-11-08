package com.example.newsclientapp;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsclientapp.models.CategoryModel;

import java.util.ArrayList;

public class CategoryModelAdapter extends RecyclerView.Adapter<CategoryModelAdapter.ViewHolder> {

    private ArrayList<CategoryModel> categoryModels;
    private Context context;
    private CategoryClickInterface categoryClickInterface;
    int selectedPosition = 0;

    @NonNull
    @Override
    public CategoryModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryModelAdapter.ViewHolder holder, int position) {
        CategoryModel categoryRVModal = categoryModels.get(position);
        holder.categoryTV.setText(categoryRVModal.getCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position= holder.getAdapterPosition();
                categoryClickInterface.onCategoryClick(position);
                // update position
                selectedPosition=position;
                // notify
                notifyDataSetChanged();
            }
        });
        // check conditions
        if(selectedPosition==position)
        {
            // When current position is equal
            // to selected position
            // set red background color
            holder.categoryRV.setBackgroundColor(Color.RED);
            // set white text color
            holder.categoryTV.setTextColor(Color.WHITE);

        }
        else
        {
            // when current position is different
            // set white background
            holder.categoryRV.setBackgroundColor(Color.WHITE);
            // set black text color
            holder.categoryTV.setTextColor(Color.BLACK);

        }
    }

    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTV;
        private RelativeLayout categoryRV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryRV = itemView.findViewById(R.id.idCVCategory);
            categoryTV = itemView.findViewById(R.id.idTVCategory);

        }
    }
}
