package com.example.project.french.dictation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.example.project.english.EnglishDashboardActivity;

import java.util.Objects;

public class DictationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictation);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dictation");
    }

}
