import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserRegistrationTest {
    private UserRegistration userRegistration;

    @BeforeEach
    void setUp() {
        userRegistration = new UserRegistration(); // This class doesn't exist yet!
    }

    @Test
    void testSuccessfulRegistration() {
        boolean result = userRegistration.register("newUser", "securePass123");
        assertTrue(result, "Valid registration should return true");
    }

    @Test
    void testDuplicateUsername() {
        userRegistration.register("testUser", "password123"); // First registration
        boolean result = userRegistration.register("testUser", "newPassword");
        assertFalse(result, "Duplicate usernames should return false");
    }

    @Test
    void testEmptyFields() {
        boolean result = userRegistration.register("", "");
        assertFalse(result, "Empty credentials should return false");
    }
}
