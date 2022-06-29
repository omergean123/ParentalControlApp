package com.omer.parentalcontrolapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.omer.parentalcontrolapp.Adapter.recyclerAdapter;
import com.omer.parentalcontrolapp.Firebase.DataManager;
import com.omer.parentalcontrolapp.Objects.User;
import com.omer.parentalcontrolapp.R;

import java.util.ArrayList;

public class myChildrenActivity extends AppCompatActivity {
    private RecyclerView myChildrenRV;
    private final DataManager dataManager = DataManager.getInstance();
    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
    private recyclerAdapter myAdapter;
    private ArrayList<String> usersList;
    private recyclerAdapter.ClickListener cl;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.children_list);
        initView();

        initAdapter();
        initRecycleView();
        loadUserFromDataBase();


    }

    private void initRecycleView() {
        usersList = new ArrayList<String>();
        myAdapter = new recyclerAdapter(usersList ,cl);
        myChildrenRV.setHasFixedSize(true);
        myChildrenRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myChildrenRV.setAdapter(myAdapter);


    }
    private void initAdapter() {
        myAdapter = new recyclerAdapter( usersList, new recyclerAdapter.ClickListener() {
            @Override
            public void clicked(View v, int position) {
                Intent intent = new Intent(getApplicationContext(),forEachChildActivity.class);
                intent.putExtra("phoneNumber",usersList.get(position));
                startActivity(intent);
            }
        });
    }
    private void loadUserFromDataBase() {
        DatabaseReference myRef = realtimeDB.getReference("Users").child(dataManager.getCurrentUser().getPhoneNumber()).child("Children");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
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
        myChildrenRV = findViewById(R.id.myChildrenList);
    }
}
