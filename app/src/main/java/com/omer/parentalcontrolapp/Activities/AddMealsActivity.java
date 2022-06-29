package com.omer.parentalcontrolapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omer.parentalcontrolapp.Firebase.DataManager;
import com.omer.parentalcontrolapp.Objects.Meal;
import com.omer.parentalcontrolapp.R;

public class AddMealsActivity extends AppCompatActivity {
    private EditText AM_EDT_details,AM_EDT_time;
    private Button AM_BTN_addMeal;
    private final DataManager dataManager = DataManager.getInstance();
    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meals);
        findViews();
        initAddButton();
    }

    private void initAddButton() {
        AM_BTN_addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String what = AM_EDT_details.getText().toString();
                String when = AM_EDT_time.getText().toString();
                Meal myMeal = new Meal(what,when);
                storeMealForChild(myMeal);
                Toast.makeText(AddMealsActivity.this, "Home work added!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddMealsActivity.this, ChildActivity.class));
            }
        });
    }
    private void storeMealForChild(Meal myMeal) {
        DatabaseReference myRef = realtimeDB.getReference("Users").child(dataManager.getCurrentUser().getPhoneNumber()).child("Meals").child(myMeal.getMealDetails());
        myRef.child("when").setValue(myMeal.getMealTime());
    }
    private void findViews() {
        AM_EDT_details = findViewById(R.id.AM_EDT_details);
        AM_EDT_time = findViewById(R.id.AM_EDT_time);
        AM_BTN_addMeal = findViewById(R.id.AM_BTN_addMeal);
    }
}
