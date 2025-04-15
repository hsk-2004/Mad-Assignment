package com.example.appactivitylifecycle;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView nameView, pathView, sizeView, dateView;
    Button deleteBtn;
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        imageView = findViewById(R.id.imageView);
        nameView = findViewById(R.id.nameView);
        pathView = findViewById(R.id.pathView);
        sizeView = findViewById(R.id.sizeView);
        dateView = findViewById(R.id.dateView);
        deleteBtn = findViewById(R.id.deleteBtn);

        imagePath = getIntent().getStringExtra("imagePath");
        if (imagePath != null) {
            File imgFile = new File(imagePath);

            Glide.with(this).load(imgFile).into(imageView);
            nameView.setText("Name: " + imgFile.getName());
            pathView.setText("Path: " + imgFile.getAbsolutePath());
            sizeView.setText("Size: " + (imgFile.length() / 1024) + " KB");

            Date lastModified = new Date(imgFile.lastModified());
            String formattedDate = new SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(lastModified);
            dateView.setText("Date: " + formattedDate);

            deleteBtn.setOnClickListener(v -> showDeleteDialog(imgFile));
        }
    }

    private void showDeleteDialog(File file) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Image")
                .setMessage("Are you sure you want to delete this image?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    if (file.delete()) {
                        Toast.makeText(this, "Image deleted", Toast.LENGTH_SHORT).show();
                        finish(); // back to gallery
                    } else {
                        Toast.makeText(this, "Failed to delete image", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
