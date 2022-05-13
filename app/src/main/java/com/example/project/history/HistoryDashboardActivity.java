package com.example.project.history;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project.R;

import java.util.Objects;

public class HistoryDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard - Histoire");
    }
}