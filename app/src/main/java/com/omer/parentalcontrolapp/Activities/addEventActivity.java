package com.omer.parentalcontrolapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omer.parentalcontrolapp.Firebase.DataManager;
import com.omer.parentalcontrolapp.R;

public class addEventActivity extends AppCompatActivity {
        private CalendarView AE_CAL_Events;
        private EditText AE_EDT_eventDetails;
        private Button AE_BTN_addEvent;
        private final DataManager dataManager = DataManager.getInstance();
        private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
        private String  date;
        //childPhone ,
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);
        initView();
        initCalPressed();
        initAddButton();
    }

    private void initCalPressed() {
        AE_CAL_Events.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date = i2 + "." +  (i1 + 1) + "." + i; }
        });
    }

    private void initAddButton() {
        DatabaseReference myRef = realtimeDB.getReference("Users").child(dataManager.getChildPhone()).child("Events");
        AE_BTN_addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               myRef.child(AE_EDT_eventDetails.getText().toString()).setValue(date);
               Toast.makeText(addEventActivity.this, "Event added!", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(addEventActivity.this, forEachChildActivity.class));
            }
        });
    }

    private void initView() {
        AE_CAL_Events = findViewById(R.id.AE_CAL_Events);
        AE_EDT_eventDetails = findViewById(R.id.AE_EDT_eventDetails);
        AE_BTN_addEvent = findViewById(R.id.AE_BTN_addEvent);

    }
}
