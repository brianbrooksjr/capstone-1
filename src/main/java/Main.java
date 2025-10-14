import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ledger ledger = new Ledger();
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
