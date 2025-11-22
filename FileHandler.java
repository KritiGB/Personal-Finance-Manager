import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private static final String USER_FILE = "users.txt";
    private static final String TRANS_FILE = "transactions.txt";

    // Save a new user to file
    public static void saveUser(User user) throws IOException {
        try (FileWriter fw = new FileWriter(USER_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(user.toString());
        }
    }

    // Load all users
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(USER_FILE);
        if (!file.exists()) return users;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.add(new User(parts[0], parts[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading users.");
        }
        return users;
    }

    // Save transaction for a specific user
    public static void saveTransaction(String username, Transaction t) throws IOException {
        try (FileWriter fw = new FileWriter(TRANS_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            // Format: username,TYPE,amount,desc,date
            out.println(username + "," + t.toString());
        }
    }

    // Load transactions for a specific user
    public static List<Transaction> loadTransactions(String username) {
        List<Transaction> list = new ArrayList<>();
        File file = new File(TRANS_FILE);
        if (!file.exists()) return list;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                // Ensure line belongs to this user
                if (parts.length == 5 && parts[0].equals(username)) {
                    list.add(new Transaction(parts[1], Double.parseDouble(parts[2]), parts[3], parts[4]));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading transactions.");
        }
        return list;
    }
}