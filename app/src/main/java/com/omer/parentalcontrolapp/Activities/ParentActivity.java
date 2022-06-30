package com.omer.parentalcontrolapp.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.omer.parentalcontrolapp.Firebase.DataManager;
import com.omer.parentalcontrolapp.Objects.User;
import com.omer.parentalcontrolapp.R;

public class ParentActivity extends AppCompatActivity {
    private Button PLS_BTN_addChild;
    private Button PLS_BTN_myChildren , PLS_BTN_LogOut;
    private final DataManager dataManager = DataManager.getInstance();
    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_landing_screen);
        findViews();
        initAddChildButton();
        initMyChildren();
        initLogout();
    }

    private void initLogout() {
        PLS_BTN_LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.setCurrentUser(null);
                startActivity(new Intent(ParentActivity.this, MainActivity.class));
            }
        });
    }

    private void initMyChildren() {
        PLS_BTN_myChildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParentActivity.this, myChildrenActivity.class));
            }
        });
    }

    private void initAddChildButton() {
        PLS_BTN_addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParentActivity.this, addChildActivity.class));
            }
        });
    }
    private void findViews() {
        PLS_BTN_addChild = findViewById(R.id.PLS_BTN_addChild);
        PLS_BTN_myChildren = findViewById(R.id.PLS_BTN_myChildren);
        PLS_BTN_LogOut = findViewById(R.id.PLS_BTN_LogOut);

    }
}
