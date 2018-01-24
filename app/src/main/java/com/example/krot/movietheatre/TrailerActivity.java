package com.example.krot.movietheatre;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrailerActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener,
        YouTubePlayer.PlaybackEventListener,
        YouTubePlayer.PlayerStateChangeListener {

    public static final String YOUTUBE_API_KEY = "AIzaSyB9eXaLJN20Q4-tFFU71UFS7TnHb63xTFs";
    private static final String MOVIE_ID = "MOVIE_ID";
    private static final String MOVIE_TITLE = "MOVIE_TITLE";

    @BindView(R.id.icon_back)
    ImageView imgBackIcon;
    @BindView(R.id.ypv_trailer)
    YouTubePlayerView ypvTrailer;
    @BindView(R.id.tv_trailer_movie_title)
    TextView tvTrailerMovieTitle;


    String movieId;
    String movieTitle;

    public static Intent createTrailerIntent(Context context, String movieId, String movieTitle){
        Intent intent = new Intent(context, TrailerActivity.class);
        intent.putExtra(MOVIE_ID, movieId);
        intent.putExtra(MOVIE_TITLE, movieTitle);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("WTF", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);
        ButterKnife.bind(this);
        Intent receivedIntent = getIntent();
        movieId = receivedIntent.getStringExtra(MOVIE_ID);
        movieTitle = receivedIntent.getStringExtra(MOVIE_TITLE);
        tvTrailerMovieTitle.setText(movieTitle);
        Log.i("WTF", "movieId = " + movieId + " - movieTitle = " + movieTitle);
        ypvTrailer.initialize(YOUTUBE_API_KEY, this);


    }

    @OnClick(R.id.icon_back)
    public void onBack() {
        finish();
    }


    /**YoutubePlayerView**/
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.i("WTF", "onInitializationSuccess: movieId = " + movieId);
        if (youTubePlayer == null) return;
        if (!wasRestored) {
            youTubePlayer.cueVideo(movieId);
            youTubePlayer.setPlaybackEventListener(this);
            youTubePlayer.setPlayerStateChangeListener(this);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "" + youTubeInitializationResult, Toast.LENGTH_SHORT).show();
        Log.i("WTF", "" + youTubeInitializationResult);
    }


    /**PlaybackEventListener**/

    @Override
    public void onPlaying() {
        //video đang play
        Log.i("WTF", "PlaybackEventListener: onPlaying");
    }

    @Override
    public void onPaused() {
        //video đc pause
        Log.i("WTF", "PlaybackEventListener: onPaused");
    }

    @Override
    public void onStopped() {
        //initial state và sau khi end video
        Log.i("WTF", "PlaybackEventListener: onStopped");
    }

    @Override
    public void onBuffering(boolean b) {
        //Sau khi ấn play, load video, hiện icon pause
        Log.i("WTF", "PlaybackEventListener: onBuffering");
    }

    @Override
    public void onSeekTo(int i) {
        //tới 1 vị trí nào đó trong video
        Log.i("WTF", "PlaybackEventListener: onSeekTo");
    }



    /**PlayerStateChangeListener**/

    @Override
    public void onLoading() {
        //dang load show progressbar
        Log.i("WTF", "PlayerStateChangeListener: onLoading");
    }

    @Override
    public void onLoaded(String s) {
        //load xong chờ play (show thumbnail)
        Log.i("WTF", "PlayerStateChangeListener: onLoaded");
    }

    @Override
    public void onAdStarted() {
        Log.i("WTF", "PlayerStateChangeListener: onAdStarted");
    }

    @Override
    public void onVideoStarted() {
        //User ấn play
        Log.i("WTF", "PlayerStateChangeListener: onVideoStarted");
    }

    @Override
    public void onVideoEnded() {
        //hết video
        Log.i("WTF", "PlayerStateChangeListener: onVideoEnded");
    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {
        Log.i("WTF", "PlayerStateChangeListener: onError");
    }


}
