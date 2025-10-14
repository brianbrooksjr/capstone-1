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
        transactions.add(new Transaction(amount, description, "Depsit"));
        System.out.println("Deposit added.");

    }

}

public void addDeposit(Scanner scanner) {

}
