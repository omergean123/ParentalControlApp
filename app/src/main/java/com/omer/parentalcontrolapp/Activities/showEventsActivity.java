package com.omer.parentalcontrolapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
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
import com.omer.parentalcontrolapp.Objects.Meal;
import com.omer.parentalcontrolapp.Objects.User;
import com.omer.parentalcontrolapp.R;

public class showEventsActivity extends AppCompatActivity {
    private EditText SE_TXT_Details;
    private CalendarView SE_CAL_events;
    private final DataManager dataManager = DataManager.getInstance();
    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_events);
        findViews();
//        initCalendar();
    }

//    private void initCalendar() {
//        SE_CAL_events.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
//                String date = (i1 + 1) + "." + i2 + "." + i;
//                String tempDet = readEventFromDataBase(date);
//            }
//        });
//    }

//    private String readEventFromDataBase(String date) {
//        DatabaseReference myRef = realtimeDB.getReference("Users").child(dataManager.getCurrentUser().getPhoneNumber()).child("Events");
//        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                EventKeeper temp = snapshot.getValue(EventKeeper.class);
//                myUser.setCurrentUser(temp);
//
//                if(temp.getType()==0){
//                    startActivity(new Intent(LoginActivity.this, ParentActivity.class));
//                }
//                else{
//                    startActivity(new Intent(LoginActivity.this, ChildActivity.class));
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(LoginActivity.this, "Failed to read from DB!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    @SuppressLint("WrongViewCast")
    private void findViews() {
        SE_TXT_Details = findViewById(R.id.SE_TXT_Details);
        SE_CAL_events = findViewById(R.id.SE_CAL_events);
    }
}
