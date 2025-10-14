import java.util.Date;
import java.text.SimpleDateFormat;


public class Transaction {
    private double amount;
    private String description;
    private Date date;

    public Transaction(Double amount, String description, String type) {
        this.amount = amount
        this.description = description;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String to String() {
        SimpleDateForemat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        retuen formatter.format(date) + " - " + description + ": " + amount;

    }
}
