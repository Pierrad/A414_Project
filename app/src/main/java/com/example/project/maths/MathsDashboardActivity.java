/*
                     ██████╗ █████╗ ██╗      ██████╗██╗   ██╗██╗
                    ██╔════╝██╔══██╗██║     ██╔════╝██║   ██║██║
                    ██║     ███████║██║     ██║     ██║   ██║██║
                    ██║     ██╔══██║██║     ██║     ██║   ██║██║
                    ╚██████╗██║  ██║███████╗╚██████╗╚██████╔╝███████╗
                     ╚═════╝╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═════╝ ╚══════╝

                                    ██████╗ ██╗   ██╗
                                    ██╔══██╗╚██╗ ██╔╝
                                    ██████╔╝ ╚████╔╝
                                    ██╔══██╗  ╚██╔╝
                                    ██████╔╝   ██║
                                    ╚═════╝    ╚═╝

     ██╗ ██████╗ ███████╗███████╗     ██████╗ ██████╗ ██╗   ██╗██████╗ ███████╗████████╗
     ██║██╔═══██╗██╔════╝██╔════╝    ██╔════╝██╔═══██╗██║   ██║██╔══██╗██╔════╝╚══██╔══╝
     ██║██║   ██║███████╗███████╗    ██║     ██║   ██║██║   ██║██████╔╝█████╗     ██║
██   ██║██║   ██║╚════██║╚════██║    ██║     ██║   ██║██║   ██║██╔═══╝ ██╔══╝     ██║
╚█████╔╝╚██████╔╝███████║███████║    ╚██████╗╚██████╔╝╚██████╔╝██║     ███████╗   ██║
 ╚════╝  ╚═════╝ ╚══════╝╚══════╝     ╚═════╝ ╚═════╝  ╚═════╝ ╚═╝     ╚══════╝   ╚═╝
 */

package com.example.project.maths;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.helpers.JSONHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Random;

public class MathsDashboardActivity extends AppCompatActivity {

    TextView tvResult;

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
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
    Button buttonCancel;
    Button buttonReset;
    Button[] buttons;
    Button[] buttonsOperand;

    Boolean initialColorState = true;

    com.example.project.helpers.JSONHandler JSONHandler;
    String JSON;
    JSONArray jsonArray;

    int numberOne;
    int numberTwo;
    int numberThree;
    int numberFour;
    int numberFive;
    int numberSix;

    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maths_dashboard);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard - Maths");

        //=====================================================<  GET ELEMENTS FROM VIEW  >==================================================

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
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonReset = findViewById(R.id.buttonReset);

        tvResult = findViewById(R.id.textView5);

        // hide reset button
        buttonReset.setVisibility(View.GONE);

        //===========================================================<  DEVIL JSON  >========================================================

        JSONHandler = new JSONHandler("calcul.json", this.getApplicationContext());
        JSON = JSONHandler.getJSON();

        JSONObject jsonObject = null;
        try { jsonObject = new JSONObject(JSON); }
        catch (JSONException e) { e.printStackTrace(); }

        try { jsonArray = jsonObject.getJSONArray("calculs"); }
        catch (JSONException e) { e.printStackTrace(); }

        //=========================================================<  SET VALUES  >========================================================

        setResultAndNumbers();

        //==========================================================<  SET VIEW  >========================================================
        // define list
        editTexts = new EditText[]{editText1, editText2, editText3, editText4, editText5};
        buttons = new Button[]{buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonPlus, buttonMinus, buttonMultiply};
        buttonsOperand = new Button[]{buttonPlus, buttonMinus, buttonMultiply};
        // make all editTexts unclickable
        for (EditText editText : editTexts) {
            editText.setClickable(false);
            editText.setFocusable(false);
        }

        // make all buttonsOperand unclickable at first step
        for (Button button : buttonsOperand) {
            button.setClickable(false);
            button.setBackgroundColor(getResources().getColor(R.color.disabledButton));
        }
    }

    public void setResultAndNumbers() {
        JSONArray ja = jsonArray;

        //========================================================<  RANDOM QUESTION  >======================================================
        int index = (int) (Math.random() * ja.length());
        // get the JSONObject at the index
        JSONObject jo;

        // initialize 3 int n1 n2 n3 that will be ne number of the question
        int n1 = 0;
        int n2 = 0;
        int n3 = 0;

        int currentResult = 0;

        try {
            jo = ja.getJSONObject(index);
            System.out.println(jo);
            n1 = (int) jo.getJSONObject("numbers").get("number1");
            n2 = (int) jo.getJSONObject("numbers").get("number2");
            n3 = (int) jo.getJSONObject("numbers").get("number3");
            currentResult = (int) jo.get("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // initialize 3 int n4 n5 n6 that is random number between 1 and 9 that must be different from n1 n2 n3 and different between them
        int n4 = (int) (Math.random() * 9) + 1;
        int n5 = (int) (Math.random() * 9) + 1;
        int n6 = (int) (Math.random() * 9) + 1;

        while (n4 == n1 || n4 == n2 || n4 == n3) {
            n4 = (int) (Math.random() * 9) + 1;
        }
        while (n5 == n1 || n5 == n2 || n5 == n3 || n5 == n4) {
            n5 = (int) (Math.random() * 9) + 1;
        }
        while (n6 == n1 || n6 == n2 || n6 == n3 || n6 == n4 || n6 == n5) {
            n6 = (int) (Math.random() * 9) + 1;
        }


        //=====================================================<  SHUFFLE POSSIBILITIES  >===================================================

        // list that contains all numbers options
        int[] numbers = new int[]{n1, n2, n3, n4, n5, n6};

        Random rand = new Random();

        for (int i = 0; i < numbers.length; i++) {
            int randomIndexToSwap = rand.nextInt(numbers.length);
            int temp = numbers[randomIndexToSwap];
            numbers[randomIndexToSwap] = numbers[i];
            numbers[i] = temp;
        }

        numberOne = numbers[0];
        numberTwo = numbers[1];
        numberThree = numbers[2];
        numberFour = numbers[3];
        numberFive = numbers[4];
        numberSix = numbers[5];
        result = currentResult;

        //===========================================================<  SET VALUES  >========================================================

        buttonOne.setText(String.valueOf(numberOne));
        buttonTwo.setText(String.valueOf(numberTwo));
        buttonThree.setText(String.valueOf(numberThree));
        buttonFour.setText(String.valueOf(numberFour));
        buttonFive.setText(String.valueOf(numberFive));
        buttonSix.setText(String.valueOf(numberSix));

        tvResult.setText(String.valueOf(result));
    }

    public void operatorButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        addOperator(buttonText);
        invertButtons();
    }

    public void numberButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        if (addValue(Integer.parseInt(buttonText))) {
            invertButtons();
        } else {
            checkResult();
        }
    }

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

    public void addOperator(String operator) {
        for (EditText editText : editTexts) {
            if (editText.getText().toString().trim().equals("")) {
                // int to string
                editText.setText(operator);
                return;
            }
        }
    }

    public boolean addValue(int value) {
        for (int i = 0; i < editTexts.length; i++) {
            if (editTexts[i].getText().toString().trim().equals("")) {
                editTexts[i].setText(String.valueOf(value));
                return i != editTexts.length - 1;
            }
        }
        System.out.println("No empty editText");
        return false;
    }

    public void checkResult () {
        int enteredValues = 0;
        if (!editTexts[1].getText().toString().trim().equals("*")) {
            if (!editTexts[3].getText().toString().trim().equals("*")) {
                switch (editTexts[1].getText().toString().trim()) {
                    case "+":
                        enteredValues = Integer.parseInt(editTexts[0].getText().toString().trim()) + Integer.parseInt(editTexts[2].getText().toString().trim());
                        break;
                    case "-":
                        enteredValues = Integer.parseInt(editTexts[0].getText().toString().trim()) - Integer.parseInt(editTexts[2].getText().toString().trim());
                        break;
                }
                switch (editTexts[3].getText().toString().trim()) {
                    case "+":
                        enteredValues = enteredValues + Integer.parseInt(editTexts[4].getText().toString().trim());
                        break;
                    case "-":
                        enteredValues = enteredValues - Integer.parseInt(editTexts[4].getText().toString().trim());
                        break;
                }
            } else {
                enteredValues = Integer.parseInt(editTexts[4].getText().toString().trim()) * Integer.parseInt(editTexts[2].getText().toString().trim());
                switch (editTexts[1].getText().toString().trim()) {
                    case "+":
                        enteredValues = Integer.parseInt(editTexts[0].getText().toString().trim()) + enteredValues;
                        break;
                    case "-":
                        enteredValues =  Integer.parseInt(editTexts[0].getText().toString().trim()) - enteredValues;
                        break;
                }
            }
        } else {
            enteredValues = Integer.parseInt(editTexts[0].getText().toString().trim()) * Integer.parseInt(editTexts[2].getText().toString().trim());
            switch (editTexts[3].getText().toString().trim()) {
                case "+":
                    enteredValues = enteredValues + Integer.parseInt(editTexts[4].getText().toString().trim());
                    break;
                case "-":
                    enteredValues = enteredValues - Integer.parseInt(editTexts[4].getText().toString().trim());
                    break;
                case "*":
                    enteredValues = enteredValues * Integer.parseInt(editTexts[4].getText().toString().trim());
                    break;
            }
        }
        System.out.println("enteredValues: " + enteredValues);
        if (enteredValues == result) {
            for (EditText editText : editTexts) {
                editText.setTextColor(Color.GREEN);
            }
            buttonCancel.setVisibility(View.GONE);
            buttonReset.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            for (EditText editText : editTexts) {
                editText.setTextColor(Color.RED);
            }
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelButtonClick (View view) {
        for (int i = editTexts.length - 1; i >= 0; i--) {
            if (!editTexts[i].getText().toString().trim().equals("")) {
                initialColorState = editTexts[i].getText().toString().trim().equals("*") || editTexts[i].getText().toString().trim().equals("-") || editTexts[i].getText().toString().trim().equals("+");
                invertButtons();
                for (EditText editText : editTexts) {
                    editText.setTextColor(Color.BLACK);
                }
                editTexts[i].setText("");
                return;
            }
        }
        Toast.makeText(this, "Rien a supprimer", Toast.LENGTH_SHORT).show();
    }

    public void reset (View view) {
        initialColorState = false;
        // reset buttons colors
        invertButtons();
        // create new question
        setResultAndNumbers();
        // clear all editTexts
        for (EditText editText : editTexts) {
            editText.setTextColor(Color.BLACK);
            editText.setText("");
        }
        // reset buttons
        buttonReset.setVisibility(View.GONE);
        buttonCancel.setVisibility(View.VISIBLE);
    }
}