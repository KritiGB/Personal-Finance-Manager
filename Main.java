import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();
        
        System.out.println("Welcome to Personal Finance Manager");

        while (true) {
            if (authService.getCurrentUser() == null) {
                // Not Logged In Menu
                System.out.println("\n1. Login\n2. Register\n3. Exit");
                System.out.print("Choose: ");
                String choice = scanner.nextLine();

                if (choice.equals("1")) {
                    System.out.print("Username: ");
                    String user = scanner.nextLine();
                    System.out.print("Password: ");
                    String pass = scanner.nextLine();
                    if (authService.login(user, pass)) {
                        System.out.println("Login Successful!");
                    } else {
                        System.out.println("Invalid Credentials.");
                    }
                } else if (choice.equals("2")) {
                    System.out.print("New Username: ");
                    String user = scanner.nextLine();
                    System.out.print("New Password: ");
                    String pass = scanner.nextLine();
                    if (authService.register(user, pass)) {
                        System.out.println("Registration Successful! Please Login.");
                    } else {
                        System.out.println("Username already exists.");
                    }
                } else if (choice.equals("3")) {
                    System.out.println("Goodbye!");
                    break;
                }
            } else {
                // Logged In Menu
                FinanceService finance = new FinanceService(authService.getCurrentUser());
                System.out.println("\nLogged in as: " + authService.getCurrentUser().getUsername());
                System.out.println("1. Add Income");
                System.out.println("2. Add Expense");
                System.out.println("3. View History");
                System.out.println("4. Check Balance");
                System.out.println("5. Logout");
                System.out.print("Choose: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Amount: ");
                        double incAmt = Double.parseDouble(scanner.nextLine());
                        System.out.print("Description: ");
                        String incDesc = scanner.nextLine();
                        finance.addIncome(incAmt, incDesc);
                        break;
                    case "2":
                        System.out.print("Amount: ");
                        double expAmt = Double.parseDouble(scanner.nextLine());
                        System.out.print("Description: ");
                        String expDesc = scanner.nextLine();
                        finance.addExpense(expAmt, expDesc);
                        break;
                    case "3":
                        finance.printHistory();
                        break;
                    case "4":
                        finance.printBalance();
                        break;
                    case "5":
                        authService.logout();
                        System.out.println("Logged out.");
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
        scanner.close();
    }
}