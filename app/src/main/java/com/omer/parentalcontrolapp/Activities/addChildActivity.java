package com.omer.parentalcontrolapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
//        int myType = dataManager.getCurrentUser().getType();
        DatabaseReference bigRef = realtimeDB.getReference("Users");
        bigRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int found = 0;
                for (DataSnapshot ds : snapshot.getChildren()){
                    if(ds.getKey().equals(myChildPhone)){
                        found = 1;
                        if(AC_EDT_childPhone.getText().toString()!=null){
                            DatabaseReference myRef = realtimeDB.getReference("Users").child(dataManager.getCurrentUser().getPhoneNumber()).child("Children");
                            myRef.child(myChildPhone).setValue(myChildPhone);
                            Toast.makeText(addChildActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(addChildActivity.this, "Please enter phone number", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                if(found == 0){
                    Toast.makeText(addChildActivity.this, "No such user", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void findViews() {
        AC_EDT_childPhone = findViewById(R.id.AC_EDT_childPhone);
        AC_BTN_AddChild = findViewById(R.id.AC_BTN_AddChild);
    }

}
