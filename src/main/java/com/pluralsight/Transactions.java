package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transactions {
    private LocalDateTime date;
    private String description, vendorName;
    private double price;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    public Transactions(String vendorName, LocalDateTime date, String description, double price) {
        this.vendorName = vendorName;
        this.date = date;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss")) + "|" + description + "|" + vendorName + "|" + price ;
    }
}
