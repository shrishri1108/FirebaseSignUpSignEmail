package com.example.firebasesignupsignemail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.firebasesignupsignemail.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();

        loginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBinding.progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(loginBinding.etLoginEmail.getText().toString(), loginBinding.etPwd.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    loginBinding.progressBar.setVisibility(View.INVISIBLE);
                                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                                    intent.putExtra("userEmail", "Email: " + loginBinding.etLoginEmail.getText().toString());
                                    intent.putExtra("user_Id", "UID: " + loginBinding.etLoginEmail.getText().toString());
                                    startActivity(intent);
                                    finish();
                                } else {
                                    loginBinding.progressBar.setVisibility(View.INVISIBLE);
                                    loginBinding.etLoginEmail.setText("");
                                    loginBinding.etPwd.setText("");
                                    Toast.makeText(getApplicationContext(), " Invalid email/password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        loginBinding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}