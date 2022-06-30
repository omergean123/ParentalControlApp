package com.omer.parentalcontrolapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.omer.parentalcontrolapp.Firebase.DataManager;
import com.omer.parentalcontrolapp.Objects.Meal;
import com.omer.parentalcontrolapp.Objects.User;
import com.omer.parentalcontrolapp.R;

public class showEventsActivity extends AppCompatActivity {
    private TextView SE_TXT_Details;
    private CalendarView SE_CAL_events;
    private final DataManager dataManager = DataManager.getInstance();
    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
    private String date,details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_events);
        findViews();
        showDetailsOnTextView();

    }

    private void showDetailsOnTextView() {
        SE_CAL_events.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date = i2 + "." +  (i1 + 1) + "." + i;
                DatabaseReference myRef = realtimeDB.getReference("Users").child(dataManager.getCurrentUser().getPhoneNumber()).child("Events");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()){
                            SE_CAL_events.setBackgroundColor(000000);
                            Log.d("pttt 1" , ds.getKey() + " " + ds.getValue() + " " + date);
                            if(ds.getValue().equals(date)){

                                details = ds.getKey();
                                SE_TXT_Details.setText(details);

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    private void findViews() {
        SE_TXT_Details = findViewById(R.id.SE_TXT_Details);
        SE_CAL_events = findViewById(R.id.SE_CAL_events);
    }
}
