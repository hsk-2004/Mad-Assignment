package com.example.appactivitylifecycle;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Button lightThemeButton, darkThemeButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences("theme", MODE_PRIVATE);

        lightThemeButton = findViewById(R.id.lightThemeButton);
        darkThemeButton = findViewById(R.id.darkThemeButton);

        // Apply saved theme
        if (sharedPreferences.getBoolean("isDarkTheme", false)) {
            setTheme(android.R.style.Theme_DeviceDefault_NoActionBar);
        } else {
            setTheme(android.R.style.Theme_DeviceDefault_Light_NoActionBar);
        }

        // Button click listeners
        lightThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme(android.R.style.Theme_DeviceDefault_Light_NoActionBar);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isDarkTheme", false);
                editor.apply();
            }
        });

        darkThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTheme(android.R.style.Theme_DeviceDefault_NoActionBar);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isDarkTheme", true);
                editor.apply();
            }
        });
    }
}
