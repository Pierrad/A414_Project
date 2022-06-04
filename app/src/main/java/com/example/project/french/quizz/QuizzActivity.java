package com.example.project.french.quizz;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.project.R;
import com.example.project.helpers.JSONHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Objects;

public class QuizzActivity extends AppCompatActivity {
    JSONArray[] quizz;
    int currentQuestion = 0;
    int score = 0;
    TextView subject, result, explanation;
    Button answer1, answer2, answer3;
    String questionIntitule;
    String[] answers = new String[3];
    String correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.french_quizz);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Français - MCQ");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.frenchMain)));
        // Set StatusBar color
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.frenchMain));

        loadQuestions();
        subject = (TextView) findViewById(R.id.MCQQuestion);
        result = (TextView) findViewById(R.id.MCQResult);
        explanation = (TextView) findViewById(R.id.explanations);
        answer1 = (Button) findViewById(R.id.MCQAnswer1);
        answer2 = (Button) findViewById(R.id.MCQAnswer2);
        answer3 = (Button) findViewById(R.id.MCQAnswer3);
        result.setText("Score : " + score + "/" + currentQuestion);
        // While currentQuestion is less than 12 we display the question and the answers
        displayQuestion(quizz[currentQuestion % 3]);
    }

    protected void loadQuestions() {
        JSONHandler jsonHandler = new JSONHandler("french.json",  this.getApplicationContext());
        String stringified = jsonHandler.getJSON();
        try {
            JSONObject jsonArray = new JSONObject(stringified);
            quizz = new JSONArray[jsonArray.length()];
            quizz[0] = jsonArray.getJSONArray("grammar");
            quizz[1] = jsonArray.getJSONArray("spelling");
            quizz[2] = jsonArray.getJSONArray("conjugation");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void displayQuestion(JSONArray quizz) {
        if(currentQuestion >= 12) {
            answer1.setVisibility(View.INVISIBLE);
            answer2.setVisibility(View.INVISIBLE);
            answer3.setVisibility(View.INVISIBLE);
            subject.setText("\uD83C\uDF89 Félicitations ! Vous avez términé le quizz.");
            result.setText("Score : " + score + "/" + currentQuestion);
            return;
        }
        // Chargement des données
        try {
            JSONObject question = quizz.getJSONObject( (int) (Math.random() * quizz.length()) );
            questionIntitule = question.getString("question");
            JSONArray answersJSON = question.getJSONArray("answers");
            answers[0] = answersJSON.getString(0);
            answers[1] = answersJSON.getString(1);
            answers[2] = answersJSON.getString(2);
            correct = question.getString("rightAnswer");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Affichage des données
        subject.setText(questionIntitule);
        answer1.setText(answers[0]);
        answer2.setText(answers[1]);
        answer3.setText(answers[2]);
        answer1.setOnClickListener(this::displayResult);
        answer2.setOnClickListener(this::displayResult);
        answer3.setOnClickListener(this::displayResult);
        currentQuestion++;
    }

    protected void displayResult(View v) {
        result.setText("Score : " + score + "/" + currentQuestion);
        Button clicked = (Button) findViewById(v.getId());
        if (clicked.getText().equals(correct)) {
            score++;
            result.setText("Score : " + score + "/" + currentQuestion);
            explanation.setText("Bonne réponse !\nPassage à la prochaine question");
        } else {
            result.setText("Score : " + score + "/" + currentQuestion);
            explanation.setText("Mauvaise réponse !\n La bonne réponse était : " + correct + "\nPassage à la prochaine question ...");
        }
        v.setOnClickListener(null);
        displayQuestion(quizz[currentQuestion % 3]);
    }
}
