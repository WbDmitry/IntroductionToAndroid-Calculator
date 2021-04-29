package com.example.introductiontoandroidgb.calculator;

import java.text.DecimalFormat;

public class Calculator {
    private Double value1;
    private Double value2;
    private final DecimalFormat numberFormat = new DecimalFormat("#.##");

    public Calculator() {
        this.value1 = 0.0;
        this.value2 = 0.0;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    public void setValue2(Double value2) {
        this.value2 = value2;
    }

    public String calcPlus ()  {
        return numberFormat.format(value1 + value2);
    }
    public String calcMinus ()  {
        return numberFormat.format(value1 - value2);
    }
    public String calcMultiply ()  {
        return numberFormat.format(value1 * value2);
    }
    public String calcSplit ()  {
        return numberFormat.format(value1 / value2);
    }
}
