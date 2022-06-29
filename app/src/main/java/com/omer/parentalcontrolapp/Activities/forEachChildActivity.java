package com.omer.parentalcontrolapp.Activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.omer.parentalcontrolapp.R;

public class forEachChildActivity extends AppCompatActivity {
    private Button PLS_BTN_ShowHomeWork,PLS_BTN_ShowMeals,PLS_BTN_AddEvents;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.from_parent_each_child);
        initView();
    }

    private void initView() {
        PLS_BTN_ShowHomeWork = findViewById(R.id.PLS_BTN_ShowHomeWork);
        PLS_BTN_ShowMeals = findViewById(R.id.PLS_BTN_ShowMeals);
        PLS_BTN_AddEvents = findViewById(R.id.PLS_BTN_AddEvents);

    }
}
