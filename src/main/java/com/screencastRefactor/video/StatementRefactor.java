package com.screencastRefactor.video;

import com.screencastRefactor.video.RentalRefactor;

import java.util.ArrayList;
import java.util.List;

public class StatementRefactor {
    private String customerName;
    private List<RentalRefactor> rentals = new ArrayList<RentalRefactor>();
    private double totalAmount;
    private int frequentRenterPoint;

    public StatementRefactor(String customerName){
        this.customerName = customerName;
    }

    public void addRental(RentalRefactor rental){
        rentals.add(rental);
    }

    public String getCustomerName(){
        return customerName;
    }

    public String generate(){
        clearTotals();
        String statementText = header();
        statementText += rentalLines();
        statementText += footer();
        return statementText;
    }
    
    private void clearTotals() {
        totalAmount = 0;
        frequentRenterPoint = 0;
    }

    private String header() {
        return String.format("Rental Record for %s\n", customerName);
    }

    private String rentalLines() {
        String rentalLines = "";
        for(RentalRefactor rental : rentals)
            rentalLines += rentalLine(rental);
        return rentalLines;
    }

    private String rentalLine(RentalRefactor rental) {
        double rentalAmount = rental.determineAmount();
        frequentRenterPoint += rental.determineFrequentRenterPoints();
        totalAmount += rentalAmount;
        return formatRentalLine(rental, rentalAmount);
    }

    private String formatRentalLine(RentalRefactor rental, double rentalAmount) {
        return String.format("\t%s\t%.1f\n", rental.getTitle(),rentalAmount);
    }

    private String footer() {
        return String.format(
                "You owed %.1f\n" +
                        "You earned %d frequent renter points\n",
                totalAmount, frequentRenterPoint);
    }
    
    public double getTotal(){
        return totalAmount;
    }

    public int getFrequentRenterPoints(){
        return frequentRenterPoint;
    }
}
