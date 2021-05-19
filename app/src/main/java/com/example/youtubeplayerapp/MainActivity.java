package com.example.youtubeplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playButton = findViewById(R.id.playButton);
        EditText editTextURL = findViewById(R.id.editTextUrl);

        // Set on click listener for the play button.
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = editTextURL.getText().toString();
                String videoString;

                // Checks if link is a valid URL and not from the mobile app. Retrieves only the
                // video ID and passes it onto the next activity.
                if (URLUtil.isValidUrl(URL) && !URL.contains(".be")) {
                    int beginning = URL.indexOf("=");
                    if (URL.contains("&")) {
                        int end = URL.indexOf("&");
                        videoString = URL.substring(beginning + 1, end);
                    } else {
                        videoString = URL.substring(beginning + 1);
                    }
                    Intent intent = new Intent(MainActivity.this, VideoPlayerActivity.class);
                    intent.putExtra("VIDEO_URL", videoString);
                    startActivity(intent);
                }

                // Checks if link is valid URL. Will start at index 17 as this is where the video ID
                // begins in all links from the mobile app. Passes on video ID to next activity.
                else if (URLUtil.isValidUrl(URL)) {
                    int beginning = 17;
                    videoString = URL.substring(beginning);
                    Intent intent = new Intent(MainActivity.this, VideoPlayerActivity.class);
                    intent.putExtra("VIDEO_URL", videoString);
                    startActivity(intent);
                }
                // Informs user of invalid URL.
                else
                {
                    Toast.makeText(MainActivity.this, "Invalid URL, please enter a valid URL.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}