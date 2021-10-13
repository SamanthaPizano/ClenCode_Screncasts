package com.screencastRefactor.video;

public class ChildrenMovieRefactor extends MovieRefactor {
    public ChildrenMovieRefactor(String title) {
        super(title);
    }

    @Override
    double determineAmount(int daysRented) {
        double rentalAmount = 1.5;
        if(daysRented > 3 )
            rentalAmount += (daysRented - 3 ) * 1.5;
        return rentalAmount;
    }

    @Override
    int determineFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
