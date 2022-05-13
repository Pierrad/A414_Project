package com.example.project.maths;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project.R;

import java.util.Objects;

public class MathsDashboardActivity extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    EditText[] editTexts;

    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    Button buttonFour;
    Button buttonFive;
    Button buttonSix;
    Button buttonPlus;
    Button buttonMinus;
    Button buttonMultiply;
    Button[] buttons;
    Button[] buttonsOperand;

    Boolean initialColorState = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard - Maths");

        editText1 = findViewById(R.id.editTextTextPersonName);
        editText2 = findViewById(R.id.editTextTextPersonName2);
        editText3 = findViewById(R.id.editTextTextPersonName3);
        editText4 = findViewById(R.id.editTextTextPersonName4);
        editText5 = findViewById(R.id.editTextTextPersonName5);

        buttonOne = findViewById(R.id.buttonNumberOne);
        buttonTwo = findViewById(R.id.buttonNumberTwo);
        buttonThree = findViewById(R.id.buttonNumberThree);
        buttonFour = findViewById(R.id.buttonNumberFour);
        buttonFive = findViewById(R.id.buttonNumberFive);
        buttonSix = findViewById(R.id.buttonNumberSix);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMultiply = findViewById(R.id.buttonMultiply);

        // define list
        editTexts = new EditText[]{editText1, editText2, editText3, editText4, editText5};
        buttons = new Button[]{buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonPlus, buttonMinus, buttonMultiply};
        buttonsOperand = new Button[]{buttonPlus, buttonMinus, buttonMultiply};
        // make all editTexts unclickable
        for (EditText editText : editTexts) {
            editText.setClickable(false);
            editText.setFocusable(false);
        }

        // make all buttonsOperand unclickable
        for (Button button : buttonsOperand) {
            button.setClickable(false);
            button.setBackgroundColor(getResources().getColor(R.color.disabledButton));
        }
    }
    // function that log content of each editText when a button is clicked
    public void logContent(View view) {
        for (EditText editText : editTexts) {
            System.out.println(editText.getText().toString().trim().equals(""));
            System.out.println(editText.getText().toString());
        }
    }

    // invert button color
    public void invertButtons() {
        if (initialColorState) {
            for (Button button : buttons) {
                button.setClickable(false);
                button.setBackgroundColor(getResources().getColor(R.color.disabledButton));
            }
            for (Button button : buttonsOperand) {
                button.setClickable(true);
                button.setBackgroundColor(getResources().getColor(R.color.defaultButton));
            }
            initialColorState = false;
        } else {
            for (Button button : buttons) {
                button.setClickable(true);
                button.setBackgroundColor(getResources().getColor(R.color.defaultButton));
            }
            for (Button button : buttonsOperand) {
                button.setClickable(false);
                button.setBackgroundColor(getResources().getColor(R.color.disabledButton));
            }
            initialColorState = true;
        }
    }

    // add value in the last empty editText if there is one
    public void addValue(int value) {
        for (int i = 0; i < editTexts.length; i++) {
            if (editTexts[i].getText().toString().trim().equals("")) {
                // int to string
                editTexts[i].setText(String.valueOf(value));
                break;
            }
        }
    }

    public void updateCalc(View view) {
        switch (view.getId()) {

        }
    }

}