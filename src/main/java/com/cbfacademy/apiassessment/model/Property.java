package com.cbfacademy.apiassessment.model;

import java.math.BigDecimal;

public class Property {
    private Address address;
    private int noOfBedrooms;
    private double priceBySqrFoot;
//    private LocalDate purchaseDate;
    private BigDecimal purchasePrice;


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getNoOfBedrooms() {
        return noOfBedrooms;
    }

    public void setNoOfBedrooms(int noOfBedrooms) {
        this.noOfBedrooms = noOfBedrooms;
    }

    public double getPriceBySqrFoot() {
        return priceBySqrFoot;
    }

    public void setPriceBySqrFoot(double priceBySqrFoot) {
        this.priceBySqrFoot = priceBySqrFoot;
    }

//    public LocalDate getPurchaseDate() {
//        return purchaseDate;
//    }
//
//    public void setPurchaseDate(LocalDate purchaseDate) {
//        this.purchaseDate = purchaseDate;
//    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
}
