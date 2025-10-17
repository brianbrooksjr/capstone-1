import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

static ArrayList<Transaction> transactions= new ArrayList<>();

public class Main {
    static ArrayList<Transaction> transactions= new ArrayList<>();
    static String filename = "src/main/resources/transaction.csv";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Accounting App");


        String choice;

        do {
            //Main menu Display
            System.out.println("Home Screen:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            choice = scanner.nextLine().toUpperCase();

            // User UI of the Accounting App
            //
            switch (choice) {
                case "D":
                    ledger.addDeposit(scanner);
                    break;
                case "P":
                    ledger.makePayment(scanner);
                    break;
                case "L":
                    ledger.displayLedger();
                    break;
            }
        } while (!choice.equals("X"));

        System.out.println("Exiting application.");
        scanner.close();
    }
}



// save the transactions to a collection and read the collection