import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ledger ledger = new Ledger();
        String choice;

        do {
            System.out.println("Home Screen:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            choice = scanner.nextLine().toUpperCase();

            switch (choice) {

        System.out.println("Exiting application.");
        scanner.close();
    }
}
