package com.example.appactivitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<File> imageList;
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        gridView = findViewById(R.id.gridView);
        imageList = new ArrayList<>();

        File picturesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (picturesDir != null && picturesDir.isDirectory()) {
            File[] files = picturesDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")) {
                        imageList.add(file);
                    }
                }
            }
        }

        adapter = new ImageAdapter(this, imageList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
            Intent intent = new Intent(GalleryActivity.this, ImageDetailsActivity.class);
            intent.putExtra("imagePath", imageList.get(position).getAbsolutePath());
            startActivity(intent);
        });
    }
}
