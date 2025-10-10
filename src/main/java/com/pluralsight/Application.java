package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalTime;
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

                    break;

                case 2:

                    break;

                case 3:
                    Ledger();

                    break;

                case 4:
                    storeActive = false;

                    break;

                default:
                    System.out.println("Enter valid option");

            }
        }
    }

    public static void Ledger() {
        System.out.println("Ledger screen");

        Scanner scanner = new Scanner(System.in);
        HashMap<LocalDateTime, Transactions> transactions = getTransaction();

        System.out.println("1 - All");
        System.out.println("2 - Deposits");
        System.out.println("3 - Payments");
        System.out.println("4 - Reports");
        System.out.println("0 - Exit");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                displayAll();


                break;

            case 2:

                break;

            case 3:

                break;

            case 4:


                break;

            case 0:
                break;

            default:
                System.out.println("Enter valid option");

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

        System.out.println("We carry the following inventory: ");

        ArrayList<LocalDateTime> sortedKeys = new ArrayList<>(transactions.keySet());

        Collections.sort(sortedKeys);


        for (LocalDateTime key : sortedKeys) {

            System.out.println(transactions.get(key));

        }

    }



}
