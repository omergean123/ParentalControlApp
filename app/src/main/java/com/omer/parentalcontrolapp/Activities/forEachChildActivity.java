package com.omer.parentalcontrolapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.omer.parentalcontrolapp.R;

public class forEachChildActivity extends AppCompatActivity {
    private Button PLS_BTN_ShowHomeWork,PLS_BTN_ShowMeals,PLS_BTN_AddEvents;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.from_parent_each_child);
        initView();
//        Bundle exstras = getIntent().getExtras();
//        if(exstras!=null){
//            childPhone = exstras.getString("phoneNumber");
//        }
        initButtons();
    }


    private void initButtons() {
        PLS_BTN_ShowHomeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),showHomeWorkActivity.class);
                startActivity(intent);
            }
        });
        PLS_BTN_ShowMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),showMealsActivity.class);
                startActivity(intent);
            }
        });

        PLS_BTN_AddEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),addEventActivity.class);
                startActivity(intent);

            }
        });
    }

    private void initView() {
        PLS_BTN_ShowHomeWork = findViewById(R.id.PLS_BTN_ShowHomeWork);
        PLS_BTN_ShowMeals = findViewById(R.id.PLS_BTN_ShowMeals);
        PLS_BTN_AddEvents = findViewById(R.id.PLS_BTN_AddEvents);

    }
}
