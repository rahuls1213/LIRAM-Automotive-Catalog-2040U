import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserAuthServiceTest {

    @BeforeEach
    void resetUserDatabase() throws Exception {
        // Reset to empty list before each test
        Files.write(Paths.get("data/users.json"), "[]".getBytes());
        UserDatabase.loadUsers();
    }

    @Test
    void testSuccessfulRegistration() {
        assertTrue(UserAuthService.register("newUser", "pass123"));
    }

    @Test
    void testDuplicateRegistrationFails() {
        UserAuthService.register("sameUser", "pass");
        assertFalse(UserAuthService.register("sameUser", "newPass"));
    }

    @Test
    void testLoginSuccess() {
        UserAuthService.register("rahul", "password123");
        assertTrue(UserAuthService.login("rahul", "password123"));
    }

    @Test
    void testLoginFailsWrongPassword() {
        UserAuthService.register("lucas", "realPassword");
        assertFalse(UserAuthService.login("lucas", "wrongPassword"));
    }

    @Test
    void testLoginFailsUserNotFound() {
        assertFalse(UserAuthService.login("ghost", "anything"));
    }
}
