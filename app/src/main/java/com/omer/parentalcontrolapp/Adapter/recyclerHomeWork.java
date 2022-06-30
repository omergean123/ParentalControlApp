package com.omer.parentalcontrolapp.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omer.parentalcontrolapp.Objects.Task;
import com.omer.parentalcontrolapp.R;

import java.util.ArrayList;

public class recyclerHomeWork extends RecyclerView.Adapter<recyclerHomeWork.MyViewHolder> {
    private ArrayList<Task> HomeWorkList = new ArrayList<>();

    public recyclerHomeWork(ArrayList<Task> HomeWorkList) {
        this.HomeWorkList = HomeWorkList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView SH_TXT_SUBJECT, SH_TXT_PAGES;

        public MyViewHolder(final View view) {
            super(view);
            SH_TXT_SUBJECT = view.findViewById(R.id.SH_TXT_KEY);
            SH_TXT_PAGES = view.findViewById(R.id.SH_TXT_VALUE);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_key_value, parent, false);
        return new recyclerHomeWork.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        final MyViewHolder mvh =(MyViewHolder) holder;
        String subject = HomeWorkList.get(position).getSubject();
        String pages = HomeWorkList.get(position).getPages();
        holder.SH_TXT_PAGES.setText(pages);
        holder.SH_TXT_SUBJECT.setText(subject);

    }


    @Override
    public int getItemCount() {
        return HomeWorkList.size();
    }
}