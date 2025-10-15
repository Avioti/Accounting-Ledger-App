package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import static com.pluralsight.Application.*;


public class Ledger {
    public static void ledgerScreen() {


        boolean inLedger = true;

        while (inLedger) {

            System.out.println();

            System.out.println("     ================================     ");
            System.out.println("              Ledger screen               ");
            System.out.println("     ================================     ");

            System.out.println();

            Scanner scanner = new Scanner(System.in);


            System.out.println("1 - All");
            System.out.println("2 - Deposits");
            System.out.println("3 - Payments");
            System.out.println("4 - Reports");
            System.out.println("0 - Exit to Home page");
            System.out.println();
            System.out.print("Enter Option #: ");

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


        sortedKeys.sort(Collections.reverseOrder());


        for (LocalDateTime key : sortedKeys) {

            System.out.println(transactions.get(key));

        }

    }

    public static void displayDeposit() {


        HashMap<LocalDateTime, Transactions> transactions = getTransaction();


        System.out.println("Deposits: ");


        ArrayList<LocalDateTime> sortedKeys = new ArrayList<>(transactions.keySet());


        sortedKeys.sort(Collections.reverseOrder());


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


        sortedKeys.sort(Collections.reverseOrder());


        for (LocalDateTime key : sortedKeys) {
            Transactions d = transactions.get(key);

            if (d.getPrice() < 0) {
                System.out.println(d);
            }

        }

    }

    public static void reports() {

        System.out.println();

        System.out.println("     ================================     ");
        System.out.println("             Reports Screen                ");
        System.out.println("     ================================     ");

        System.out.println();

        boolean reportsScreen = true;


        while (reportsScreen) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("1 - Month To Date");
            System.out.println("2 - Previous Month");
            System.out.println("3 - Year To Date");
            System.out.println("4 - Previous Year");
            System.out.println("5 - Search by Vendor");
            System.out.println("6 - Search with either Start/End Date, Desc, Vendor, Amount");
            System.out.println("0 - Back to Ledger Page");
            System.out.println();
            System.out.print("Enter Option #: ");

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
                    previousYear();

                    break;

                case 5:
                    searchByVendor();

                    break;

                case 6:
                    searchFunction();
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

            if (!keyDate.isAfter(lastMonthEnd) && !keyDate.isBefore(lastMonth)) {
                System.out.println(value);

            }


        }

    }

    public static void yearToDate() {


        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        int year = LocalDate.now().getYear();
        LocalDate todayDate = LocalDate.now();
        LocalDate firstDay = LocalDate.of(year, 1, 1);


        ArrayList<LocalDateTime> sortedKeys = new ArrayList<>(transactions.keySet());


        sortedKeys.sort(Collections.reverseOrder());


        for (LocalDateTime key : sortedKeys) {
            Transactions sortedKey = transactions.get(key);

            if (!sortedKey.getDate().isAfter(todayDate) && sortedKey.getDate().isAfter(firstDay)) {
                System.out.println(sortedKey);
            }

        }
    }

    public static void previousYear() {


        HashMap<LocalDateTime, Transactions> transactions = getTransaction();


        int year = LocalDate.now().getYear();
        int previousYear = year - 1;


        LocalDate firstDay = LocalDate.of(previousYear, 1, 1);

        LocalDate lastDay = LocalDate.of(previousYear, 12, 31);

        ArrayList<LocalDateTime> sortedKeys = new ArrayList<>(transactions.keySet());


        sortedKeys.sort(Collections.reverseOrder());


        for (LocalDateTime key : sortedKeys) {
            Transactions sortedKey = transactions.get(key);

            if (!sortedKey.getDate().isAfter(lastDay) && sortedKey.getDate().isAfter(firstDay)) {
                System.out.println(sortedKey);
            }

        }
    }

    public static void searchByVendor() {


        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter the Vendor/Depositee Name");


        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        String option = scanner.nextLine();


        for (Transactions key : transactions.values()) {
            boolean matches = true;

            if (!option.equalsIgnoreCase("")) {
                try{
                    if (!key.getVendorName().trim().toLowerCase().replaceAll("\\s", "").contains(option.toLowerCase().replaceAll("\\s", "").trim())) {
                        matches = false;
                    }
                }catch (Exception e){

                }

            }
            if (matches){
                System.out.println(key);
            }


        }

    }

    public static void searchFunction() {


        Scanner scanner = new Scanner(System.in);


        HashMap<LocalDateTime, Transactions> transactions = getTransaction();


        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();


        System.out.print("Enter End Date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();


        System.out.print("Description: ");
        String description = scanner.nextLine();


        System.out.print("Vendor/Depositee: ");
        String vendor = scanner.nextLine();


        System.out.print("Enter Price : ");
        String price = scanner.nextLine();


        for (Transactions key : transactions.values()) {


            boolean matches = true;


            LocalDate keyDate = key.getDate();


            if (!startDate.equalsIgnoreCase("")) {
                try{
                    LocalDate start = LocalDate.parse(startDate);

                    if (keyDate.isBefore(start)) {
                        matches = false;
                    }
                }catch (Exception e){
                    
                }

            }


            if (!endDate.equalsIgnoreCase("")) {
                try{
                    LocalDate end = LocalDate.parse(endDate);

                    if (keyDate.isAfter(end)) {
                        matches = false;
                    }
                }catch (Exception e){
                    
                }

            }


            if (!vendor.equalsIgnoreCase("")) {
                try{
                    if (!key.getVendorName().trim().toLowerCase().replaceAll("\\s", "").contains(vendor.toLowerCase().replaceAll("\\s", "").trim())) {
                        matches = false;
                    }
                }catch (Exception e){
                    
                }

            }


            if (!description.equalsIgnoreCase("")) {
                try{
                    if (!key.getdescription().trim().toLowerCase().replaceAll("\\s", "").contains(description.toLowerCase().replaceAll("\\s", "").trim())) {
                        matches = false;
                    }
                }catch (Exception e){
                    
                }

            }


            if (!price.equalsIgnoreCase("")) {
                try{
                    double money = Double.parseDouble(price);

                    if (Double.compare(key.getPrice(), money) != 0) {
                        matches = false;
                    }
                }catch (Exception e){
                    
                }

            }
            if (matches) {

                System.out.println(key);
            }


        }

    }


}
