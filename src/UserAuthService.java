import java.security.MessageDigest;

public class UserAuthService {

    public static boolean register(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) return false;
        String hashed = md5(password);
        return UserDatabase.addUser(new User(username, hashed));
    }

    public static boolean login(String username, String password) {
        User user = UserDatabase.getUser(username);
        return user != null && user.getPasswordHash().equals(md5(password));
    }

    private static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
