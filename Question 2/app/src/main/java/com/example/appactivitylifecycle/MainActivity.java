package com.example.appactivitylifecycle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromUnit, toUnit;
    Button convertButton, settingsButton;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Apply saved theme before super.onCreate
        SharedPreferences preferences = getSharedPreferences("theme", MODE_PRIVATE);
        if (preferences.getBoolean("isDarkTheme", false)) {
            setTheme(android.R.style.Theme_DeviceDefault_NoActionBar);
        } else {
            setTheme(android.R.style.Theme_DeviceDefault_Light_NoActionBar);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnit = findViewById(R.id.fromUnit);
        toUnit = findViewById(R.id.toUnit);
        convertButton = findViewById(R.id.convertButton);
        settingsButton = findViewById(R.id.settingsButton);
        resultText = findViewById(R.id.resultText);

        String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromUnit.setAdapter(adapter);
        toUnit.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputValue.getText().toString();
                if (!input.isEmpty()) {
                    try {
                        double value = Double.parseDouble(input);
                        String from = fromUnit.getSelectedItem().toString();
                        String to = toUnit.getSelectedItem().toString();
                        double result = convertUnits(value, from, to);
                        resultText.setText(String.format("Converted value: %.2f", result));
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Settings button logic
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private double convertUnits(double value, String fromUnit, String toUnit) {
        double valueInMeters = 0;
        switch (fromUnit) {
            case "Feet":
                valueInMeters = value * 0.3048;
                break;
            case "Inches":
                valueInMeters = value * 0.0254;
                break;
            case "Centimeters":
                valueInMeters = value * 0.01;
                break;
            case "Meters":
                valueInMeters = value;
                break;
            case "Yards":
                valueInMeters = value * 0.9144;
                break;
        }

        switch (toUnit) {
            case "Feet":
                return valueInMeters / 0.3048;
            case "Inches":
                return valueInMeters / 0.0254;
            case "Centimeters":
                return valueInMeters / 0.01;
            case "Meters":
                return valueInMeters;
            case "Yards":
                return valueInMeters / 0.9144;
        }

        return 0;
    }
}
