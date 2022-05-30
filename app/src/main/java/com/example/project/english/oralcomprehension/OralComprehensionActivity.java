package com.example.project.english.oralcomprehension;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

import java.util.Objects;

public class OralComprehensionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_english_multiple_entry);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Anglais - Compréhension Orale");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.english)));

        //TODO
    }
}