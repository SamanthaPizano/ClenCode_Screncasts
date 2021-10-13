package com.screencastRefactor.video;

import com.screencastRefactor.video.MovieRefactor;

public class RentalRefactor {
    private MovieRefactor movie;
    private int daysRented;

    public RentalRefactor(MovieRefactor movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public MovieRefactor getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public String getTitle() {
        return movie.getTitle();
    }

    double determineAmount() {
        return movie.determineAmount(daysRented);
    }

    int determineFrequentRenterPoints() {
        return movie.determineFrequentRenterPoints(daysRented);
    }
}
