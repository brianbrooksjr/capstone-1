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
    // TODO: [2025-10-16] Check for invalid data formats in the CSV before parsing to prevent runtime exceptions and improve error handling.
    // Load transactions from the CSV file
    private static void loadTransactions() {
        File file = new File(filename);
        if (!file.exists()) return; // Exit if the file does not exist

        // Read from the transactions CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|"); // Split line by delimiter '|'
                if (parts.length == 5) {
                    LocalDateTime dateTime = LocalDateTime.parse(parts[0] + "T" + parts[1]); // Parse date and time
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]); // Parse the amount
                    transactions.add(new Transaction(dateTime, description, vendor, amount)); // Add transaction to the list
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading transactions: " + e.getMessage()); // Handle IO exceptions
        }
    }
    // TODO: [2025-10-15] Implement validation for correct format,
    // Save a transaction to the CSV file
    private static void saveTransaction(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(transaction.toString()); // Write transaction as a string
            writer.newLine(); // Add a new line after the transaction
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage()); // Handle IO exceptions
        }
    }
    // TODO: [2025-10-15] Validate user input for description, vendor, and amount before processing.
    // Record a new transaction (deposit or payment)
    private static void recordTransaction(String type) {
        LocalDateTime now = LocalDateTime.now(); // Use the current date and time

        System.out.print("Item Description: ");
        String description = scanner.nextLine().trim(); // Get item description from user

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine().trim(); // Get vendor name from user

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim()); // Get amount from user

        // If payment, make the amount negative
        if (type.equalsIgnoreCase("payment")) {
            amount = -Math.abs(amount);
        }

        // Create a new transaction object using the current date and time
        Transaction transaction = new Transaction(now, description, vendor, amount);
        transactions.add(transaction);  // Add to the transaction list
        saveTransaction(transaction);    // Save to the CSV file
        System.out.println("Transaction recorded successfully: " + transaction); // Confirmation message
    }
    // TODO: [2025-10-16] Implement sorting options for transactions to enhance user experience.
    // Display the ledger of transactions
