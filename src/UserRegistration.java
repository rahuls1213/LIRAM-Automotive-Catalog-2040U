import java.util.HashSet;
import java.util.Set;

public class UserRegistration {
    private final Set<String> registeredUsers = new HashSet<>();

    public boolean register(String username, String password) {
        return !username.isEmpty() && !password.isEmpty() && registeredUsers.add(username);
    }
}
