package com.example.youtubeplayerapp;

import android.os.Bundle;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoPlayerActivity extends YouTubeBaseActivity {

    YouTubePlayerView YTPlayer;
    String URL;
    YouTubePlayer.OnInitializedListener onInitListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        YTPlayer = (YouTubePlayerView) findViewById(R.id.youtubePlayer);
        URL = getIntent().getStringExtra("VIDEO_URL");

        // Set initialization listener.
        onInitListener = new YouTubePlayer.OnInitializedListener() {
            // On success, load the URL passed in from the previous activity to be watched.
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(URL);
            }

            // If fails, make a Toast containing the name of the initialisation result.
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(VideoPlayerActivity.this, youTubeInitializationResult.name(), Toast.LENGTH_LONG).show();
            }
        };
        // Call the initialization of the YouTube player.
        YTPlayer.initialize(YouTubeConfig.getApiKey(), onInitListener);
    }
}