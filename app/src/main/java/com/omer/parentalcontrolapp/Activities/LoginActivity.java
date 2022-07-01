package com.omer.parentalcontrolapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.omer.parentalcontrolapp.Firebase.DataManager;
import com.omer.parentalcontrolapp.Objects.User;
import com.omer.parentalcontrolapp.R;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity  {
    private Button Login_BTN_Login,Login_BTN_Regiser;
    private EditText Login_EDT_PhoneNumber,Login_EDT_Password;
    FirebaseDatabase realtimeDB = FirebaseDatabase.getInstance();
    DataManager myUser = DataManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findViews();

        InitRegisterButton();
        initLoginButton();
//        FirebaseDatabase db = FirebaseDatabase.getInstance();

    }

    private void initLoginButton() {
        Login_BTN_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                loadUserFromDB();

                loginUser();
            }
        });
    }

    private void InitRegisterButton() {
        Login_BTN_Regiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceActivity();
            }
        });
    }
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            loadUserFromDB();
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }
    private void loginUser() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.PhoneBuilder().build());
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);

    }

    private void loadUserFromDB() {
        DatabaseReference myRef = realtimeDB.getReference("Users").child(Login_EDT_PhoneNumber.getText().toString());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User temp = snapshot.getValue(User.class);
                myUser.setCurrentUser(temp);

                if(temp.getType()==0){
                    startActivity(new Intent(LoginActivity.this, ParentActivity.class));
                }
                else{
                    startActivity(new Intent(LoginActivity.this, ChildActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginActivity.this, "Failed to read from DB!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void replaceActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void findViews() {
        Login_BTN_Login = findViewById(R.id.Login_BTN_Login);
        Login_BTN_Regiser = findViewById(R.id.Login_BTN_Register);
        Login_EDT_Password = findViewById(R.id.LOGIN_EDT_password);
        Login_EDT_PhoneNumber = findViewById(R.id.Login_EDT_PhoneNumber);
    }
}
