package com.example.project.maths;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project.R;

import java.util.Objects;

public class MathsDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard - Maths");
    }
}