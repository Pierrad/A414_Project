package com.example.project.english.vocabulary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.example.project.english.quizz.EnglishResultActivity;
import com.example.project.english.quizz.QuizzEntryMultiple;
import com.example.project.english.quizz.QuizzEntrySimple;
import com.example.project.helpers.HTTPHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class VocabularyWordsActivity extends AppCompatActivity {
    String apiURL = "http://10.0.2.2:3000/english/words";
    ArrayList<QuizzEntryMultiple> quizzEntries = new ArrayList<>();
    int quizzIndex = 0;
    String selected = "";
    int score = 0;

    TextView title, question;
    Button btn1, btn2, btn3;
    LinearLayout linearLayoutButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_english_multiple_entry);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Anglais - Vocabulaire - Mots");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.english)));

        title = findViewById(R.id.englishQuizzMultipleTitle);
        question = findViewById(R.id.englishQuizzMultipleQuestion);
        linearLayoutButtons = findViewById(R.id.englishQuizzMultipleAnswers);
        btn1 = findViewById(R.id.englishQuizzMultipleAnswer1);
        btn2 = findViewById(R.id.englishQuizzMultipleAnswer2);
        btn3 = findViewById(R.id.englishQuizzMultipleAnswer3);

        ArrayList<Button> buttons = new ArrayList<Button>();
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);

        for (Button btn : buttons) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btn1.setBackgroundColor(getResources().getColor(R.color.english_light));
                    btn2.setBackgroundColor(getResources().getColor(R.color.english_light));
                    btn3.setBackgroundColor(getResources().getColor(R.color.english_light));
                    selected = btn.getText().toString();
                    btn.setBackgroundColor(getResources().getColor(R.color.english));
                }
            });
        }

//        HTTPHandler h = new HTTPHandler();
//        String strJSON = h.makeServiceCall(apiURL);
//
//        if (strJSON != null) {
//            try {
//                JSONObject objJSON = new JSONObject(strJSON);
//                JSONArray arrJSON = objJSON.getJSONObject("data").getJSONArray("english");
//
//                for (int i=0; i<arrJSON.length(); i++) {
//                    JSONObject entry = arrJSON.getJSONObject(i);
//                    String question = entry.getString("question");
//                    String answer = entry.getString("answer");
//                    JSONArray possibleAnswersJSON = entry.getJSONArray("possibleAnswers");
//
//                    ArrayList<String> possibleAnswers = new ArrayList<String>();
//                    for (int j=0; j<possibleAnswersJSON.length(); j++) {
//                        possibleAnswers.add(possibleAnswersJSON.getString(j));
//                    }
//
//                    quizzEntries.add(new QuizzEntryMultiple(question, answer, possibleAnswers));
//                }
//            } catch (final JSONException e) {
//                Log.e(HTTPHandler.TAG, "Erreur JSON" + e.getMessage());
//            }
//        } else {
//            Log.e(HTTPHandler.TAG, "Problème connexion");
//        }

        try {
            JSONArray arrJSON = new JSONArray(getJSONFromAsset(this));
            for (int i = 0; i < arrJSON.length(); i++) {
                JSONObject entry = arrJSON.getJSONObject(i);
                String question = entry.getString("question");
                String answer = entry.getString("answer");
                JSONArray possibleAnswersJSON = entry.getJSONArray("possibleAnswers");
                ArrayList<String> possibleAnswers = new ArrayList<String>();
                for (int j=0; j<possibleAnswersJSON.length(); j++) {
                    possibleAnswers.add(possibleAnswersJSON.getString(j));
                }
                quizzEntries.add(new QuizzEntryMultiple(question, answer, possibleAnswers));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Collections.shuffle(quizzEntries);
        title.setText(getString(R.string.quizz_entry_title, "" + 1, "" + quizzEntries.size()));
        QuizzEntryMultiple currentEntry = quizzEntries.get(0);
        question.setText(getString(R.string.english_quizz_enonce, quizzEntries.get(quizzIndex).getQuestion()));
        btn1.setText(currentEntry.getPossibleAnswers().get(0).toString());
        btn2.setText(currentEntry.getPossibleAnswers().get(1).toString());
        btn3.setText(currentEntry.getPossibleAnswers().get(2).toString());
    }

    private static String getJSONFromAsset(Context context) {
        String JSON = null;
        try {
            InputStream is = context.getAssets().open("englishVocabularyWords.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            JSON = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return JSON;
    }

    public void submitQuizzMultiple(View v) {
        if (selected.equals("")) {
            Toast.makeText(this, "Il faut saisir une réponse avant de valider", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selected.equals(quizzEntries.get(quizzIndex).getAnswer().toLowerCase())) {
            score += 1;
        }
        quizzIndex += 1;

        if (quizzIndex == quizzEntries.size()) {
            Intent intent = new Intent(VocabularyWordsActivity.this, EnglishResultActivity.class);
            intent.putParcelableArrayListExtra("quizzEntries", quizzEntries);
            intent.putExtra("score", score);
            this.startActivity(intent);
        } else {
            title.setText(getString(R.string.quizz_entry_title, "" + (quizzIndex + 1), "" + quizzEntries.size()));
            QuizzEntryMultiple currentEntry = quizzEntries.get(quizzIndex);
            question.setText(getString(R.string.english_quizz_enonce, quizzEntries.get(quizzIndex).getQuestion()));
            btn1.setBackgroundColor(getResources().getColor(R.color.english_light));
            btn2.setBackgroundColor(getResources().getColor(R.color.english_light));
            btn3.setBackgroundColor(getResources().getColor(R.color.english_light));
            btn1.setText(currentEntry.getPossibleAnswers().get(0));
            btn2.setText(currentEntry.getPossibleAnswers().get(1));
            btn3.setText(currentEntry.getPossibleAnswers().get(2));
            selected = "";
        }
    }
}