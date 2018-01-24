package com.example.krot.movietheatre.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krot.movietheatre.R;
import com.example.krot.movietheatre.TrailerActivity;
import com.example.krot.movietheatre.bus.EventAddNewMovie;
import com.example.krot.movietheatre.bus.EventMoveToTrailer;
import com.example.krot.movietheatre.bus.EventPlaybackVideo;
import com.example.krot.movietheatre.bus.EventRemoveMovie;
import com.example.krot.movietheatre.bus.RxBus;
import com.example.krot.movietheatre.model.MovieItem;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Krot on 1/17/18.
 */

public class MovieItemViewHolder extends ItemBaseViewHolder<MovieItem> implements
        YouTubeThumbnailView.OnInitializedListener, ImageView.OnClickListener {

    @BindView(R.id.tv_movie_rating)
    TextView mTvMovieRating;

    @BindView(R.id.tv_movie_title)
    TextView mTvMovieTitle;

    @BindView(R.id.tv_movie_id)
    TextView mTvMovieId;

    @BindView(R.id.tv_movie_overview)
    TextView mTvMovieOverview;

    @BindView(R.id.tv_movie_release_date)
    TextView mTvMovieReleaseDate;

    @BindView(R.id.movie_container)
    public FrameLayout mMovieContainer;

    @BindView(R.id.ic_add_movie)
    ImageView mIconAddMovie;

    @BindView(R.id.ic_remove_movie)
    ImageView mIconRemoveMovie;


    @BindView(R.id.ytv_thumbnail)
    YouTubeThumbnailView ytvThumbnail;

    @BindView(R.id.icon_play_video)
    ImageView iconPlayVideo;

    @NonNull
    private final RxBus bus;

    Context context;


    public MovieItemViewHolder(ViewGroup parent, int resId, RxBus bus, Context context) {
        super(parent, resId);
        Log.i("WTF", "MovieItemViewHolder");
        this.bus = bus;
        this.context = context;
        ButterKnife.bind(this, itemView);
        iconPlayVideo.setOnClickListener(this);

    }

    @Override
    public void bindData(@Nullable MovieItem item) {
        Log.i("WTF", "bindData: position = " + getAdapterPosition());
        super.bindData(item);
        ytvThumbnail.initialize(TrailerActivity.YOUTUBE_API_KEY, this);
        mTvMovieRating.setText("" + item.getMovie().getMovieRating());
        mTvMovieTitle.setText(item.getMovie().getMovieTitle());
        mTvMovieId.setText("Id: " + item.getMovie().getMovieId());
        mTvMovieOverview.setText(item.getMovie().getMovieOverview());
        mTvMovieReleaseDate.setText("Release date: " + item.getMovie().getMovieReleaseDate());

    }


    @Override
    public void onClick(View v) {
//        Log.i("WTF", "item: id = " + item.getMovie().getMovieId());
        bus.send(new EventPlaybackVideo(item, MovieItemViewHolder.this));
    }


    @OnClick(R.id.ic_add_movie)
    public void onAddNewMovie() {
        bus.send(new EventAddNewMovie(MovieItemViewHolder.this));
    }

    @OnClick(R.id.ic_remove_movie)
    public void onRemoveMovie() {
        bus.send(new EventRemoveMovie(MovieItemViewHolder.this));
    }


    @Override
    public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
        Log.i("WTF", "onInitializationSuccess: position = " + getAdapterPosition() + " - id = " + item.getMovie().getMovieId());
        youTubeThumbnailLoader.setVideo(item.getMovie().getMovieId());
        youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailLoader.release();
            }

            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }
        });
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
        Log.i("WTF", "onInitializationFailure: " + youTubeInitializationResult);
    }


}
