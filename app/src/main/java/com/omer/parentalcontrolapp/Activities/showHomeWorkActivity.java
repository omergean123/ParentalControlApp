package com.omer.parentalcontrolapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.omer.parentalcontrolapp.Adapter.recyclerHomeWork;
import com.omer.parentalcontrolapp.Firebase.DataManager;
import com.omer.parentalcontrolapp.Objects.Task;
import com.omer.parentalcontrolapp.R;

import java.util.ArrayList;

public class showHomeWorkActivity extends AppCompatActivity {
    private ArrayList<Task> taskList;
//    String childPhone;
    private RecyclerView myChildrenRV;
    private final DataManager dataManager = DataManager.getInstance();
    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
    private recyclerHomeWork myAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_lists);
        initView();
//        Bundle exstras = getIntent().getExtras();
//        if(exstras!=null){
//            childPhone = exstras.getString("phoneNumber");
//        }
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
        DatabaseReference myRef = realtimeDB.getReference("Users").child(dataManager.getChildPhone());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.child("Tasks").getChildren()){
                    String sub = ds.getKey();
                    String page = ds.getValue(String.class);
                    Task myTask = new Task(sub,page);
                    taskList.add(myTask);
                }

                myChildrenRV.setHasFixedSize(true);
                myChildrenRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                myAdapter = new recyclerHomeWork(taskList, new recyclerHomeWork.ClickListener() {
                    @Override
                    public void longClicked(View v, int position) {
                        delete(position, taskList.get(position).getSubject());

                    }
                });
                myChildrenRV.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(showHomeWorkActivity.this, "Failed!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void delete(int position, String subject) {
        DatabaseReference refDel = realtimeDB.getReference("Users").child(dataManager.getChildPhone()).child("Tasks");
        Query q = refDel.child(subject);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().removeValue();
                taskList.remove(position);
                myAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView() {
        myChildrenRV = findViewById(R.id.myList);
    }
}
