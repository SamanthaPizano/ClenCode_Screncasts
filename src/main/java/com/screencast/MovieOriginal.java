package com.screencast;

public class MovieOriginal {
    public static final int CHILDREN = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private int priceCode;

    public MovieOriginal(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int code) {
        this.priceCode = code;
    }

    public String getTitle() {
        return title;
    }
}
