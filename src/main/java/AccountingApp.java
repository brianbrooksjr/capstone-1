import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountingApp {
    // List to store transactions
    static List<Transaction> transactions = new ArrayList<>();
    // File path for saving transactions
    static final String filename = "src/main/resources/transaction.csv"; // Adjust path if necessary
    // Scanner for user input
    static final Scanner scanner = new Scanner(System.in);
    // TODO: [2025-10-14]Home Screen
    public static void main(String[] args) {
        System.out.println("Welcome to the IMF Accounting App");
        loadTransactions(); // Load existing transactions from the CSV file

        boolean activeRunning = true; // Flag to control the main loop
        while (activeRunning) {
            System.out.println("Home:");
            System.out.println("Select option below: ");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String choice = scanner.nextLine().trim().toUpperCase(); // Get user's choice

            switch (choice) {
                case "D":
                    recordTransaction("deposit"); // Record a deposit transaction
                    break;
                case "P":
                    recordTransaction("payment"); // Record a payment transaction
                    break;
                case "L":
                    displayLedger(); // Display the ledger of transactions
                    break;
                case "X":
                    System.out.println("Session Ended. Log Out successful!");
                    activeRunning = false; // Exit the loop
                    break;
                default:
                    System.out.println("Invalid option, select an option shown."); // Handle invalid input
            }
        }
    }
   }