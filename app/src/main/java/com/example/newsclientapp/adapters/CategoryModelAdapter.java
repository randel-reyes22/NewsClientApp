package com.example.newsclientapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsclientapp.R;
import com.example.newsclientapp.models.CategoryModel;
import com.example.newsclientapp.utils.Utility;

public class CategoryModelAdapter extends RecyclerView.Adapter<CategoryModelAdapter.ViewHolder> {
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    //current position of category selected
    private int selectedPosition = 0;

    public CategoryModelAdapter(Context context, CategoryClickInterface categoryClickInterface) {
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_items,parent,false);
        return new CategoryModelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryModelAdapter.ViewHolder holder, int position) {
        CategoryModel categoryRVModal = Utility.categoryModelArrayList.get(position);
        holder.categoryTV.setText(categoryRVModal.getCategory());
        holder.itemView.setOnClickListener(view -> {
            int position1 = holder.getAdapterPosition();
            categoryClickInterface.onCategoryClick(position1);
            selectedPosition= position1;
            notifyDataSetChanged();
        });

        if(selectedPosition == position) {
            holder.categoryRV.setBackgroundColor(Color.WHITE);
            holder.categoryTV.setTextColor(Color.BLACK);
        } else {
            holder.categoryRV.setBackgroundColor(0x121212);
            holder.categoryTV.setTextColor(Color.WHITE);
        }
    }

    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }

    @Override
    public int getItemCount() {
        return Utility.categoryModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTV;
        private RelativeLayout categoryRV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryRV = itemView.findViewById(R.id.idCategory);
            categoryTV = itemView.findViewById(R.id.idTVCategory);
        }
    }
}