package com.vanhackathon.dreamshop.frag;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.vanhackathon.dreamshop.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.vanhackathon.dreamshop.frag.YoutubeFrag.URL_TAG;

/**
 * Created by jrvansuita on 23/10/16.
 */

public class YoutubePreviewFrag extends Fragment implements YouTubeThumbnailView.OnInitializedListener {


    private YouTubeThumbnailView preview;


    public static void place(FragmentActivity activity, int layoutId, String url) {
        YoutubePreviewFrag frag = new YoutubePreviewFrag();
        Bundle b = new Bundle();
        b.putString(YoutubeFrag.URL_TAG, url);
        frag.setArguments(b);

        activity.getFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(layoutId, frag)
                .commit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.youtube_preview, null, false);

        preview = (YouTubeThumbnailView) v.findViewById(R.id.imageView_thumbnail);

        return v;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        preview.initialize(YoutubeFrag.API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView view, YouTubeThumbnailLoader loader) {
        final String url = getArguments().getString(URL_TAG);
        final String id = getVideoID(url);
        loader.setVideo(id);

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Issue #3 - Need to resolve StandalonePlayer as well
                if (YouTubeIntents.canResolvePlayVideoIntentWithOptions(getActivity())) {
                    //Opens in the StandAlonePlayer but in "Light box" mode
                    startActivity(YouTubeStandalonePlayer.createVideoIntent(getActivity(),
                            YoutubeFrag.API_KEY, id, 0, true, true));
                }
            }
        });
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView thumbnailView, YouTubeInitializationResult errorReason) {
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
