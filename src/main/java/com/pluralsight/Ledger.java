package com.pluralsight;

import javax.sound.sampled.FloatControl;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import static com.pluralsight.Application.*;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class Ledger {
    public static void ledgerScreen() {
        boolean inLedger = true;

        while (inLedger) {

            System.out.println("Ledger screen");

            Scanner scanner = new Scanner(System.in);
            HashMap<LocalDateTime, Transactions> transactions = getTransaction();

            System.out.println("1 - All");
            System.out.println("2 - Deposits");
            System.out.println("3 - Payments");
            System.out.println("4 - Reports");
            System.out.println("0 - Exit to Home page");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

                case 1:

                    displayAll();

                    break;

                case 2:

                    displayDeposit();

                    break;

                case 3:

                    displayPayments();

                    break;

                case 4:

                    reports();

                    break;

                case 0:
                    inLedger = false;
                    break;

                default:
                    System.out.println("Enter valid option");

            }
        }

    }

    public static void displayAll() {

        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        System.out.println("Transactions & Invoices: ");

        ArrayList<LocalDateTime> sortedKeys = new ArrayList<>(transactions.keySet());

        Collections.sort(sortedKeys, Collections.reverseOrder());


        for (LocalDateTime key : sortedKeys) {

            System.out.println(transactions.get(key));

        }

    }

    public static void displayDeposit() {
        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        System.out.println("Deposits: ");

        ArrayList<LocalDateTime> sortedKeys = new ArrayList<>(transactions.keySet());

        Collections.sort(sortedKeys, Collections.reverseOrder());


        for (LocalDateTime key : sortedKeys) {
            Transactions d = transactions.get(key);

            if (d.getPrice() > 0) {
                System.out.println(d);
            }

        }

    }

    public static void displayPayments() {
        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        System.out.println("Payments: ");

        ArrayList<LocalDateTime> sortedKeys = new ArrayList<>(transactions.keySet());

        Collections.sort(sortedKeys, Collections.reverseOrder());


        for (LocalDateTime key : sortedKeys) {
            Transactions d = transactions.get(key);

            if (d.getPrice() < 0) {
                System.out.println(d);
            }

        }

    }

    public static void reports() {

        boolean reportsScreen = true;

        while (reportsScreen) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("1 - Month To Date");
            System.out.println("2 - Previous Month");
            System.out.println("3 - Year To Date");
            System.out.println("4 - Previous Year");
            System.out.println("5 - Search by Vendor");
            System.out.println("0 - Back to Ledger Page");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

                case 1:
                    monthToDate();


                    break;

                case 2:
                    previousMonth();


                    break;


                case 3:
                    yearToDate();


                    break;

                case 4:

                    break;

                case 5:
                    searchByVendor();

                    break;

                case 0:
                    reportsScreen = false;
                    break;

                default:
                    System.out.println("Enter valid option");

            }


        }


    }

    public static void monthToDate() {


        HashMap<LocalDateTime, Transactions> transactions = getTransaction();
        int year = LocalDate.now().getYear();
        LocalDateTime todayDate = LocalDateTime.now().plusDays(1);
        LocalDateTime option = todayDate.withDayOfMonth(1);
        for (LocalDateTime key : transactions.keySet()) {
            Transactions value = transactions.get(key);

            if (key.isAfter(option) && !key.isAfter(todayDate)) {
                System.out.println(value);
            }


        }


    }

    public static void previousMonth() {
        HashMap<LocalDateTime, Transactions> transactions = getTransaction();
        LocalDate todayDate = LocalDate.now();
        LocalDate firstOfTheMonth = todayDate.withDayOfMonth(1);
        LocalDate lastMonth = firstOfTheMonth.minusMonths(1);
        LocalDate lastMonthEnd = firstOfTheMonth.minusDays(1);

        for (LocalDateTime key : transactions.keySet()) {
            Transactions value = transactions.get(key);
            LocalDate keyDate = key.toLocalDate();
            if (!keyDate.isAfter(lastMonthEnd) && !keyDate.isBefore(lastMonth)){
                System.out.println(value);

            }


        }

    }

    public static void yearToDate() {
        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        int year = LocalDate.now().getYear();
        LocalDate todayDate = LocalDate.now();
        LocalDate firstDay = LocalDate.of(year, 1, 1);
        for (Transactions key : transactions.values()) {


            if (key.getDate().isEqual(todayDate) && key.getDate().isAfter(firstDay)) {
                System.out.println(key);
            }


        }
    }

    public static void searchByVendor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Vendor/Depositee Name");
        HashMap<LocalDateTime, Transactions> transactions = getTransaction();
        String option = scanner.nextLine().replaceAll("\\s", "").trim().toLowerCase();
        for (Transactions key : transactions.values()) {

            if (key.getVendorName().trim().toLowerCase().contains(option)) {
                System.out.println(key);
            }


        }

    }


}
