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
import com.omer.parentalcontrolapp.Objects.Task;
import com.omer.parentalcontrolapp.R;

public class AddHomeWorkActivity extends AppCompatActivity {
    private EditText AHW_EDT_class,AHW_EDT_pages;
    private Button AHW_EDT_AddHomeWork;
    private final DataManager dataManager = DataManager.getInstance();
    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_homework);
        findViews();
        initAddButton();
    }

    private void initAddButton() {
        AHW_EDT_AddHomeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String theClass = AHW_EDT_class.getText().toString();
                String thePages = AHW_EDT_pages.getText().toString();
                Task myTask = new Task(theClass,thePages);
                storeTaskForChild(myTask);
                Toast.makeText(AddHomeWorkActivity.this, "Home work added!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddHomeWorkActivity.this, ChildActivity.class));
            }
        });

    }

    private void storeTaskForChild(Task myTask) {
        DatabaseReference myRef = realtimeDB.getReference("Users").child(dataManager.getCurrentUser().getPhoneNumber()).child("Tasks");
        myRef.child(myTask.getSubject()).setValue(myTask.getPages());
    }

    private void findViews() {
        AHW_EDT_class = findViewById(R.id.AHW_EDT_class);
        AHW_EDT_pages = findViewById(R.id.AHW_EDT_pages);
        AHW_EDT_AddHomeWork = findViewById(R.id.AHW_EDT_AddHomeWork);
    }
}