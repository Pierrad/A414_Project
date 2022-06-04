package com.example.project.history.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.project.R;
import com.example.project.helpers.HTTPHandler;
import com.example.project.history.quizz.QuizzEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class QuizzActivity extends AppCompatActivity {
    String apiURL = "";
    ArrayList<QuizzEntry> quizzEntries = new ArrayList<>();
    int quizzIndex = 0;
    String currentSelectedAnswer = "";
    int score = 0;

    TextView title, questionText;
    ToggleButton toggleA, toggleB, toggleC;
    LinearLayout toggles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_history);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Quizz ");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.history)));
        // Set StatusBar color
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.history));

        apiURL = getResources().getString(R.string.API_address) + "/history";

        registerElements();
        setTogglesListeners();
        fetchQuestions();
    }

    public void registerElements() {
        title = findViewById(R.id.quizzTitle);
        questionText = findViewById(R.id.quizzQuestion);
        toggles = findViewById(R.id.quizzAnswers);
        toggleA = findViewById(R.id.quizzAnswer1);
        toggleB = findViewById(R.id.quizzAnswer2);
        toggleC = findViewById(R.id.quizzAnswer3);
    }

    public void checkAndUncheck(ToggleButton toCheck) {
        toggleA.setChecked(false);
        toggleB.setChecked(false);
        toggleC.setChecked(false);

        currentSelectedAnswer = toCheck.getText().toString();
        toCheck.setChecked(true);
    }

    public void uncheckAll() {
        toggleA.setChecked(false);
        toggleB.setChecked(false);
        toggleC.setChecked(false);
    }

    public void setTogglesListeners() {
        ArrayList<ToggleButton> toggles = new ArrayList<ToggleButton>();
        toggles.add(toggleA);
        toggles.add(toggleB);
        toggles.add(toggleC);

        for (ToggleButton toggle : toggles) {
            toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        checkAndUncheck(toggle);
                    }
                }
            });
        }
    }

    public void fetchQuestions() {
        new GetQuestions().execute();
    }

    public void renderQuizzEntry(int quizzIndex) {
        title.setText(getString(R.string.quizz_entry_title, "" + (quizzIndex + 1), "" + quizzEntries.size()));
        currentSelectedAnswer = "";

        QuizzEntry currentEntry = quizzEntries.get(quizzIndex);
        questionText.setText(currentEntry.getQuestion());
        questionText.startAnimation(AnimationUtils.loadAnimation(QuizzActivity.this, R.anim.in));
        setTogglesText(currentEntry.getAllShuffleAnswers().get(0), currentEntry.getAllShuffleAnswers().get(1), currentEntry.getAllShuffleAnswers().get(2));
        toggles.startAnimation(AnimationUtils.loadAnimation(QuizzActivity.this, R.anim.appear));
    }

    public void setTogglesText(String a, String b, String c) {
        setToggleText(toggleA, a);
        setToggleText(toggleB, b);
        setToggleText(toggleC, c);
    }

    public void setToggleText(ToggleButton t, String s) {
        t.setText(s);
        t.setTextOff(s);
        t.setTextOn(s);
    }

    public boolean isSelectedAnswerGood() {
        return currentSelectedAnswer.equals(quizzEntries.get(quizzIndex).getAnswer());
    }

    public void submit(View v) {
        if (currentSelectedAnswer.equals("")) {
            Toast.makeText(this, "N'oubliez pas de sélectionner une réponse 😉", Toast.LENGTH_SHORT).show();
            return;
        }
        if (isSelectedAnswerGood()) {
            score += 1;
        }
        quizzIndex += 1;
        if (quizzIndex == quizzEntries.size()) {
            redirectToResultsActivity();
        } else {
            uncheckAll();
            renderQuizzEntry(quizzIndex);
        }
    }

    public void redirectToResultsActivity() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putParcelableArrayListExtra("quizzEntries", quizzEntries);
        intent.putExtra("score", score);
        this.startActivity(intent);
    }


    private class GetQuestions extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HTTPHandler sh = new HTTPHandler();
            String jsonStr = sh.makeServiceCall(apiURL);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONObject jsonData = jsonObj.getJSONObject("data");
                    JSONArray jsonHistory = jsonData.getJSONArray("history");

                    for (int i = 0; i < jsonHistory.length(); i++) {
                        JSONObject entry = jsonHistory.getJSONObject(i);
                        String question = entry.getString("question");
                        String answer = entry.getString("answer");
                        JSONArray jsonPossiblesAnswers = entry.getJSONArray("possibleAnswers");
                        ArrayList<String> possibleAnswers = new ArrayList<>();

                        for (int j = 0; j < jsonPossiblesAnswers.length(); j++) {
                            possibleAnswers.add(jsonPossiblesAnswers.getString(j));
                        }

                        QuizzEntry mus = new QuizzEntry(question, answer, possibleAnswers);
                        quizzEntries.add(mus);
                    }

                } catch (final JSONException e) {
                    Log.e(HTTPHandler.TAG, "Erreur JSON " + e.getMessage());
                }
            } else {
                Log.e(HTTPHandler.TAG, "Problème connexion ");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Collections.shuffle(quizzEntries);
            renderQuizzEntry(quizzIndex);
        }
    }


}