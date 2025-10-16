package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.pluralsight.Ledger.ledgerScreen;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);


        System.out.println();
        System.out.println("==========================================");
        System.out.println("Welcome to your personal Accounting Ledger");
        System.out.println("==========================================");
        System.out.println();

        System.out.println("Press Enter to continue");
        scanner.nextLine();

        homeScreen();
    }


    public static void homeScreen() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        boolean storeActive = true;


        while (storeActive) {
            System.out.println();
            System.out.println("     ================================     ");
            System.out.println("               Home Screen                ");
            System.out.println("     ================================     ");
            System.out.println();

            System.out.println("1 - Add Deposit");
            System.out.println("2 - Make Payment(Debit only)");
            System.out.println("3 - Ledger");
            System.out.println("4 - Exit");
            System.out.println();
            System.out.print("Enter Option #: ");


            try {

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

                        System.out.println("Are you sure you want to Exit?");
                        System.out.print("\nEnter Yes/No: ");
                        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                            storeActive = false;
                        }

                        break;

                    default:

                        System.out.println("Enter valid option");


                }
            } catch (Exception e) {
                System.out.println("Enter Valid Option");

            }
        }


    }


    public static void addDeposit() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);


        System.out.println();
        System.out.println("Enter Deposit info");

        System.out.println();
        System.out.print("Description: ");
        String description = scanner.nextLine().trim().toLowerCase();
        retryFunction(description);

        System.out.println();
        System.out.print("Name of Depositee: ");
        String vendor = scanner.nextLine().trim();
        retryFunction(vendor);

        System.out.println();
        System.out.print("Total Deposited (#): ");
        String price = scanner.next().trim();
        isNumber(price);
        retryFunction(price);

        System.out.println();
        scanner.nextLine();


        try {
            if (!price.isBlank()) {
                double money = Double.parseDouble(price);

                FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);

                BufferedWriter bufWriter = new BufferedWriter(fileWriter);
                bufWriter.newLine();
                String format = String.format(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "|" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "|" + "%s|%s|%.2f", description, vendor, money);
                bufWriter.write(format);
                bufWriter.flush();
                bufWriter.close();


            }
        } catch (Exception ignored) {


        }
    }


    public static void isNumber(String str) {
        try {

            Integer.parseInt(str);

        } catch (Exception e) {
            System.out.println();

            System.out.println("Non Number value Detected " + "\n=====INVALID PRICE=====");

            System.out.println("=====NUMBERS ONLY=====");

        }
    }


    public static void retryFunction(String entry) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        boolean notNamed = true;

        while (notNamed) {
            if (!entry.isBlank()) {

                System.out.println("Is " + entry.replaceAll("\\s", " ") + " correct?");
                System.out.print("Enter (yes/no): ");

                if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                    notNamed = false;
                } else {

                    System.out.println();
                    System.out.println("Retry entry?");
                    System.out.print("Enter (yes/no): ");

                    if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                        System.out.println();
                        System.out.print("ReEnter: ");
                        entry = scanner.next().trim();
                        scanner.nextLine();

                    } else {

                        System.out.println();
                        System.out.println("Exit to Homescreen?");
                        System.out.println("(Yes/No)");

                        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                            homeScreen();
                        }

                    }

                }
            }


        }
    }

    public static void makePayment() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);


        System.out.println();

        System.out.println("Enter Payment info");

        System.out.println();
        System.out.print("Description: ");
        String description = scanner.nextLine().trim().toLowerCase();
        retryFunction(description);

        System.out.println();
        System.out.print("Vendor Name: ");
        String vendor = scanner.nextLine().trim();
        retryFunction(vendor);

        System.out.println();
        System.out.print("Total deducted (#): ");
        String price = scanner.next().trim();
        isNumber(price);
        retryFunction(price);

        System.out.println();
        scanner.nextLine();


        try {
            if (!price.isBlank()) {
                double money = Double.parseDouble(price);

                FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);

                BufferedWriter bufWriter = new BufferedWriter(fileWriter);

                bufWriter.newLine();
                String format = String.format(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "|" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "|" + "%s|%s|-%.2f", description, vendor, money);
                bufWriter.write(format);
                bufWriter.flush();
                bufWriter.close();
            }
        } catch (Exception e) {


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


        } catch (Exception ignored) {

        }

        return transactions;
    }


}
