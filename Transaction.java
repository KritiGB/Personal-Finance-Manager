import java.time.LocalDate;

public class Transaction {
    private String type; // "INCOME" or "EXPENSE"
    private double amount;
    private String description;
    private LocalDate date;

    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = LocalDate.now();
    }

    // Constructor for loading from file
    public Transaction(String type, double amount, String description, String dateStr) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = LocalDate.parse(dateStr);
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        // Format: TYPE,AMOUNT,DESC,DATE
        return type + "," + amount + "," + description + "," + date;
    }
    
    public String getDisplayString() {
        return date + " | " + type + ": $" + amount + " (" + description + ")";
    }
}