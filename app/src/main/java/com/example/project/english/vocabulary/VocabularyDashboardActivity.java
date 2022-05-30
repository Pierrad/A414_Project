package com.example.project.english.vocabulary;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.example.project.english.vocabulary.VocabularyColorsActivity;
import com.example.project.english.vocabulary.VocabularyFiguresActivity;
import com.example.project.english.vocabulary.VocabularyWordsActivity;

import java.util.Objects;

public class VocabularyDashboardActivity extends AppCompatActivity {
    private Intent words;
    private Intent colors;
    private Intent figures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.english_vocabulary_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Anglais - Vocabulaire - Dashboard");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.english)));

        this.words = new Intent(this, VocabularyWordsActivity.class);
        this.colors = new Intent(this, VocabularyColorsActivity.class);
        this.figures = new Intent(this, VocabularyFiguresActivity.class);

    }

    public void startQuizzWordsTranslation(View view) {
        this.startActivity(words);
    }

    public void startQuizzColorsTranslation(View view) {
        this.startActivity(colors);
    }

    public void startQuizzFiguresTranslation(View view) {
        this.startActivity(figures);
    }
}