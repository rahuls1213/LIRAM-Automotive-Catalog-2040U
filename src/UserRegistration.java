import java.util.HashSet;

public class UserRegistration {
    private HashSet<String> registeredUsers;

    public UserRegistration() {
        registeredUsers = new HashSet<>();
    }

    public boolean register(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) return false; // Reject empty fields
        if (registeredUsers.contains(username)) return false; // Reject duplicate usernames
        registeredUsers.add(username);
        return true; // Successful registration
    }
}
