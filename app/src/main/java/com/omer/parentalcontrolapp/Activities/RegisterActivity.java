package com.omer.parentalcontrolapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omer.parentalcontrolapp.Firebase.DataManager;
import com.omer.parentalcontrolapp.Objects.Child;
import com.omer.parentalcontrolapp.Objects.Parent;
import com.omer.parentalcontrolapp.Objects.User;
import com.omer.parentalcontrolapp.R;

public class RegisterActivity extends AppCompatActivity {
    private Button Register_BTN_Register,Register_BTN_BackToLogin;
    private EditText Register_EDT_UserName,Register_EDT_Password , Register_EDT_PhoneNumber;
    private RadioButton Register_isChild , Register_isParent;
    private RadioGroup Register_Radio_Group;
    private final int PARENT = 0;
    private final int CHILD = 1;
    private FirebaseAuth auth;
    private final DataManager dataManager = DataManager.getInstance();
    private final FirebaseDatabase realtimeDB = dataManager.getRealTimeDB();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        auth = FirebaseAuth.getInstance();
        findViews();
        InitRegisterButton();
        InitBackButton();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dataManager.getFirebaseAuth().signOut();
    }

    private void InitRegisterButton() {

        Register_BTN_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = Register_EDT_PhoneNumber.getText().toString();
                String password = Register_EDT_Password.getText().toString();
                String userName = Register_EDT_UserName.getText().toString();
                User tempUser = new User(userName , phoneNumber, password);
               // if(check_validation()){
                    if(Register_isParent.isChecked()){
                        tempUser.setType(0);
                        storeUserInDB(tempUser);
                        dataManager.setCurrentUser(tempUser);
//                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        replaceToParentActivity();
                    }
                    else{
                        tempUser.setType(1);
                        storeUserInDB(tempUser);
                        dataManager.setCurrentUser(tempUser);
//                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        replaceToChildActivity();
                    }
                }
        });
    }
    private void storeUserInDB(User userToStore) {
        DatabaseReference myRef = realtimeDB.getReference("Users").child(userToStore.getPhoneNumber());
        myRef.child("uid").setValue(userToStore.getUid());
        myRef.child("PhoneNumber").setValue(userToStore.getPhoneNumber());
        myRef.child("password").setValue(userToStore.getPassword());
        myRef.child("type").setValue(userToStore.getType());
//        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();

    }
    private void replaceToChildActivity() {
        Intent intent = new Intent(this, ChildActivity.class);
        startActivity(intent);
    }

    private void replaceToParentActivity() {
        Intent intent = new Intent(this, ParentActivity.class);
        startActivity(intent);
    }

    private void InitBackButton() {
        Register_BTN_BackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceActivity();
            }
        });
    }

    private void replaceActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private boolean check_validation() {
        if(TextUtils.isEmpty(Register_EDT_UserName.getText().toString())){
            Toast.makeText(this, "Please enter user name !", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(TextUtils.isEmpty(Register_EDT_Password.getText().toString())){
            Toast.makeText(this, "Please enter password !", Toast.LENGTH_SHORT).show();
            return false;

        }
        else if(TextUtils.isEmpty(Register_EDT_PhoneNumber.getText().toString())){
            Toast.makeText(this, "Please enter phoneNumber !", Toast.LENGTH_SHORT).show();
            return false;

        }
        else if(Register_isChild.isSelected() || Register_isParent.isSelected()){
            Toast.makeText(this, "Please select Parent or child !", Toast.LENGTH_SHORT).show();
            return false;

        }
//        else if(!isValidEmail()){
//            Toast.makeText(this, "Invalid email !", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        else {
//            Toast.makeText(this, "good", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public void findViews() {
        Register_BTN_Register = findViewById(R.id.Register_BTN_Register);
        Register_BTN_BackToLogin = findViewById(R.id.Register_BTN_BackToLogin);
        Register_EDT_UserName = findViewById(R.id.Register_EDT_userName);
        Register_EDT_Password = findViewById(R.id.Register_EDT_password);
        Register_EDT_PhoneNumber = findViewById(R.id.Register_EDT_PhoneNumber);
        Register_isChild = findViewById(R.id.radio_Child);
        Register_isParent = findViewById(R.id.radio_Parent);
        Register_Radio_Group = findViewById(R.id.Register_Radio_Group);
    }
}
