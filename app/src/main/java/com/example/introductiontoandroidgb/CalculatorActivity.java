package com.example.introductiontoandroidgb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CalculatorActivity extends AppCompatActivity {
    private char OPERATION;
    private static final char SPLIT = '/';
    private static final char MULTIPLY = '*';
    private static final char MINUS = '-';
    private static final char PLUS = '+';
    private static final int MAX_LENGTH_TEXTVIEW = 11;
    private Double value1;
    private Double value2;
    private TextView tv_text;
    private TextView tv_calculateHistory;
    private DecimalFormat numberFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        numberFormat = new DecimalFormat("#.##");
        initView();
    }

    private void initView() {
        tv_text = findViewById(R.id.textView);
        tv_calculateHistory = findViewById(R.id.calculationHistory);

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

        enteringAValue(buttonSplit, "/",'/');
        enteringAValue(buttonMultiply, "*",'*');
        enteringAValue(buttonMinus, "-",'-');
        enteringAValue(buttonPlus, "+",'+');

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
                tv_text.setText(tv_text.getText() + num);
                tv_calculateHistory.setText(tv_calculateHistory.getText() + num);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void enteringAValue(Button name, String sign, char operation) {
        name.setOnClickListener(v -> {
            OPERATION = operation;
            value1 = Double.parseDouble(tv_text.getText().toString());
            tv_calculateHistory.setText(tv_calculateHistory.getText() + sign);
            tv_text.setText("");
        });
    }

    private void btnWipe(Button name) {
        name.setOnClickListener(v -> {
            tv_text.setText("");
            tv_calculateHistory.setText("");
        });
    }

    private void calculate(Button name) {
        name.setOnClickListener(v -> {
            value2 = Double.parseDouble(tv_text.getText().toString());
//            tv_calculateHistory.setText("");
            switch (OPERATION) {
                case (PLUS):
                    tv_text.setText(numberFormat.format(value1 + value2));
                    break;
                case (MINUS):
                    tv_text.setText(numberFormat.format(value1 - value2));
                    break;
                case (MULTIPLY):
                    tv_text.setText(numberFormat.format(value1 * value2));
                    break;
                case (SPLIT):
                    tv_text.setText(numberFormat.format(value1 / value2));
                    break;
            }
        });
    }
}