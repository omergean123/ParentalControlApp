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
    private ClickListener clickListener;
    public interface ClickListener {
        void clicked(View v, int position);
    }
    public recyclerHomeWork(ArrayList<Task> HomeWorkList) {
        Log.d("pttt","2 "+ HomeWorkList.toString());
        this.HomeWorkList = HomeWorkList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements ClickListener{
        private TextView SH_TXT_SUBJECT, SH_TXT_PAGES;

        public MyViewHolder(final View view) {
            super(view);
            SH_TXT_SUBJECT = view.findViewById(R.id.SH_TXT_SUBJECT);
            SH_TXT_PAGES = view.findViewById(R.id.SH_TXT_PAGES);

        }

        @Override
        public void clicked(View v, int position) {
            clickListener.clicked(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
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