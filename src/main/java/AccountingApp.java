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
    private static void displayLedger() {
        boolean isViewing = true; // Flag for viewing transactions
        // TODO: [2025-10-14] Ledger Screen
        while (isViewing) {
            System.out.println("\nLedger Screen:");
            System.out.println("Select an option to filter:");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("R) Reports");
            System.out.println("0) Back to Home");

            String choice = scanner.nextLine().trim().toUpperCase(); // Get user's filtering choice
            switch (choice) {
                case "A":
                    displayAllTransactions(); // Display all transactions
                    break;
                case "D":
                    displayDeposits(); // Display only deposits
                    break;
                case "P":
                    displayPayments(); // Display only payments
                    break;
                case "R":
                    displayReports(); // Navigate to reports menu
                    break;
                case "0":
                    isViewing = false; // Exit viewing mode and go back to home
                    break;
                default:
                    System.out.println("Invalid option, please try again."); // Handle invalid input
            }
        }
    }
    // TODO: [2025-10-15] Enhance transaction display by formatting output and including summary statistics.
    // Display all transactions
    private static void displayAllTransactions() {
        // Sort transactions by date in descending order
        transactions.sort((t1, t2) -> t2.getDateTime().compareTo(t1.getDateTime()));

        // Display each transaction
        for (Transaction transaction : transactions) {
            System.out.println(transaction); // Print transaction details
        }
    }
    // TODO: [2025-10-15] Add filtering options for date range and other criteria to improve deposit display.
    // Display only deposits
    private static void displayDeposits() {
        // Filter for deposits and display them
        transactions.stream()
                .filter(t -> t.getAmount() > 0) // Select only deposits
                .forEach(System.out::println); // Print each deposit
    }
    // TODO: [2025-10-16] P) Payments - Filter and display only payments (negative entries)
    // Display only payments
    private static void displayPayments() {
        // Filter for payments and display them
        transactions.stream()
                .filter(t -> t.getAmount() < 0) // Select only payments
                .forEach(System.out::println); // Print each payment
    }

    // TODO: [2025-10-14] R) Reports - Create a new screen for reports
    // Display the reports menu
    private static void displayReports() {
        boolean isReporting = true; // Flag for viewing reports

        while (isReporting) {
            System.out.println("\nReports Menu:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back to Ledger");

            String choice = scanner.nextLine().trim(); // Get user's choice for reports

            switch (choice) {

                case "1":
                    reportMonthToDate(); // Display transactions for the current month
                    break;
                case "2":
                    reportPreviousMonth(); // Display transactions for the previous month
                    break;
                case "3":
                    reportYearToDate(); // Display transactions for the current year
                    break;
                case "4":
                    reportPreviousYear(); // Display transactions for the previous year
                    break;
                case "5":
                    searchByVendor(); // Search transactions by vendor name
                    break;
                case "0":
                    isReporting = false; // Exit reports menu and go back to ledger
                    break;
                default:
                    System.out.println("Invalid option, please try again."); // Handle invalid input
            }
        }
    }
    // TODO: [2025-10-15] 1) Month To Date - Generate report for the current month
    // Display transactions for the current month
    private static void reportMonthToDate() {
        LocalDateTime now = LocalDateTime.now();
        transactions.stream()
                .filter(t -> t.getDateTime().getMonth() == now.getMonth() &&
                        t.getDateTime().getYear() == now.getYear()) // Filter transactions by current month and year
                .forEach(System.out::println); // Print each transaction
    }
    // TODO: [2025-10-16] 2) Previous Month - Generate report for the last month
    // Display transactions for the previous month
    private static void reportPreviousMonth() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstDayOfLastMonth = now.minusMonths(1).withDayOfMonth(1); // Get first day of last month
        LocalDateTime lastDayOfLastMonth = now.minusMonths(1)
                .withDayOfMonth(firstDayOfLastMonth.toLocalDate().lengthOfMonth()); // Get last day of last month

        transactions.stream()
                .filter(t -> !t.getDateTime().isBefore(firstDayOfLastMonth) &&
                        !t.getDateTime().isAfter(lastDayOfLastMonth)) // Filter transactions within last month
                .forEach(System.out::println); // Print each transaction in the previous month
    }
    // TODO: [2025-10-16] 3) Year To Date - Generate report for the current year
    // Display transactions for the current year
    private static void reportYearToDate() {
        int year = LocalDateTime.now().getYear();
        transactions.stream()
                .filter(t -> t.getDateTime().getYear() == year) // Filter transactions for the current year
                .forEach(System.out::println); // Print each transaction
    }
    // TODO: [2025-10-16] 4) Previous Year - Generate report for the previous year
    // Display transactions for the previous year
    private static void reportPreviousYear() {
        int lastYear = LocalDateTime.now().getYear() - 1;
        transactions.stream()
                .filter(t -> t.getDateTime().getYear() == lastYear) // Filter transactions for the previous year
                .forEach(System.out::println); // Print each transaction
    }
    // TODO: [2025-10-16] 5) Search by Vendor - Prompt for vendor name and display entries
    // Search for transactions by vendor name
    private static void searchByVendor() {
        System.out.print("Enter Vendor Name: ");
        String vendorName = scanner.nextLine().trim(); // Get vendor name from user

        transactions.stream()
                .filter(t -> t.getVendor().equalsIgnoreCase(vendorName)) // Filter transactions by vendor
                .forEach(System.out::println); // Print each matching transaction
    }
}
