package com.project;

import java.util.Date;

public class Vat {
    private String countryName;
    private double standardRate;
    private Date effectiveFromDate;

    public Vat(String countryName, double standardRate, Date effectiveFromDate) {
        this.countryName = countryName;
        this.standardRate = standardRate;
        this.effectiveFromDate = effectiveFromDate;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public Double getStandardRate() {
        return this.standardRate;
    }

    public Date getEffectiveFromDate() {
        return this.effectiveFromDate;
    }

    public void printCountryVatDate() {
        System.out.println("Country: " + this.countryName + " VAT: " + this.standardRate + " Effective from: " + this.effectiveFromDate);
    }
}