package com.example.project.french;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project.R;

import java.util.Objects;

public class FrenchDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.french_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard - Français");
    }
}