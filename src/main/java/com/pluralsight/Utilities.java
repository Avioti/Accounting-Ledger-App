package com.pluralsight;

import static com.pluralsight.Application.scanner;

public class Utilities {

    public static String retryFunction(String entry)  {


        System.out.println();
        String newEntry = entry.trim();
        while (true) {
            if (entry.isBlank() || newEntry.isBlank()) {

                System.out.println();
                System.out.println("Entry Cannot be Blank. Please Retry");
                System.out.print("Enter: ");
                newEntry = scanner.nextLine().trim();
                continue;

            }

            System.out.println("Is " + newEntry.replaceAll("\\s", " ") + " correct?");
            System.out.print("Enter (yes/no): ");

            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase("yes")) {
                return newEntry;
            } else {
                System.out.println();
                System.out.println("Retry entry");
                System.out.println();
                System.out.print("ReEnter: ");
                newEntry = scanner.nextLine().trim();

            }


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

    public static boolean ifNumber(String str) {
        try {

            Integer.parseInt(str);
            return true;

        } catch (Exception e) {
            return false;

        }
    }

    public static void sleep(int entry){
        try{
            Thread.sleep(entry);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
