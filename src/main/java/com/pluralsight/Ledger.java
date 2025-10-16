package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import static com.pluralsight.Application.*;


public class Ledger {
    public static void ledgerScreen() throws InterruptedException {


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

    public static void displayAll() throws InterruptedException {


        HashMap<LocalDateTime, Transactions> transactions = getTransaction();


        System.out.println();


        System.out.println("Transactions & Invoices: ");


        ArrayList<LocalDateTime> sortedKeys = new ArrayList<>(transactions.keySet());


        sortedKeys.sort(Collections.reverseOrder());


        for (LocalDateTime key : sortedKeys) {

            System.out.println(transactions.get(key));
            Thread.sleep(100);

        }

    }

    public static void displayDeposit() throws InterruptedException {


        HashMap<LocalDateTime, Transactions> transactions = getTransaction();


        System.out.println();


        System.out.println("Deposits: ");


        ArrayList<LocalDateTime> sortedKeys = new ArrayList<>(transactions.keySet());


        sortedKeys.sort(Collections.reverseOrder());


        for (LocalDateTime key : sortedKeys) {
            Transactions d = transactions.get(key);

            if (d.getPrice() > 0) {
                System.out.println(d);
            }
            Thread.sleep(100);
        }

    }

    public static void displayPayments() throws InterruptedException {


        HashMap<LocalDateTime, Transactions> transactions = getTransaction();


        System.out.println();


        System.out.println("Payments: ");


        ArrayList<LocalDateTime> sortedKeys = new ArrayList<>(transactions.keySet());


        sortedKeys.sort(Collections.reverseOrder());


        for (LocalDateTime key : sortedKeys) {
            Transactions d = transactions.get(key);

            if (d.getPrice() < 0) {
                System.out.println(d);
            }
            Thread.sleep(100);
        }

    }

    public static void reports() throws InterruptedException {

        System.out.println();

        System.out.println("     ================================     ");
        System.out.println("             Reports Screen                ");
        System.out.println("     ================================     ");



        boolean reportsScreen = true;


        while (reportsScreen) {

            Scanner scanner = new Scanner(System.in);

            System.out.println();

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

    public static void monthToDate() throws InterruptedException {

        System.out.println();

        System.out.println("Month-To-Date");

        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        LocalDateTime todayDate = LocalDateTime.now().plusDays(1);
        LocalDateTime option = todayDate.withDayOfMonth(1);

        for (LocalDateTime key : transactions.keySet()) {
            Transactions value = transactions.get(key);

            if (key.isAfter(option) && !key.isAfter(todayDate)) {
                System.out.println(value);
            }

            Thread.sleep(75);
        }


    }

    public static void previousMonth() throws InterruptedException {

        System.out.println();

        System.out.println("Previous-Month");

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
            Thread.sleep(75);

        }

    }

    public static void yearToDate() throws InterruptedException {

        System.out.println();

        System.out.println("Year-To-Date");

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
            Thread.sleep(75);
        }
    }

    public static void previousYear() throws InterruptedException {

        System.out.println();

        System.out.println("Previous-year");

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
            Thread.sleep(75);
        }
    }

    public static void searchByVendor() throws InterruptedException {


        Scanner scanner = new Scanner(System.in);

        System.out.println();

        System.out.println("Enter the Vendor/Depositee Name");
        System.out.print("Enter: ");


        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        String option = scanner.nextLine();
        retryReportsFunction(option);

        System.out.println();


        for (Transactions key : transactions.values()) {
            boolean matches = true;

            if (!option.isBlank()) {
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

    public static void searchFunction() throws InterruptedException {


        Scanner scanner = new Scanner(System.in);

        System.out.println();

        HashMap<LocalDateTime, Transactions> transactions = getTransaction();


        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        retryReportsFunction(startDate);
        System.out.println();


        System.out.print("Enter End Date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        retryReportsFunction(endDate);
        System.out.println();

        System.out.print("Description: ");
        String description = scanner.nextLine();
        retryReportsFunction(description);
        System.out.println();

        System.out.print("Vendor/Depositee: ");
        String vendor = scanner.nextLine();
        retryReportsFunction(vendor);
        System.out.println();

        System.out.print("Enter Price : ");
        String price = scanner.nextLine();
        isNumber(price);
        retryReportsFunction(price);

        System.out.println();


        for (Transactions key : transactions.values()) {


            boolean matches = true;


            LocalDate keyDate = key.getDate();


            if (!startDate.isBlank()) {

                try{
                    LocalDate start = LocalDate.parse(startDate);

                    if (keyDate.isBefore(start)) {
                        matches = false;
                    }
                }catch (Exception e){

                }

            }


            if (!endDate.isBlank()) {
              
                try{
                    LocalDate end = LocalDate.parse(endDate);

                    if (keyDate.isAfter(end)) {
                        matches = false;
                    }
                }catch (Exception e){

                }

            }


            if (!vendor.isBlank()) {
              
                try{
                    if (!key.getVendorName().trim().toLowerCase().replaceAll("\\s", "").contains(vendor.toLowerCase().replaceAll("\\s", "").trim())) {
                        matches = false;
                    }
                }catch (Exception e){

                }

            }


            if (!description.isBlank()) {
              
                try{
                    if (!key.getdescription().trim().toLowerCase().replaceAll("\\s", "").contains(description.toLowerCase().replaceAll("\\s", "").trim())) {
                        matches = false;
                    }
                }catch (Exception e){

                }

            }


            if (!price.isBlank()) {
              
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

    public static void retryReportsFunction(String entry) throws InterruptedException {
        if(!entry.isBlank()){
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        boolean notNamed = true;

        while(notNamed){
            if(!entry.isBlank()){
                System.out.println("Is " + entry.replaceAll("\\s", " ") + " correct?");
                System.out.print("Enter (yes/no): ");
                if(scanner.nextLine().trim().equalsIgnoreCase("yes")){
                    notNamed = false;
                }else{
                    System.out.println();
                    System.out.println("Retry entry?");
                    System.out.print("Enter (yes/no): ");
                    if(scanner.nextLine().trim().equalsIgnoreCase("yes")){
                        System.out.println();
                        System.out.print("ReEnter: ");
                        entry = scanner.next().trim();
                        scanner.nextLine();
                    }else{
                        System.out.println();
                        System.out.println("Exit to Reports screen?");
                        System.out.println("(Yes/No)");
                        if(scanner.nextLine().trim().equalsIgnoreCase("yes")){
                            reports();
                        }
                    }

                }
            }



        }}
    }


}
