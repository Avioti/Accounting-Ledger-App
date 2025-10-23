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

import static com.pluralsight.Ledger.ledgerScreen;
import static com.pluralsight.Utilities.*;

public class Application {
    public static Scanner scanner = new Scanner(System.in);
    public static HashMap<LocalDateTime, Transactions> transactions = getTransaction();

    public static void main(String[] args)  {

        firstScreen();


    }

    public static void firstScreen()  {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("Welcome to your personal Accounting Ledger");
        System.out.println("==========================================");
        System.out.println();

        System.out.println("Press Enter to continue");
        scanner.nextLine();

        homeScreen();
    }


    public static void homeScreen()  {


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


            String option = scanner.nextLine().trim();
            if (ifNumber(option)) {
                int choice = Integer.parseInt(option);

                switch (choice) {
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
                        break;


                }
            } else {
                System.out.println();
                System.out.println("Enter valid option");
            }
        }


    }


    public static void addDeposit()  {


        System.out.println();
        System.out.println("Enter Deposit info");

        System.out.println();
        System.out.print("Description: ");
        String description = scanner.nextLine().trim().toLowerCase();
        description = retryFunction(description);


        System.out.println();
        System.out.print("Name of Depositee: ");
        String vendor = scanner.nextLine().trim();
        vendor = retryFunction(vendor);

        System.out.println();
        System.out.print("Total Deposited (#): ");
        String price = scanner.next().trim();
        isNumber(price);
        price = retryFunction(price);


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


    public static void makePayment()  {


        System.out.println();

        System.out.println("Enter Payment info");

        System.out.println();
        System.out.print("Description: ");
        String description = scanner.nextLine().trim().toLowerCase();
        description = retryFunction(description);

        System.out.println();
        System.out.print("Vendor Name: ");
        String vendor = scanner.nextLine().trim();
        vendor = retryFunction(vendor);

        System.out.println();
        System.out.print("Total deducted (#): ");
        String price = scanner.next().trim();
        isNumber(price);
        price = retryFunction(price);

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
