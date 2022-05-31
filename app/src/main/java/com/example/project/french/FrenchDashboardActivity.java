package com.example.project.french;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project.R;
import com.example.project.english.EnglishDashboardActivity;
import com.example.project.french.dictation.DictationActivity;

import java.util.Objects;

public class FrenchDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.french_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard - Français");
    }
    public void redirectToDictation(View v) {
        Intent intent = new Intent(this, DictationActivity.class);
        this.startActivity(intent);
    }
}