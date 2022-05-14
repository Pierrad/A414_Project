package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.health.SystemHealthManager;

import com.example.project.user.User;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (hasAccount()) {
            User.getInstance(this);
            Intent intent = new Intent(this, DashboardActivity.class);
            this.startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);
        }
    }

    public boolean hasAccount() {
        SharedPreferences sh = getSharedPreferences("PersonalInfo", MODE_PRIVATE);
        return sh.contains("pseudo") && sh.contains("avatar");
    }
}