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
import com.omer.parentalcontrolapp.R;

public class addChildActivity extends AppCompatActivity {
    private EditText AC_EDT_childPhone;
    private Button AC_BTN_AddChild;
    private final DataManager dataManager = DataManager.getInstance();
    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_child);
        findViews();
        initAddButtons();

    }

    private void initAddButtons() {
        AC_BTN_AddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeChildForParent();
                startActivity(new Intent(addChildActivity.this, ParentActivity.class));
            }
        });
    }

    private void storeChildForParent() {
        String myChildPhone = AC_EDT_childPhone.getText().toString();
        if(AC_EDT_childPhone.getText().toString()!=null){
            DatabaseReference myRef = realtimeDB.getReference("Users").child(dataManager.getCurrentUser().getPhoneNumber()).child("Children");
            myRef.child(myChildPhone).setValue(myChildPhone);
        }
        else {
            Toast.makeText(this, "please enter phone", Toast.LENGTH_SHORT).show();
        }
    }

    private void findViews() {
        AC_EDT_childPhone = findViewById(R.id.AC_EDT_childPhone);
        AC_BTN_AddChild = findViewById(R.id.AC_BTN_AddChild);
    }

}
