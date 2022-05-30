package com.example.project.english;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.project.R;
import com.example.project.english.conjugaison.ConjugaisonActivity;
import com.example.project.english.oralcomprehension.OralComprehensionActivity;
import com.example.project.english.vocabulary.VocabularyDashboardActivity;

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
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.english)));

        this.vocabulary = new Intent(this, VocabularyDashboardActivity.class);
        this.conjugaison = new Intent(this, ConjugaisonActivity.class);
        this.oralComprehension = new Intent(this, OralComprehensionActivity.class);

    }

    public void redirectToVocabulary(View view) {
        this.startActivity(vocabulary);
    }

    public void startQuizzConjugaison(View view) {
        this.startActivity(conjugaison);
    }

    public void startQuizzOralComprehension(View view) {
        this.startActivity(oralComprehension);
    }
}