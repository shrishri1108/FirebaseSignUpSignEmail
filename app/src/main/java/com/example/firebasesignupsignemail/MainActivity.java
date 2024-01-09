package com.example.firebasesignupsignemail;


import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasesignupsignemail.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());


        // getting instance of FirebaseAuth .
        auth= FirebaseAuth.getInstance();

        activityMainBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityMainBinding.progressbar.setVisibility(View.VISIBLE);
                String email= activityMainBinding.etEmail.getText().toString();
                String password=activityMainBinding.etPwd.getText().toString();

                try {
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "onComplete:  Successfully Logged in. ");
                                        activityMainBinding.progressbar.setVisibility(View.INVISIBLE);
                                        Intent intent= new Intent(MainActivity.this, Dashboard.class);
                                        intent.putExtra("userEmail", "Email: "+task.getResult().getUser().getEmail());
                                        intent.putExtra("user_Id", "UID: "+task.getResult().getUser().getUid());
                                        Toast.makeText(MainActivity.this, " Registered Successfully", Toast.LENGTH_SHORT).show();
                                        activityMainBinding.etEmail.setText("");
                                        activityMainBinding.etPwd.setText("");
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        activityMainBinding.progressbar.setVisibility(View.INVISIBLE);
                                        activityMainBinding.etEmail.setText("");
                                        activityMainBinding.etPwd.setText("");
                                        Toast.makeText(MainActivity.this, "Failed to be as  Registered" + task.getResult(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, " Kindly enter correct email and minimum 6 length of password. Check Your Internet Connection  Exception occuring is "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        activityMainBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in ( non-null) and update UI accordingly.

        FirebaseUser currentUser= auth.getCurrentUser();
        
    }
}