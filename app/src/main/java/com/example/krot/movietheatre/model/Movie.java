package com.example.krot.movietheatre.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krot on 1/17/18.
 */

public class Movie {

    /**
     * 1. c25GKl5VNeY
     * 2. JL7WZwWd7h4
     * 3. 1TSo4GBi1xI
     * 4. xU47nhruN-Q
     * 5. 6hB3S9bIaco
     * 6. sY1S34973zA
     * 7. njNnTDiLOd4
     * 8. ByXuk9QqQkk
     * 9. CGzKnyhYDQI
     * 10. gG22XNhtnoY
     * 11. 9O1Iy9od7-A
     * 12. 0pauY5cgRU4
     * 13. 7d_jQycdQGo
     * 14. SUXWAEX2jlg
     * 15. pAYEQP8gx3w
     * 16. s7EdQ4FqbhY
     * 17. NG3-GlvKPcg
     * 18. P3RHYNZplWg
     * 19. OXrcDonY-B8
     * 20. EXeTwQWrcwY
     *
     *
     * **/

    public static final List<String> fakeMovieIdList() {
        List<String> movieIdList = new ArrayList<>();
        movieIdList.add("c25GKl5VNeY");
        movieIdList.add("JL7WZwWd7h4");
        movieIdList.add("1TSo4GBi1xI");
        movieIdList.add("xU47nhruN-Q");
        movieIdList.add("6hB3S9bIaco");
        movieIdList.add("sY1S34973zA");
        movieIdList.add("njNnTDiLOd4");
        movieIdList.add("ByXuk9QqQkk");
        movieIdList.add("CGzKnyhYDQI");
        movieIdList.add("gG22XNhtnoY");
        movieIdList.add("9O1Iy9od7");
        movieIdList.add("0pauY5cgRU4");
        movieIdList.add("7d_jQycdQGo");
        movieIdList.add("SUXWAEX2jlg");
        movieIdList.add("pAYEQP8gx3w");
        movieIdList.add("s7EdQ4FqbhY");
        movieIdList.add("NG3-GlvKPcg");
        movieIdList.add("P3RHYNZplWg");
        movieIdList.add("EXeTwQWrcwY" );
        movieIdList.add("OXrcDonY-B8");

        return movieIdList;
    }

    @NonNull
    private String movieId;

    @SerializedName("title")
    @NonNull
    private String movieTitle;

    @SerializedName("original_title")
    @NonNull
    private String movieOriginalTitle;

    @SerializedName("overview")
    @NonNull
    private String movieOverview;

    @SerializedName("release_date")
    @NonNull
    private String movieReleaseDate;

    @SerializedName("vote_average")
    private float movieRating;


    public Movie(@NonNull String movieId, @NonNull String movieTitle, @NonNull String movieOriginalTitle, @NonNull String movieOverview, @NonNull String movieReleaseDate, float movieRating) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieOriginalTitle = movieOriginalTitle;
        this.movieOverview = movieOverview;
        this.movieReleaseDate = movieReleaseDate;
        this.movieRating = movieRating;
    }


    public String getMovieId() {
        return movieId;
    }

    @NonNull
    public String getMovieTitle() {
        return movieTitle;
    }

    @NonNull
    public String getMovieOriginalTitle() {
        return movieOriginalTitle;
    }

    @NonNull
    public String getMovieOverview() {
        return movieOverview;
    }

    @NonNull
    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public float getMovieRating() {
        return movieRating;
    }


    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setMovieTitle(@NonNull String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setMovieOriginalTitle(@NonNull String movieOriginalTitle) {
        this.movieOriginalTitle = movieOriginalTitle;
    }

    public void setMovieOverview(@NonNull String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public void setMovieReleaseDate(@NonNull String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public void setMovieRating(float movieRating) {
        this.movieRating = movieRating;
    }
}
