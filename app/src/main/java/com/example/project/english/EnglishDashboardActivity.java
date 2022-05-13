package com.example.project.english;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project.R;

import java.util.Objects;

public class EnglishDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.english_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard - Anglais");
    }
}