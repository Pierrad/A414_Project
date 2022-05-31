package com.example.project.english.vocabulary;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.example.project.english.quizz.EnglishResultActivity;
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

public class VocabularyColorsActivity extends AppCompatActivity {
    String apiURL = "http://10.0.2.2:3000/english/colors";
    ArrayList<QuizzEntrySimple> quizzEntries = new ArrayList<QuizzEntrySimple>();
    int quizzIndex = 0;
    int score = 0;

    TextView title, question;
    EditText answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_english_simple_entry);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Anglais - Vocabulaire - Couleurs");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.english)));

        title = findViewById(R.id.englishQuizzSimpleTitle);
        question = findViewById(R.id.englishQuizzSimpleQuestion);
        answer = findViewById(R.id.englishQuizzSimpleAnswer);

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
//                    quizzEntries.add(new QuizzEntrySimple(question, answer));
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
                quizzEntries.add(new QuizzEntrySimple(question, answer));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Collections.shuffle(quizzEntries);
        title.setText(getString(R.string.quizz_entry_title, "" + 1, "" + quizzEntries.size()));
        question.setText(getString(R.string.english_quizz_enonce, quizzEntries.get(quizzIndex).getQuestion()));
        answer.setText("");

    }

    private static String getJSONFromAsset(Context context) {
        String JSON = null;
        try {
            InputStream is = context.getAssets().open("englishVocabularyColors.json");
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

    public void submitQuizzSimple(View v) {
        String answerText = answer.getText().toString().toLowerCase();
        if (answerText.equals("")) {
            Toast.makeText(this, "Il faut saisir une réponse avant de valider", Toast.LENGTH_SHORT).show();
            return;
        }
        if (answerText.equals(quizzEntries.get(quizzIndex).getAnswer().toLowerCase())) {
            score += 1;
        }
        quizzIndex += 1;

        if (quizzIndex == quizzEntries.size()) {
            Intent intent = new Intent(this, EnglishResultActivity.class);
            intent.putParcelableArrayListExtra("quizzEntries", quizzEntries);
            intent.putExtra("score", score);
            this.startActivity(intent);
        } else {
            title.setText(getString(R.string.quizz_entry_title, "" + (quizzIndex + 1), "" + quizzEntries.size()));
            question.setText(getString(R.string.english_quizz_enonce, quizzEntries.get(quizzIndex).getQuestion()));
            answer.setText("");
        }
    }
}