package com.screencast;

import java.util.Enumeration;
import java.util.Vector;

public class CustomerOriginal {
    public CustomerOriginal( String name ){
        this.name = name;
    }

    public void addRental( RentalOriginal rental ){
        rentals.addElement( rental );
    }

    public String getName(){
        return name;
    }

    public String statement(){
        double totalAmount = 0;
        int frequentRenterPoint = 0;
        Enumeration rentals = this.rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while(rentals.hasMoreElements()) {
            double thisAmount = 0;
            RentalOriginal each = (RentalOriginal)rentals.nextElement();

            //determines the amount for each lines
            switch(each.getMovie().getPriceCode() ){
                case MovieOriginal.REGULAR:
                    thisAmount += 2;
                    if( each.getDaysRented() > 2 )
                        thisAmount += ( each.getDaysRented() - 2 ) * 1.5;
                    break;
                case MovieOriginal.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case MovieOriginal.CHILDREN:
                    thisAmount += 1.5;
                    if( each.getDaysRented() > 3 )
                        thisAmount += ( each.getDaysRented() - 3 ) * 1.5;
                    break;
            }

            frequentRenterPoint++;

            if( each.getMovie().getPriceCode() == MovieOriginal.NEW_RELEASE )
                frequentRenterPoint++;

            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }

        result += "You owed " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoint) + " frequent renter points\n";

        return result;
    }

    private String name;
    private Vector rentals = new Vector();
}
