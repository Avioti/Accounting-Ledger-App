package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


    //My Transactions class to handle the simple Setters and getters for My application
    //This class also includes the constructor that is used within my get transaction method in the application class

public class Transactions {
    //Variables/Objects

    private LocalDate date;
    private LocalTime time;
    private String description, vendorName;
    private double price;

    //Setters and Getters

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //Transactions Constructor
    public Transactions(LocalDate date, LocalTime time, String description, String vendorName, double price) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendorName = vendorName;
        this.price = price;
    }

    //This override method is so whenever the key is read it is formatted like this
    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "|" + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "|" + description + "|" + vendorName + "|" + price;
    }
}
