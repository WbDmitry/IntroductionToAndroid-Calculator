package com.example.introductiontoandroidgb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.introductiontoandroidgb.calculator.Calculator;

public class CalculatorActivity extends AppCompatActivity {
    private static final String appTheme = "APP_THEME";

    private static final int MYTHEME1 = 0;
    private static final int MYTHEME2 = 1;
    private static final int MYTHEME3 = 2;

    private static final String NameSharedPreference = "LOGIN";
    public static final int REQUEST_CODE = 109;

    private char OPERATION;
    private static final char SPLIT = '/';
    private static final char MULTIPLY = '*';
    private static final char MINUS = '-';
    private static final char PLUS = '+';
    private static final int MAX_LENGTH_TEXTVIEW = 11;

    private TextView tv_text;
    private TextView tv_calculateHistory;

    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.MyTheme1));
        setContentView(R.layout.activity_calculator_new);

        calculator = new Calculator();

        initView();
    }


    private void initView() {
        findViewById(R.id.iconChangeTheme).setOnClickListener(v -> {
            Intent intentChangeThemeActivity = new Intent(this, ChangeThemeActivity.class);
            startActivityForResult(intentChangeThemeActivity, REQUEST_CODE);
        });

        tv_text = findViewById(R.id.progressOfOperations);
        tv_calculateHistory = findViewById(R.id.resultOperations);

        Button buttonNum0 = findViewById(R.id.button_num_0);
        Button buttonNum1 = findViewById(R.id.button_num_1);
        Button buttonNum2 = findViewById(R.id.button_num_2);
        Button buttonNum3 = findViewById(R.id.button_num_3);
        Button buttonNum4 = findViewById(R.id.button_num_4);
        Button buttonNum5 = findViewById(R.id.button_num_5);
        Button buttonNum6 = findViewById(R.id.button_num_6);
        Button buttonNum7 = findViewById(R.id.button_num_7);
        Button buttonNum8 = findViewById(R.id.button_num_8);
        Button buttonNum9 = findViewById(R.id.button_num_9);
        Button buttonComma = findViewById(R.id.button_comma);
        Button buttonSplit = findViewById(R.id.button_split);
        Button buttonMultiply = findViewById(R.id.button_multiply);
        Button buttonMinus = findViewById(R.id.button_minus);
        Button buttonPlus = findViewById(R.id.button_plus);
        Button buttonEqually = findViewById(R.id.button_equally);
        Button buttonWipe = findViewById(R.id.button_wipe);

        enteringAValue(buttonNum0, "0");
        enteringAValue(buttonNum1, "1");
        enteringAValue(buttonNum2, "2");
        enteringAValue(buttonNum3, "3");
        enteringAValue(buttonNum4, "4");
        enteringAValue(buttonNum5, "5");
        enteringAValue(buttonNum6, "6");
        enteringAValue(buttonNum7, "7");
        enteringAValue(buttonNum8, "8");
        enteringAValue(buttonNum9, "9");
        enteringAValue(buttonComma, ".");

        enteringAValue(buttonSplit, "/", '/');
        enteringAValue(buttonMultiply, "*", '*');
        enteringAValue(buttonMinus, "-", '-');
        enteringAValue(buttonPlus, "+", '+');

        calculate(buttonEqually);
        btnWipe(buttonWipe);
    }

    @SuppressLint("SetTextI18n")
    public void enteringAValue(Button name, String num) {
        name.setOnClickListener(v -> {
            if (tv_text.length() > MAX_LENGTH_TEXTVIEW) {
                Toast.makeText(this,
                        "Нельзя ввести больше " + MAX_LENGTH_TEXTVIEW + " цифр",
                        Toast.LENGTH_SHORT).show();
            } else {
                setTheValue(tv_text, tv_text.getText() + num);
                calculateHistory(num);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void enteringAValue(Button name, String sign, char operation) {
        name.setOnClickListener(v -> {
            OPERATION = operation;
            calculator.setValue1(valueOfValues());
            calculateHistory(sign);
            setTheValue(tv_text, "");
        });
    }

    private void btnWipe(Button name) {
        name.setOnClickListener(v -> {
            setTheValue(tv_text, "");
            setTheValue(tv_calculateHistory, "");
        });
    }

    private void calculate(Button name) {
        name.setOnClickListener(v -> {
            calculator.setValue2(valueOfValues());
            switch (OPERATION) {
                case (PLUS):
                    setTheValue(tv_text, calculator.calcPlus());
                    break;
                case (MINUS):
                    setTheValue(tv_text, calculator.calcMinus());
                    break;
                case (MULTIPLY):
                    setTheValue(tv_text, calculator.calcMultiply());
                    break;
                case (SPLIT):
                    setTheValue(tv_text, calculator.calcSplit());
                    break;
            }
        });
    }

    private void setTheValue(TextView text, String s) {
        text.setText(s);
    }

    private double valueOfValues() {
        return Double.parseDouble(tv_text.getText().toString());
    }

    @SuppressLint("SetTextI18n")
    private void calculateHistory(String value) {
        tv_calculateHistory.setText(tv_calculateHistory.getText() + value);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                recreate();
            }
        }
    }

    private int getAppTheme(int code) {
        return codeStyleToStyleId(getCodeStyle(code));
    }

    public static int codeStyleToStyleId(int code) {
        switch (code) {
            case MYTHEME2:
                return R.style.MyTheme2;
            case MYTHEME3:
                return R.style.MyTheme3;
            case MYTHEME1:
            default:
                return R.style.MyTheme1;
        }
    }

    private int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPreferences = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        return sharedPreferences.getInt(appTheme, codeStyle);
    }
}