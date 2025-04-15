package com.example.appactivitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

  private static final int REQUEST_IMAGE_CAPTURE = 1;
  private ImageView imageView;
  private File photoFile;
  private String photoFileName = "photo.jpg";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    imageView = findViewById(R.id.imageView);

    // Corrected ID for the button
    Button cameraBtn = findViewById(R.id.cameraBtn);
    cameraBtn.setOnClickListener(v -> {
      try {
        dispatchTakePictureIntent();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  private File createImageFile() throws IOException {
    // Create an image file name
    File storageDir = getExternalFilesDir(null);
    return File.createTempFile(photoFileName, ".jpg", storageDir);
  }

  private void dispatchTakePictureIntent() throws IOException {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    // Ensure there's a camera activity to handle the intent
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      // Create the file where the photo should go
      photoFile = createImageFile();
      if (photoFile != null) {
        Uri photoURI = FileProvider.getUriForFile(this,
                "com.example.appactivitylifecycle.fileprovider", // Your file provider authority
                photoFile);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
      }
    }
  }

  // Handle the result of the camera intent
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      // Display the image in the ImageView
      Uri photoURI = Uri.fromFile(photoFile);
      imageView.setImageURI(photoURI);
    }
  }
}
