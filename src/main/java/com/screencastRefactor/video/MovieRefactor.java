package com.screencastRefactor.video;

public abstract class MovieRefactor {
    private String title;

    public MovieRefactor(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    abstract double determineAmount(int daysRented);

    abstract int determineFrequentRenterPoints(int daysRented);
}
