package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
    private LocalDateTime date;
    private String purchaser;
    private double amount;
    private int invoiceLine;

    public Invoice(LocalDateTime date, int invoiceLine, String purchaser, double amount) {
        this.date = date;
        this.invoiceLine = invoiceLine;
        this.purchaser = purchaser;
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getInvoiceLine() {
        return invoiceLine;
    }

    public void setInvoiceLine(int invoiceLine) {
        this.invoiceLine = invoiceLine;
    }

    public String getpurchaser() {
        return purchaser;
    }

    public void setpurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public double getamount() {
        return amount;
    }

    public void setamount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss")) + "|" +"Invoice  "+ invoiceLine + " paid"+ "|" + purchaser + "|" + amount ;
    }
}
