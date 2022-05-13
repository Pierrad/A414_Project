package com.example.project.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project.R;

import java.util.Objects;

public class EnglishDashboardActivity extends AppCompatActivity {
    private Intent vocabulary;
    private Intent conjugaison;
    private Intent oralComprehension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.english_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard - Anglais");

        this.vocabulary = new Intent(this, VocabularyDashboardActivity.class);
        this.conjugaison = new Intent(this, ConjugaisonActivity.class);
        this.oralComprehension = new Intent(this, OralComprehensionActivity.class);

    }

    public void redirectToVocabulary(View view) {
        this.startActivity(vocabulary);
    }

    public void redirectToConjugaison(View view) {
        this.startActivity(conjugaison);
    }

    public void redirectToOralComprehension(View view) {
        this.startActivity(oralComprehension);
    }
}