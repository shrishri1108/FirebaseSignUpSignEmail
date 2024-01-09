 package com.example.firebasesignupsignemail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.view.View;
import android.view.WindowManager;

import com.example.firebasesignupsignemail.databinding.ActivityDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;

 public class Dashboard extends AppCompatActivity {

    private ActivityDashboardBinding dashboardBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardBinding= ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(dashboardBinding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        dashboardBinding.tvName.setText(getIntent().getStringExtra("userEmail"));
        dashboardBinding.tvUserIds.setText(getIntent().getStringExtra("user_Id"));

        dashboardBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(Dashboard.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}