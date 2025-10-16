import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Transaction {
    private double amount;
    private String description;
    private String vendor;
    private LocalDateTime dateTime;

    public Transaction(LocalDateTime dateTime, String description, String vendor, double amount) {
        this.dateTime = new dateTime();
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public String getVendor(){
        return vendor;
    }

    public double getAmount() {
        return amount;
    }
// TODO write in transaction single line format [date|time|description|vendor|amount]

public void addDeposit (Scanner scanner) {
        System.out.print ("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter deposit description: ");
        String description = scanner.next();
//
//        //Deposit information Format
//        transactions.add(new Transaction(dateTime, description, "Deposit"));
//        System.out.println("Deposit added.");
//    }
//
//
//
//
//    public String toString() {
//        SimpleDateForemat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        return formatter.format(date) + " - " + description + ": " + amount;

    }
}
