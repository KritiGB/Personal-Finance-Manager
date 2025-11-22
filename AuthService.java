import java.io.IOException;
import java.util.List;

public class AuthService {
    private List<User> users;
    private User currentUser;

    public AuthService() {
        // Load users from file when app starts
        this.users = FileHandler.loadUsers();
    }

    public boolean register(String username, String password) {
        // Check if user already exists
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return false; 
            }
        }
        User newUser = new User(username, password);
        users.add(newUser);
        try {
            FileHandler.saveUser(newUser);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
            return false;
        }
    }

    public boolean login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.validatePassword(password)) {
                currentUser = u;
                return true;
            }
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }
}