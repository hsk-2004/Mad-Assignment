package com.example.appactivitylifecycle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable; // Add this import

public class MainActivity extends AppCompatActivity {

  LottieAnimationView lottieAnimationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    lottieAnimationView = findViewById(R.id.lottieAnimationView);

    lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE); // Correct usage
    lottieAnimationView.playAnimation();
  }
}
