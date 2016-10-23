package com.vanhackathon.dreamshop.frag;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jrvansuita place 22/10/16.
 */

public class YoutubeFrag extends YouTubePlayerFragment implements YouTubePlayer.OnInitializedListener {

    public static final String URL_TAG = "URL";
    public static final String API_KEY = "AIzaSyA9fNsOyBqo_HCs1vVgxMp0AF0IjyddAdI";


    public static void place(FragmentActivity activity, int layoutId, String url) {
        YoutubeFrag frag = new YoutubeFrag();
        Bundle b = new Bundle();
        b.putString(YoutubeFrag.URL_TAG, url);
        frag.setArguments(b);

        activity.getFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(layoutId, frag)
                .commit();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            String url = getArguments().getString(URL_TAG);

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            //player.loadVideo(getVideoID(url));
            player.cueVideo(getVideoID(url));

            //player.get

            if (getView() != null)
                if (getView().getParent() != null) {
                    ((ViewGroup) getView().getParent()).setVisibility(View.VISIBLE);
                }

            // Hiding player controls
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);

        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        if (result.isUserRecoverableError()) {
            result.getErrorDialog(getActivity(), 1).show();
        }
    }


    private String getVideoID(String url) {

        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url); //url is youtube url for which you want to extract the id.
        if (matcher.find()) {
            return matcher.group();
        }

        return "";
    }
}
