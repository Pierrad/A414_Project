package com.example.project.french;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.project.R;
import com.example.project.english.EnglishDashboardActivity;
import com.example.project.french.dictation.DictationActivity;
import com.example.project.french.quizz.QuizzActivity;

import java.util.Objects;

public class FrenchDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.french_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard - Français");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.frenchMain)));
        // Set StatusBar color
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.frenchMain));
    }
    public void redirectToDictation(View v) {
        Intent intent = new Intent(this, DictationActivity.class);
        this.startActivity(intent);
    }
    public void redirectToQuizz(View v) {
        Intent intent = new Intent(this, QuizzActivity.class);
        this.startActivity(intent);
    }
}