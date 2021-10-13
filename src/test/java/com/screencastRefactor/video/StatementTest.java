package com.screencastRefactor.video;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StatementTest {
    private StatementRefactor statement;
    private MovieRefactor newReleaseMovie1;
    private MovieRefactor newReleaseMovie2;
    private MovieRefactor childrenMovie;
    private MovieRefactor regularMovie1;
    private MovieRefactor regularMovie2;
    private MovieRefactor regularMovie3;

    @Before
    public void setUp() {
        statement = new StatementRefactor("Customer");
        newReleaseMovie1 = new NewReleaseMovieRefactor("New Release 1");
        newReleaseMovie2 = new NewReleaseMovieRefactor("New Release 2");
        childrenMovie = new ChildrenMovieRefactor("Children");
        regularMovie1 = new RegularMovieRefactor("Regular 1");
        regularMovie2 = new RegularMovieRefactor("Regular 2");
        regularMovie3 = new RegularMovieRefactor("Regular 3");
    }

    @Test
    public void testSingleNewReleaseStatementTotals() {
        statement.addRental(new RentalRefactor(newReleaseMovie1, 3));
        statement.generate();
        assertThat(statement.getTotal(), is(9.0));
        assertThat(statement.getFrequentRenterPoints(), is(2));
    }

    @Test
    public void testDualNewReleaseStatementTotals() {
        statement.addRental(new RentalRefactor(newReleaseMovie1, 3));
        statement.addRental(new RentalRefactor(newReleaseMovie2, 3));
        statement.generate();
        assertThat(statement.getTotal(), is(18.0));
        assertThat(statement.getFrequentRenterPoints(), is(4));
    }

    @Test
    public void testSingleChildrensStatementTotals() {
        statement.addRental(new RentalRefactor(childrenMovie, 3));
        statement.generate();
        assertThat(statement.getTotal(), is(1.5));
        assertThat(statement.getFrequentRenterPoints(), is(1));
    }

    @Test
    public void testMultipleRegularStatementTotals() {
        statement.addRental(new RentalRefactor(regularMovie1, 1));
        statement.addRental(new RentalRefactor(regularMovie2, 2));
        statement.addRental(new RentalRefactor(regularMovie3, 3));
        statement.generate();
        assertThat(statement.getTotal(), is(7.5));
        assertThat(statement.getFrequentRenterPoints(), is(3));
    }

    @Test
    public void testMultipleRegularStatementFormat() {
        statement.addRental(new RentalRefactor(regularMovie1, 1));
        statement.addRental(new RentalRefactor(regularMovie2, 2));
        statement.addRental(new RentalRefactor(regularMovie3, 3));
        assertEquals(
                "Rental Record for Customer\n" +
                        "\tRegular 1\t2.0\n" +
                        "\tRegular 2\t2.0\n" +
                        "\tRegular 3\t3.5\n" +
                        "You owed 7.5\n" +
                        "You earned 3 frequent renter points\n",
                statement.generate()
        );
    }
}
