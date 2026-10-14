package com.example.rental.entity;

import com.example.rental.web.Dates;

// Entite : represente une voiture de l'agence de location
public class Voiture {

    // les 3 champs demandes par le sujet : ils gardent leur nom anglais
    // car ils apparaissent tels quels dans le JSON
    private String plateNumber;
    private String brand;
    private double price;

    private boolean louee;
    private Dates datesLocation;

    public Voiture() {
    }

    public Voiture(String plateNumber, String brand, double price) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
        this.louee = false;
        this.datesLocation = null;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isLouee() {
        return louee;
    }

    public void setLouee(boolean louee) {
        this.louee = louee;
    }

    public Dates getDatesLocation() {
        return datesLocation;
    }

    public void setDatesLocation(Dates datesLocation) {
        this.datesLocation = datesLocation;
    }
}
