package com.omer.parentalcontrolapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.omer.parentalcontrolapp.Adapter.recyclerMyChildren;
import com.omer.parentalcontrolapp.Firebase.DataManager;
import com.omer.parentalcontrolapp.R;

import java.util.ArrayList;

public class myChildrenActivity extends AppCompatActivity {
    private RecyclerView myChildrenRV;
    private final DataManager dataManager = DataManager.getInstance();
    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
    private recyclerMyChildren myAdapter;
    private ArrayList<String> usersList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_lists);
        initView();
        usersList = new ArrayList<String>();
        loadUserFromDataBase();
        initRecycleView();
    }

    private void initRecycleView() {
        myChildrenRV.setHasFixedSize(true);
        myChildrenRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myAdapter = new recyclerMyChildren( usersList, new recyclerMyChildren.ClickListener() {
            @Override
            public void clicked(View v, int position) {
                dataManager.setChildPhone(usersList.get(position));
                Intent intent = new Intent(getApplicationContext(),forEachChildActivity.class);
                startActivity(intent);
            }
        });

        myChildrenRV.setAdapter(myAdapter);
    }
    private void loadUserFromDataBase() {
        DatabaseReference myRef = realtimeDB.getReference("Users").child(dataManager.getCurrentUser().getPhoneNumber()).child("Children");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    String phoneNumber = ds.getValue(String.class);
                    usersList.add(phoneNumber);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(myChildrenActivity.this, "Failed!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void initView() {
        myChildrenRV = findViewById(R.id.myList);
    }
}
