package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project.english.EnglishDashboardActivity;
import com.example.project.french.FrenchDashboardActivity;
import com.example.project.history.HistoryDashboardActivity;
import com.example.project.maths.MathsDashboardActivity;
import com.example.project.user.User;

import java.util.Objects;

public class DashboardActivity extends AppCompatActivity {
    private User user = User.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard - " + user.getPseudo());
    }

    public void redirectToEnglish(View v) {
        Intent intent = new Intent(this, EnglishDashboardActivity.class);
        this.startActivity(intent);
    }

    public void redirectToFrench(View v) {
        Intent intent = new Intent(this, FrenchDashboardActivity.class);
        this.startActivity(intent);
    }

    public void redirectToHistory(View v) {
        Intent intent = new Intent(this, HistoryDashboardActivity.class);
        this.startActivity(intent);
    }

    public void redirectToMaths(View v) {
        Intent intent = new Intent(this, MathsDashboardActivity.class);
        this.startActivity(intent);
    }

}