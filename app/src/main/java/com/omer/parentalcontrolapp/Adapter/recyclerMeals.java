package com.omer.parentalcontrolapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omer.parentalcontrolapp.Objects.Meal;
import com.omer.parentalcontrolapp.Objects.Task;
import com.omer.parentalcontrolapp.R;

import java.util.ArrayList;

public class recyclerMeals extends RecyclerView.Adapter<recyclerMeals.MyViewHolder> {
    private ArrayList<Meal> mealsList = new ArrayList<>();

    public recyclerMeals(ArrayList<Meal> mealsList) {
        this.mealsList = mealsList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView SH_TXT_KEY, SH_TXT_VALUE;

        public MyViewHolder(final View view) {
            super(view);
            SH_TXT_KEY = view.findViewById(R.id.SH_TXT_KEY);
            SH_TXT_VALUE = view.findViewById(R.id.SH_TXT_VALUE);

        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_key_value, parent, false);
        return new recyclerMeals.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String food = mealsList.get(position).getMealDetails();
        String time = mealsList.get(position).getMealTime();
        holder.SH_TXT_KEY.setText(food);
        holder.SH_TXT_VALUE.setText(time);

    }


    @Override
    public int getItemCount() {
        return mealsList.size();
    }
}
