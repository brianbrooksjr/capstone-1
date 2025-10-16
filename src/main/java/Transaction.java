import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Transaction {
    private double amount;
    private String description;
    private String vendor;
    private Date date;

    public Transaction(double amount, String description, String vendor, LocalDateTime dateTime) {
        this.amount = amount;
        this.description = description;
        this.vendor = vendor;
        this.dateTime = new dateTime();
    }

    public Date getDate() {
        return date;
    }

    public String getDescriptioon(){
        return description;
    }
public void addDeposit (Scanner scanner) {
        System.out.print ("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Enter deposit description: ");
        String description = scanner.next();

        //Deposit information Format
        transactions.add(new Transaction(amount, description, "Deposit"));
        System.out.println("Deposit added.");
    }




    public String toString() {
        SimpleDateForemat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date) + " - " + description + ": " + amount;

    }
}
