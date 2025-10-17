import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class HomeScreen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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



    public void makePayment(Scanner scanner){
        // Payment UI
        System.out.print("Enter Payment amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter payment description: ");
        String description = scanner.next();

        //Payment Information Format
        transactions.add(new Transaction(-amount,description, "Payment"));
        System.out.println("Payment added ");
    }

    public void displayLedger() {
        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public void displayDeposits(){
        transactions.stream()
                .filter(t -> t.getAmount() < 0)
    }
// save the transactions to a collection and read the collection