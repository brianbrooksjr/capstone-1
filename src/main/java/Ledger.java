import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    //
    private List<Transaction> transactions = new ArrayList<>();
    //Deposit UI
    public void addDeposit (Scanner scanner) {
        System.out.print ("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter deposit description: ");
        String descriptions = scanner.next();

        //Format the Deposit information
        transactions.add(new Transaction(amount, description, "Deposit"));
        System.out.println("Deposit added.");

    }
    public void makePayment(Scanner scanner){
        System.out.print("Enter Payment amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter payment description: ");
        String description = scanner.next();

        transactions.add(new Transaction(-amount, description, "Payment"));
        System.out.println("Payment added");
    }

}

public void addDeposit(Scanner scanner) {

}
