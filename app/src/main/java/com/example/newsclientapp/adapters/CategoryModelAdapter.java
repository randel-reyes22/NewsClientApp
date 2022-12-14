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
    private int selectedCategoryIndex = 0;

    public CategoryModelAdapter(Context context, CategoryClickInterface categoryClickInterface) {
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTV;
        private View lineDivider;
        private RelativeLayout categoryRL;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryRL = itemView.findViewById(R.id.idCategory);
            categoryTV = itemView.findViewById(R.id.idTVCategory);
            lineDivider = itemView.findViewById(R.id.idDividerCategory);
        }
    }

    @Override
    public int getItemCount() {
        return Utility.categoryModelArrayList.size();
    }

    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }

    @NonNull
    @Override
    public CategoryModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_items,parent,false);
        return new CategoryModelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryModelAdapter.ViewHolder holder, int position) {
        //get the index of the selected category
        CategoryModel categoryRVModal = Utility.categoryModelArrayList.get(position);

        holder.categoryTV.setText(categoryRVModal.getCategory());
        holder.itemView.setOnClickListener(view -> {
            int index = holder.getAdapterPosition();
            categoryClickInterface.onCategoryClick(index);
            selectedCategoryIndex = index;
            notifyDataSetChanged();
        });

        if(selectedCategoryIndex == position) {
            holder.categoryTV.setTextColor(Color.parseColor("#BB86FC"));
            holder.lineDivider.setVisibility(View.VISIBLE);
            holder.lineDivider.setBackgroundColor(Color.parseColor("#BB86FC"));
        } else {
            holder.categoryTV.setTextColor(Color.parseColor("#B3ADAD"));
            holder.lineDivider.setVisibility(View.GONE);
        }
    }
}
