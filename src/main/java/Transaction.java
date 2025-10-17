import java.time.LocalDateTime;

public class Transaction {
    private final LocalDateTime dateTime;
    private final String description;
    private final String vendor;
    private final double amount;

    public Transaction(LocalDateTime dateTime, String description, String vendor, double amount) {
        this.dateTime = dateTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
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

    @Override
    public String toString() {
        // Format for CSV: date|time|description|vendor|amount
        return dateTime.toLocalDate() + "|" + dateTime.toLocalTime() + "|" +
                description + "|" + vendor + "|" + amount;
    }
}
