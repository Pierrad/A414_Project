package com.example.project.english;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

import java.util.Objects;

public class OralComprehensionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.english_oralcomprehension);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard - Anglais");

    }
}
