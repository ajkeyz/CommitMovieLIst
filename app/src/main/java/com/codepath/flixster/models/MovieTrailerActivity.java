package com.codepath.flixster.models;

import android.os.Bundle;
import android.util.Log;

import com.codepath.flixster.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.RequestParams;

public class MovieTrailerActivity extends YouTubeBaseActivity {
    String API_BASE_URL = "https/www.youtube.com";
    String API_KEY_PARAM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);

        // temporary test video id -- TODO replace with movie trailer video id
        final String videoId = "tKodtNFpzBA";

        // resolve the player view from the layout
        YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.player);

        // initialize with API key stored in secrets.xml
        playerView.initialize(getString(R.string.youtube_api), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                // do any work here to cue video, play video, etc.
                youTubePlayer.cueVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult youTubeInitializationResult) {
                // log the error
                Log.e("MovieTrailerActivity", "Error initializing YouTube player");
            }
        });
    }

    private void getTrailer() {
            //create the url
            String url = API_BASE_URL + "/configuration";

            //set the request parameters
            RequestParams params = new RequestParams();
            params.put(API_KEY_PARAM, getString(R.string.api_key));
        }
    }
}