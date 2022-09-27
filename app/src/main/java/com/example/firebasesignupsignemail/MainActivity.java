package com.example.firebasesignupsignemail;


import static android.content.ContentValues.TAG;

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

                auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Log.d(TAG, "onComplete:  Successfully Logged in. ");
                                    activityMainBinding.progressbar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(MainActivity.this, " Registered Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    activityMainBinding.progressbar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(MainActivity.this, "Failed to be as  Registered"+task.getResult(), Toast.LENGTH_SHORT).show();
                                }
                                activityMainBinding.etEmail.setText("");
                                activityMainBinding.etPwd.setText("");
                            }
                        });

            }
        });

    }
}