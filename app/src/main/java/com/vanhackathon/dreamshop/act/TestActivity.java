package com.vanhackathon.dreamshop.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.vanhackathon.dreamshop.R;
import com.vanhackathon.dreamshop.frag.YoutubeFrag;

/**
 * Created by jrvansuita on 23/10/16.
 */

public class TestActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);


        YouTubePlayerFragment fragment1, fragment2;
        fragment1 = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment_1);
        fragment2 = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment_2);

        fragment1.initialize(YoutubeFrag.API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                if (!wasRestored) {
                    youTubePlayer.cueVideo("2pLmfbHLi9k");
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });


        fragment2.initialize(YoutubeFrag.API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                if (!wasRestored) {
                    youTubePlayer.cueVideo("wi6yM9tNtJ4");
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

    }
}
