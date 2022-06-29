package com.omer.parentalcontrolapp.Activities;

import android.os.Bundle;
import android.util.Log;
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
import com.omer.parentalcontrolapp.Adapter.recyclerAdapter;
import com.omer.parentalcontrolapp.Adapter.recyclerHomeWork;
import com.omer.parentalcontrolapp.Firebase.DataManager;
import com.omer.parentalcontrolapp.Objects.Task;
import com.omer.parentalcontrolapp.R;

import java.util.ArrayList;

public class showHomeWorkActivity extends AppCompatActivity {
    private ArrayList<Task> taskList;
    private String subject , pages;
    String childPhone;
    private RecyclerView myChildrenRV;
    private final DataManager dataManager = DataManager.getInstance();
    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
    private recyclerHomeWork myAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_lists);
        initView();
        Bundle exstras = getIntent().getExtras();
        if(exstras!=null){
            childPhone = exstras.getString("phoneNumber");
        }
        taskList = new ArrayList<Task>();
        loadHomeWorkFromDataBase();
//        initRecycleView();
    }
//    private void initRecycleView() {
//
//
//
//    }
    private void loadHomeWorkFromDataBase() {
        DatabaseReference myRef = realtimeDB.getReference("Users").child(childPhone);
        Log.d("pttt" , childPhone);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.child("Tasks").getChildren()){
                    Log.d("pttt" , childPhone);
                    String sub = ds.getValue(String.class);
                    String page = ds.getValue(String.class);
                    Log.d("pttt" , sub);
                    Task myTask = new Task(sub,page);
                    Log.d("pttt" ,"1 "+ myTask.toString());
                    taskList.add(myTask);
                    Log.d("pttt" ,"4 "+ myTask.toString());
                }

                myChildrenRV.setHasFixedSize(true);
                myChildrenRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                Log.d("pttt","3 "+ taskList.toString());
                myAdapter = new recyclerHomeWork(taskList);
                myChildrenRV.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(showHomeWorkActivity.this, "Failed!", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void initView() {
        myChildrenRV = findViewById(R.id.myList);
    }
}
