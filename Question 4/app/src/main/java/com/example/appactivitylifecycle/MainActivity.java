package com.example.appactivitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;  // Add this line


public class MainActivity extends AppCompatActivity {

  private static final int RC_SIGN_IN = 100;
  private GoogleSignInClient mGoogleSignInClient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();

    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    Button signInBtn = findViewById(R.id.signInButton);
    signInBtn.setOnClickListener(view -> signIn());
  }

  private void signIn() {
    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
    startActivityForResult(signInIntent, RC_SIGN_IN);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == RC_SIGN_IN) {
      Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

      try {
        GoogleSignInAccount account = task.getResult(ApiException.class);
        String name = account.getDisplayName();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
      } catch (ApiException e) {
        Log.w("Google Sign In", "signInResult:failed code=" + e.getStatusCode());
        Toast.makeText(this, "Sign-In Failed!", Toast.LENGTH_SHORT).show();
      }
    }
  }
}
