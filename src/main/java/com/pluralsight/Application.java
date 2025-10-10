package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        homeScreen();
    }


    public static void homeScreen() {

        System.out.println("Welcome to your personal Accounting Ledger");

        Scanner scanner = new Scanner(System.in);
        boolean storeActive = true;

        while (storeActive) {

            System.out.println("1 - Add Deposit");
            System.out.println("2 - Make Payment(Debit only)");
            System.out.println("3 - Ledger");
            System.out.println("4 - Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:

                    addDeposit();

                    break;

                case 2:

                    makePayment();

                    break;

                case 3:

                    ledgerScreen();

                    break;

                case 4:

                    storeActive = false;

                    break;

                default:

                    System.out.println("Enter valid option");

            }
        }
    }


    public static void addDeposit() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Deposit info");
        System.out.print("Service Description: ");
        String description = scanner.next().trim().toLowerCase();
        scanner.nextLine();
        System.out.print("Depositee: ");
        String vendor = scanner.next().trim();
        scanner.nextLine();
        System.out.print("Total Deposited: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        try {

            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);

            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            bufWriter.newLine();
            int invoiceNumber = 0;
            invoiceNumber++;

            String format = String.format(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "|" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "|" + "Invoice " + invoiceNumber + " paid " + "Service: %s|%s|%.2f", description, vendor, price);
            bufWriter.write(format);
            bufWriter.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    public static void makePayment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Deposit info");
        System.out.print("Description: ");
        String description = scanner.next().trim().toLowerCase();
        scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.next().trim();
        scanner.nextLine();
        System.out.print("Total deducted: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        try {

            FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);

            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            bufWriter.newLine();
            String format = String.format(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "|" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "|" + "%s|%s|-%.2f", description, vendor, price);
            bufWriter.write(format);
            bufWriter.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


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

    public static HashMap<LocalDateTime, Transactions> getTransaction() {
        HashMap<LocalDateTime, Transactions> transactions = new HashMap<>();

        try {
            BufferedReader buffReader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
            buffReader.readLine();
            String input;

            while ((input = buffReader.readLine()) != null) {
                String[] transactionsDisplay = input.split("\\|");


                LocalDate date = LocalDate.parse(transactionsDisplay[0]);

                LocalTime time = LocalTime.parse(transactionsDisplay[1]);

                String description = transactionsDisplay[2];

                String vendor = transactionsDisplay[3];

                double price = Double.parseDouble(transactionsDisplay[4]);


                Transactions item = new Transactions(date, time, description, vendor, price);

                LocalDateTime times = date.atTime(time);

                transactions.put(times, item);
            }
            buffReader.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactions;
    }


    public static void displayAll() {

        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        System.out.println("Transactions & Invoices: ");

        ArrayList<LocalDateTime> sortedKeys = new ArrayList<>(transactions.keySet());

        Collections.sort(sortedKeys);


        for (LocalDateTime key : sortedKeys) {

            System.out.println(transactions.get(key));

        }

    }

    public static void displayDeposit() {
        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        System.out.println("Deposits: ");

        ArrayList<LocalDateTime> sortedKeys = new ArrayList<>(transactions.keySet());

        Collections.sort(sortedKeys);


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

        Collections.sort(sortedKeys);


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


                    break;

                case 2:


                    break;


                case 3:


                    break;

                case 4:

                    break;

                case 5:

                    break;

                case 0:
                    reportsScreen = false;
                    break;

                default:
                    System.out.println("Enter valid option");

            }


        }


    }

    public static String monthToDate() {

        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        System.out.println("Month to Date: ");


        ArrayList<LocalDateTime> sortedKeys = new ArrayList<>(transactions.keySet());

        Collections.sort(sortedKeys);


        for (LocalDateTime key : sortedKeys) {
            Transactions d = transactions.get(key);


        }


        return monthToDate();
    }


}
