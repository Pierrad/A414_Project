package com.example.project.history.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.history.HistoryDashboardActivity;
import com.example.project.history.quizz.QuizzEntriesAdapter;
import com.example.project.history.quizz.QuizzEntry;

import java.util.ArrayList;
import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

    private ArrayList<QuizzEntry> entries;
    private ListView results;
    private TextView title, subtitle;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_history_results);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Vos résultats");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.history)));

        Intent intent = getIntent();
        entries = intent.getParcelableArrayListExtra("quizzEntries");
        score = intent.getIntExtra("score", 0);

        registerElements();
        renderScore();
        renderResults();
    }

    public void registerElements() {
        results = findViewById(R.id.quizzListResults);
        title = findViewById(R.id.quizzResultsTitle);
        subtitle = findViewById(R.id.quizzResultsSubtitle);
    }

    public void renderScore() {
        int percentageScore = (score / entries.size()) * 100;
        if (percentageScore < 40) {
            title.setText(getResources().getText(R.string.quizz_results_bad));
        } else if (percentageScore > 40 && percentageScore < 60) {
            title.setText(getResources().getText(R.string.quizz_results_okay));
        } else if (percentageScore > 60 && percentageScore < 90) {
            title.setText(getResources().getText(R.string.quizz_results_good));
        } else {
            title.setText(getResources().getText(R.string.quizz_results_very_good));
        }
        subtitle.setText(getString(R.string.quizz_results_score, "" + (score), "" + entries.size()));
    }

    public void renderResults() {
        QuizzEntriesAdapter adapter = new QuizzEntriesAdapter(this, entries);
        results.setAdapter(adapter);
    }

    public void goBackToDashboard(View v) {
        Intent intent = new Intent(this, HistoryDashboardActivity.class);
        this.startActivity(intent);
    }


}