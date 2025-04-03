import java.security.MessageDigest;

/**
 * UserAuthService handles user registration and login logic by integrating
 * with the {@link UserDatabase} and applying MD5 hashing for password security.
 *
 * This class supports authentication-related user stories and is used by
 * {@link LoginScreen} and {@link Session} to verify credentials and create accounts.
 *
 */
public class UserAuthService {

    /**
     * Attempts to register a new user by validating input and storing their credentials.
     * The password is hashed using MD5 before being saved.
     *
     * @param username the desired username
     * @param password the desired password (plaintext)
     * @return true if registration succeeds, false if fields are empty or username is taken
     */
    public static boolean register(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) return false;

        // Hash the password before storage
        String hashed = md5(password);
        
        // Create new User object and attempt to add to the database
        return UserDatabase.addUser(new User(username, hashed));
    }

    /**
     * Attempts to log a user in by validating the credentials.
     * The input password is hashed and matched against the stored hash.
     *
     * @param username the entered username
     * @param password the entered password (plaintext)
     * @return true if authentication is successful, false otherwise
     */
    public static boolean login(String username, String password) {
        User user = UserDatabase.getUser(username);

        // Compare hashed password against sorted hash
        return user != null && user.getPasswordHash().equals(md5(password));
    }

    /**
     * Generates an MD5 hash of the given string.
     *
     * @param input the string to hash (usually a password)
     * @return the hexadecimal MD5 hash of the input
     * @throws RuntimeException if hashing fails due to unavailable algorithm
     */
    private static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b)); // Convert each byte to hex
            }

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e); // Fail fast if MD5 is not available
        }
    }
}
