package com.project;

public class Vat {
    private String name;
    private double standard;

    public Vat(String name, double standard) {
        this.name = name;
        this.standard = standard;
    }

    public String getName() {
        return name;
    }

    public Double vatStandard() {
        return standard;
    }
}