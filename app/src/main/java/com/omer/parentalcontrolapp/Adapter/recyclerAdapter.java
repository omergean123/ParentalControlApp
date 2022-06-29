package com.omer.parentalcontrolapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.omer.parentalcontrolapp.Activities.ChildActivity;
import com.omer.parentalcontrolapp.Activities.LoginActivity;
import com.omer.parentalcontrolapp.Activities.MainActivity;
import com.omer.parentalcontrolapp.Activities.ParentActivity;
import com.omer.parentalcontrolapp.Firebase.DataManager;
import com.omer.parentalcontrolapp.Objects.Task;
import com.omer.parentalcontrolapp.Objects.User;
import com.omer.parentalcontrolapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{
    private ArrayList<String> usersList;
    private ClickListener clickListener;
    public interface ClickListener {
        void clicked(View v, int position);
    }

    public recyclerAdapter(ArrayList<String> userList,ClickListener cl){
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
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String phoneNum = usersList.get(position);
        holder.phoneNumber.setText(phoneNum);

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
//    public String getPhoneNum(int position) {
//        return usersList.get(position);
//    }
}
