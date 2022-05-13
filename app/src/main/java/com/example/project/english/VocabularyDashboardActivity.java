package com.example.project.english;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

import java.util.Objects;

public class VocabularyDashboardActivity extends AppCompatActivity {
    private Intent words;
    private Intent colors;
    private Intent figures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.english_vocabulary_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard - Anglais");

        //this.words = new Intent(this, VocabularyWordsActivity.class);
        //this.colors = new Intent(this, VocabularyColorsActivityy.class);
        //this.figures = new Intent(this, OVocabularyFiguresActivity.class);

    }

    public void redirectToWordsTranslation(View view) {
        //TO DO
    }

    public void redirectToColorsTranslation(View view) {
        //TO DO
    }

    public void redirectToFiguresTranslation(View view) {
        //TO DO
    }
}
