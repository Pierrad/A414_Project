package com.example.project.english.quizz;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.project.R;
import com.example.project.english.EnglishDashboardActivity;

import java.util.ArrayList;
import java.util.Objects;

public class EnglishResultActivity extends AppCompatActivity {
    int score, percent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_english_results);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Anglais - Résultats");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.english)));
        // Set StatusBar color
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.english));

        Intent current = getIntent();
        ArrayList<QuizzEntrySimple> quizzEntries = current.getParcelableArrayListExtra("quizzEntries");
        score = current.getIntExtra("score", 0);

        ListView results = findViewById(R.id.englishQuizzListResults);
        TextView title = findViewById(R.id.englishQuizzResultsTitle);
        TextView subtitle = findViewById(R.id.englishQuizzResultsSubtitle);

        percent = (score / quizzEntries.size()) * 100;
        if (percent < 40) {
            title.setText(getResources().getText(R.string.quizz_results_bad));
        } else if (percent > 40 && percent < 60) {
            title.setText(getResources().getText(R.string.quizz_results_okay));
        } else if (percent > 60 && percent < 90) {
            title.setText(getResources().getText(R.string.quizz_results_good));
        } else {
            title.setText(getResources().getText(R.string.quizz_results_very_good));
        }
        subtitle.setText(getString(R.string.quizz_results_score, "" + (score), "" + quizzEntries.size()));

        QuizzEntryAdapter adapter = new QuizzEntryAdapter(this, quizzEntries);
        results.setAdapter(adapter);
    }

    public void goBackToEnglishDashboard(View view) {
        Intent englishDashboard = new Intent(this, EnglishDashboardActivity.class);
        this.startActivity(englishDashboard);
    }
}