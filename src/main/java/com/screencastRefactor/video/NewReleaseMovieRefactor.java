package com.screencastRefactor.video;

public class NewReleaseMovieRefactor extends MovieRefactor {
    public NewReleaseMovieRefactor(String title) {
        super(title);
    }

    @Override
    double determineAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int determineFrequentRenterPoints(int daysRented) {
        return (daysRented > 1) ? 2 : 1;
    }
}
