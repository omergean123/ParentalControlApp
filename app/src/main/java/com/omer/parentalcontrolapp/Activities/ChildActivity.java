package com.omer.parentalcontrolapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.omer.parentalcontrolapp.Firebase.DataManager;
import com.omer.parentalcontrolapp.Objects.User;
import com.omer.parentalcontrolapp.R;

public class ChildActivity extends AppCompatActivity {
    private Button CLS_BTN_AddHomeWork , CLS_BTN_AddMeals , CLS_BTN_WatchEvents ,CLS_BTN_LogOut ;
    private final DataManager dataManager = DataManager.getInstance();
//    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.child_landing_screen);
            findViews();
            initButtons();

        }

    private void initButtons() {
        initAddHomeWorkButton();
        initAddMealsButton();
        initWatchEventsButton();
        initLogout();
    }

    private void initLogout() {
        CLS_BTN_LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.setCurrentUser(null);
                startActivity(new Intent(ChildActivity.this, MainActivity.class));
            }
        });
    }

    private void initWatchEventsButton() {
        CLS_BTN_WatchEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChildActivity.this, showEventsActivity.class));
            }
        });
    }

    private void initAddMealsButton() {
        CLS_BTN_AddMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChildActivity.this, AddMealsActivity.class));
            }
        });
    }

    private void initAddHomeWorkButton() {
        CLS_BTN_AddHomeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChildActivity.this, AddHomeWorkActivity.class));
            }
        });
    }


    private void findViews() {
        CLS_BTN_AddHomeWork = findViewById(R.id.CLS_BTN_AddHomeWork);
        CLS_BTN_AddMeals = findViewById(R.id.CLS_BTN_AddMeals);
        CLS_BTN_WatchEvents = findViewById(R.id.CLS_BTN_WatchEvents);
        CLS_BTN_LogOut = findViewById(R.id.CLS_BTN_LogOut);
        }
}