package com.screencastRefactor.video;

public class RegularMovieRefactor extends MovieRefactor {
    public RegularMovieRefactor(String title) {
        super(title);
    }

    @Override
    double determineAmount(int daysRented) {
        double rentalAmount = 2;
        if(daysRented > 2 )
            rentalAmount += (daysRented - 2 ) * 1.5;
        return rentalAmount;
    }

    @Override
    int determineFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
