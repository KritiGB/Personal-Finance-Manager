import java.io.IOException;
import java.util.List;

public class FinanceService {
    private User user;

    public FinanceService(User user) {
        this.user = user;
    }

    public void addIncome(double amount, String desc) {
        Transaction t = new Transaction("INCOME", amount, desc);
        saveToDisk(t);
    }

    public void addExpense(double amount, String desc) {
        Transaction t = new Transaction("EXPENSE", amount, desc);
        saveToDisk(t);
    }

    private void saveToDisk(Transaction t) {
        try {
            FileHandler.saveTransaction(user.getUsername(), t);
            System.out.println("Transaction saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving transaction.");
        }
    }

    public void printHistory() {
        List<Transaction> history = FileHandler.loadTransactions(user.getUsername());
        System.out.println("\n--- Transaction History ---");
        if (history.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (Transaction t : history) {
                System.out.println(t.getDisplayString());
            }
        }
    }

    public void printBalance() {
        List<Transaction> history = FileHandler.loadTransactions(user.getUsername());
        double balance = 0;
        for (Transaction t : history) {
            if (t.getType().equals("INCOME")) {
                balance += t.getAmount();
            } else {
                balance -= t.getAmount();
            }
        }
        System.out.println("\n---------------------------");
        System.out.println("Current Wallet Balance: $" + balance);
        System.out.println("---------------------------");
    }
}