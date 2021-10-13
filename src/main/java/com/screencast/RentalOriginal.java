package com.screencast;

public class RentalOriginal {
    private MovieOriginal movie;
    private int daysRented;

    public RentalOriginal(MovieOriginal movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public MovieOriginal getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
