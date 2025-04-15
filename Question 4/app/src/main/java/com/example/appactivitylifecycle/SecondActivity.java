package com.example.appactivitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.tasks.Task;

public class SecondActivity extends AppCompatActivity {

  GoogleSignInClient mGoogleSignInClient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);

    String name = getIntent().getStringExtra("name");
    TextView welcomeText = findViewById(R.id.welcomeText);
    Button logoutBtn = findViewById(R.id.logoutButton);

    welcomeText.setText("Welcome, " + name + "!");

    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();
    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    logoutBtn.setOnClickListener(v -> {
      mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> {
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
        finish();
      });
    });
  }
}
