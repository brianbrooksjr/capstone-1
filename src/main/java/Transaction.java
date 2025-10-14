import java.util.Date;
import java.text.SimpleDateFormat;


public class Transaction {
    private double amount;
    private String description;
    private Date date;

    public Transaction(Double amount, String description, String type) {
        this.amount = amount;
        this.description = description;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }


    public String toString() {
        SimpleDateForemat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(date) + " - " + description + ": " + amount;

    }
}
