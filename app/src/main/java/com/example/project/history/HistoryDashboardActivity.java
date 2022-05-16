package com.example.project.history;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.project.R;
import com.example.project.history.activities.QuizzActivity;

import java.util.Objects;

public class HistoryDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard - Histoire");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.history)));
    }

    public void startQuizz(View v) {
        Intent intent = new Intent(this, QuizzActivity.class);
        this.startActivity(intent);
    }

}