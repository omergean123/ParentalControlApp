package com.omer.parentalcontrolapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omer.parentalcontrolapp.R;

import java.util.ArrayList;

public class recyclerMyChildren extends RecyclerView.Adapter<recyclerMyChildren.MyViewHolder>{
    private ArrayList<String> usersList;
    private ClickListener clickListener;
    public interface ClickListener {
        void clicked(View v, int position);
    }

    public recyclerMyChildren(ArrayList<String> userList, ClickListener cl){
        this.clickListener = cl;
        this.usersList = userList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Button phoneNumber;
        public MyViewHolder(final View view) {
            super(view);
            phoneNumber = view.findViewById(R.id.LI_BTN_childName);
            phoneNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.clicked(view, getAdapterPosition());
                    }
                }
            });
        }

//        @Override
//        public void onClick(View view) {            view.setOnClickListener(this);
//            clickListener.clicked(view,getAdapterPosition());
//        }
    }
    @NonNull
    @Override
    public recyclerMyChildren.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerMyChildren.MyViewHolder holder, int position) {
        String phoneNum = usersList.get(position);
        holder.phoneNumber.setText(phoneNum);

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
