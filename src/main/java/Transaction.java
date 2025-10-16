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

    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss"); // Format 2023-04-15|10:13:25
        return dateTime.format(formatter) + "|" + description + "|" + vendor + amount;//2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }
}

