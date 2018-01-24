package com.example.krot.movietheatre;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;
import com.example.krot.movietheatre.adapter.MovieItemAdapter;
import com.example.krot.movietheatre.bus.EventAddNewMovie;
import com.example.krot.movietheatre.bus.EventMoveToTrailer;
import com.example.krot.movietheatre.bus.EventPlaybackVideo;
import com.example.krot.movietheatre.bus.EventRemoveMovie;
import com.example.krot.movietheatre.bus.RxBus;
import com.example.krot.movietheatre.model.Item;
import com.example.krot.movietheatre.model.Movie;
import com.example.krot.movietheatre.model.MovieItem;
import com.example.krot.movietheatre.presenter.MovieItemPresenterImpl;
import com.example.krot.movietheatre.viewholder.MovieItemViewHolder;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;

public class MainActivity extends YouTubeBaseActivity implements MovieItemContract.MovieItemView {

    private static final String MOVIE_CONTENT = "When the network of satellites designed to control the global climate starts to attack Earth, it's a race against the clock for its creator to uncover the real threat before a worldwide Geostorm wipes out everything and everyone.";

    @BindView(R.id.fab_navigate_to_next_screen)
    FloatingActionButton mFabNavigate;
    @BindView(R.id.movie_list_fragment_container)
    FrameLayout movieListFragmentContainer;
    @BindView(R.id.movie_recycler_view)
    RecyclerView mMovieRecyclerView;
    @BindView(R.id.loading_indicator)
    ProgressBar mLoadingIndicator;

    private MovieItemAdapter movieItemAdapter;
    private MovieItemPresenterImpl movieItemPresenter;
    private RxBus bus = new RxBus();
    private Disposable disposable;


    @OnClick(R.id.fab_navigate_to_next_screen)
    public void navigateToNextScreen() {

    }

    @BindView(R.id.icon_refresh)
    ImageView mIconRefresh;

    @OnClick(R.id.icon_refresh)
    public void doRefresh(){
        movieItemPresenter.loadData();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        movieItemPresenter = new MovieItemPresenterImpl(this);
        setUpMovieItemAdapter();
        movieItemPresenter.loadData();

    }


    @Override
    protected void onStart() {
        super.onStart();
        disposable = bus.toObserverable().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (o instanceof EventRemoveMovie) {
                    EventRemoveMovie eventRemoveMovie = (EventRemoveMovie) o;
                    movieItemPresenter.removeMovie(eventRemoveMovie.getMovieItemViewHolder().getAdapterPosition());
                } else if (o instanceof EventAddNewMovie) {
                    EventAddNewMovie eventAddNewMovie = (EventAddNewMovie) o;

                    //create clone Movie object
                    Movie randomMovie = new Movie("vIu85WQTPRc",
                            "Godzilla",
                            "Godzilla",
                            MOVIE_CONTENT,
                            "2017-10-20",
                            (float) 5.4);

                    movieItemPresenter.insertMovie(new MovieItem(randomMovie), eventAddNewMovie.getMovieItemViewHolder().getAdapterPosition());
                } else if (o instanceof EventPlaybackVideo) {
                    EventPlaybackVideo eventPlaybackVideo = (EventPlaybackVideo) o;
                    final MovieItem currentMovieItem = eventPlaybackVideo.getMovieItem();
                    MovieItemViewHolder movieItemViewHolder = eventPlaybackVideo.getMovieItemViewHolder();
//                    Log.i("WTF", "position = " + movieItemViewHolder.getAdapterPosition() + " - item: id = " + currentMovieItem.getMovie().getMovieId());
                    FragmentManager fm = getFragmentManager();
                    String tag = YouTubePlayerFragment.class.getSimpleName();
                    YouTubePlayerFragment playerFragment = (YouTubePlayerFragment) fm.findFragmentByTag(tag);
                    if (playerFragment == null) {
                        FragmentTransaction transaction = fm.beginTransaction();
                        playerFragment = YouTubePlayerFragment.newInstance();
                        transaction.add(R.id.movie_container, playerFragment, tag);
                        transaction.commit();
                    }

                    playerFragment.initialize(TrailerActivity.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            youTubePlayer.loadVideo(currentMovieItem.getMovie().getMovieId());
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        final LinearLayoutManager manager = (LinearLayoutManager) mMovieRecyclerView.getLayoutManager();
        mMovieRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Toast.makeText(MainActivity.this, "pos = " + manager.findFirstCompletelyVisibleItemPosition(), Toast.LENGTH_SHORT).show();
                Log.i("WTF", "" + manager.findFirstCompletelyVisibleItemPosition());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        disposable.dispose();
    }


    public void setUpMovieItemAdapter() {
        movieItemAdapter = new MovieItemAdapter(bus, this);
        mMovieRecyclerView.setAdapter(new AlphaInAnimationAdapter(movieItemAdapter));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mMovieRecyclerView.setLayoutManager(manager);
        mMovieRecyclerView.setItemAnimator(new SlideInRightAnimator());
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.simple_divider_decoration);
        mMovieRecyclerView.addItemDecoration(new DividerItemDecoration(drawable));
    }

    @Override
    public void updateMovieItemList(List<Item> newMovieItemList) {
        movieItemAdapter.updateListItem(newMovieItemList);
    }


    @Override
    public void showLoadingIndicator() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mLoadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getResources().getString(R.string.loading_error), Toast.LENGTH_LONG).show();
    }
}
