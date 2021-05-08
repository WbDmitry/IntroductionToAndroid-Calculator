package com.example.introductiontoandroidgb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class ChangeThemeActivity extends AppCompatActivity {
    private static final String appTheme = "APP_THEME";

    private static final int MYTHEME1 = 0;
    private static final int MYTHEME2 = 1;
    private static final int MYTHEME3 = 2;

    private static final String NameSharedPreference = "LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.MyTheme1));
        setContentView(R.layout.activity_change_theme);

        initView();
    }

    private void initView() {
        findViewById(R.id.theme1).setOnClickListener(v -> {
            setAppTheme(MYTHEME1);
            recreate();
        });

        findViewById(R.id.theme2).setOnClickListener(v -> {
            setAppTheme(MYTHEME2);
            recreate();
        });

        findViewById(R.id.theme3).setOnClickListener(v -> {
            setAppTheme(MYTHEME3);
            recreate();
        });
    }

    private int getAppTheme(int code) {
        return codeStyleToStyleId(getCodeStyle(code));
    }

    private void setAppTheme(int code) {
        SharedPreferences sharedPreferences = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        sharedPreferences.edit()
                .putInt(appTheme, code)
                .apply();
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