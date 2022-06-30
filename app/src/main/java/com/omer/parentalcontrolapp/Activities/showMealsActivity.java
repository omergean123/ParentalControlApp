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
import com.omer.parentalcontrolapp.Adapter.recyclerHomeWork;
import com.omer.parentalcontrolapp.Adapter.recyclerMeals;
import com.omer.parentalcontrolapp.Firebase.DataManager;
import com.omer.parentalcontrolapp.Objects.Meal;
import com.omer.parentalcontrolapp.Objects.Task;
import com.omer.parentalcontrolapp.R;

import java.util.ArrayList;

public class showMealsActivity extends AppCompatActivity {
    private ArrayList<Meal> mealsList;
//    String childPhone;
    private RecyclerView myChildrenRV;
    private final DataManager dataManager = DataManager.getInstance();
    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
    private recyclerMeals myAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_lists);
        initView();
//        Bundle exstras = getIntent().getExtras();
//        if(exstras!=null){
//            childPhone = exstras.getString("phoneNumber");
//        }
        mealsList = new ArrayList<Meal>();
        loadHomeWorkFromDataBase();
    }

    private void loadHomeWorkFromDataBase() {

        DatabaseReference myRef = realtimeDB.getReference("Users").child(dataManager.getChildPhone());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.child("Meals").getChildren()){
                    String food = ds.getKey();
                    String time = ds.getValue(String.class);
                    Meal myMeal = new Meal(food,time);
                    mealsList.add(myMeal);
                }

                myChildrenRV.setHasFixedSize(true);
                myChildrenRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                myAdapter = new recyclerMeals(mealsList);
                myChildrenRV.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(showMealsActivity.this, "Failed!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initView() {
        myChildrenRV = findViewById(R.id.myList);
    }

}
